package com.lzb.meetmusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.lzb.meetmusic.R;
import com.lzb.meetmusic.activitys.LoginActivity;
import com.lzb.meetmusic.helps.RealmHelper;
import com.lzb.meetmusic.helps.UserHelper;
import com.lzb.meetmusic.models.UserModel;

import java.util.List;

public class UserUtils {

    /**
     * 验证登录用户的输入合法性
     */
    public static boolean validateLogin(Context context,String phone,String password){
/*        //简单验证手机号
        RegexUtils.isMobileSimple(phone);*/
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(context,"请输入手机号码",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            //精确验证手机号
            if (!RegexUtils.isMobileExact(phone)){
                Toast.makeText(context, "无效的手机号", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        //判断密码是否为空
        if (TextUtils.isEmpty(password)){
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        /**
         * 1.用户当前手机号是否已经注册
         * 2.用户输入的手机号和密码是否匹配
         */
        if (!UserUtils.userExistFromPhone(phone)){
            Toast.makeText(context, "手机号或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }
        RealmHelper realmHelper = new RealmHelper();
        boolean result = realmHelper.validateUser(phone, EncryptUtils.encryptMD5ToString(password));
        realmHelper.close();
        if (!result){
            Toast.makeText(context, "手机号或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }

        //保存用户登录标记
        boolean isSave = SPUtils.saveUser(context, phone);
        if (!isSave){
            Toast.makeText(context, "SharedPreferencesException，Failed to save user information", Toast.LENGTH_LONG).show();
            return false;
        }

        //使用线程安全的单例设计模式，保存用户手机号
        UserHelper.getInstance().setPhone(phone);

        return true;
    }

    /**
     * 退出登录
     */
    public static void logout(Context context){
        //删除SharedPreferences中保存的用户标记
        boolean isRemove = SPUtils.removeUser(context);
        if (!isRemove){
            Toast.makeText(context, "SharedPreferencesException,SystemError", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(context, LoginActivity.class);
        //添加intent标志符，清理task栈，并且重新生成一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //由于清理了task栈，导致Activity过渡动画效果异常，可以使用以下方法重新定义Activity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }

    /**
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     */
    public static boolean registerUser(Context context,String phone,String password,String passwordConfirm){
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(context,"请输入手机号码",Toast.LENGTH_SHORT).show();
            return false;
        }
        //精确验证手机号
        if (!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context, "无效的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(password)){
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (StringUtils.isEmpty(passwordConfirm)){
            Toast.makeText(context, "请确认密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(passwordConfirm)){
            Toast.makeText(context, "两次输入的密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }
        //用户当前的手机号是否已经注册
        /**
         * 1.通过realm获取到当前已经注册的所有用户
         * 2.根据用户输入的手机号匹配查询的所有用户
         *      如果可以匹配，则证明该手机号已经被注册了，否则表示还未注册
         */

        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));

        saveUser(userModel);
        return true;
    }

    /**
     * 保存用户到数据库
     * @param userModel
     */
    public static void saveUser(UserModel userModel){
        RealmHelper realmHelper = new RealmHelper();
        realmHelper.saveUser(userModel);
        realmHelper.close();
    }

    /**
     * 根据手机号判断用户是否存在
     */
    public static boolean userExistFromPhone(String phone){
        boolean result = false;

        RealmHelper realmHelper = new RealmHelper();
        List<UserModel> allUser = realmHelper.getAllUser();

        for (UserModel userModel : allUser){
            if (userModel.getPhone().equals(phone)){
                //当前手机号已经存在数据库
                result = true;
                break;
            }
        }

        realmHelper.close();

        return result;
    }

    /**
     * 验证是否存在已登录用户
     */
    public static boolean validateUserLogin(Context context){
        return SPUtils.isLoginUser(context);
    }

    /**
     * 修改密码
     * 1.数据验证
     *       ① 原密码是否输入
     *       ② 新密码是否输入并且新密码与确认密码是否相同
     *       ③ 原密码输入是否正确
     *          1）Realm获取到当前登录的用户模型
     *          2）根据用户模型中保存的密码匹配用户原密码
     * 2.利用 Realm 数据自动更新的特性完成密码的修改
     */
    public static boolean changePassword(Context context,String oldPassword,String password,String passwordConfirm){

        if (TextUtils.isEmpty(oldPassword)){
            Toast.makeText(context, "请输入原密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(context, "请输入新密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(passwordConfirm)){
            Toast.makeText(context, "两次输入的密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }

        //验证原密码是否正确
        RealmHelper realmHelper = new RealmHelper();
        UserModel userModel = realmHelper.getUser();

        if (!EncryptUtils.encryptMD5ToString(oldPassword).equals(userModel.getPassword())){
            Toast.makeText(context, "原密码不正确", Toast.LENGTH_SHORT).show();
            return false;
        }

        realmHelper.changePassword(EncryptUtils.encryptMD5ToString(password));
        realmHelper.close();

        return true;
    }

}
