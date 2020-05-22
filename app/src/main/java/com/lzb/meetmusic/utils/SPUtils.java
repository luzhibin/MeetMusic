package com.lzb.meetmusic.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.lzb.meetmusic.constants.SPConstants;
import com.lzb.meetmusic.helps.UserHelper;

import java.util.HashMap;
import java.util.Map;

public class SPUtils {

    /**
     * 一、用户登录
     *      1. 当用户登陆APP时，利用SharedPreference 保存登录用户的用户标记
     *      2. 利用全局单例类UserHelper保存用户登录信息
     *          什么时候保存？
     *          ① 用户登录后
     *          ② 用户重新打开应用程序，检测SharedPreference中是否存在登录用户标记
     *              如果存在，则为UserHelper赋值，并且进入主页。如果不存在，则进入登录页面
     * 二、用户退出
     *      1.删除SharedPreference保存的用户标记，退出到登录页面
     */
    /**
     *
     * @param context
     * @param phone
     * @return 当登录信息(phone)正确保存到SharedPreferences中时，会返回true，否则返回false
     */
    @SuppressLint("CommitPrefEdits")
    public static boolean saveUser(Context context, String phone){
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SPConstants.SP_KEY_PHONE, phone);
        boolean result = editor.commit();
        return result;

    }
    /**
     * 验证是否存在已登录用户
     */
    public static boolean isLoginUser(Context context){
        boolean result = false;

        //判断SharedPreference是否保存过用户的登录信息
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        String phone = sp.getString(SPConstants.SP_KEY_PHONE, "");
        //如果不为空，则当前存在登录用户
        if (!TextUtils.isEmpty(phone)){
            result = true;
            UserHelper.getInstance().setPhone(phone);
        }

        return result;
    }

    /**
     * 删除用户标记
     */
    public static boolean removeUser(Context context){
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(SPConstants.SP_KEY_PHONE);
        boolean result = editor.commit();
        return result;
    }

}
