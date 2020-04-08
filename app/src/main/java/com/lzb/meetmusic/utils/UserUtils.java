package com.lzb.meetmusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.lzb.meetmusic.R;
import com.lzb.meetmusic.activitys.LoginActivity;

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
        return true;
    }

    /**
     * 退出登录
     */
    public static void logout(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        //添加intent标志符，清理task栈，并且重新生成一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //由于清理了task栈，导致Activity过渡动画效果异常，可以使用以下方法重新定义Activity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }
}
