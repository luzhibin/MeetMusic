package com.lzb.meetmusic.helps;

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
public class UserHelper {

    private static UserHelper instance;

    private UserHelper(){ }

    public static UserHelper getInstance(){
        if (instance == null){
            synchronized (UserHelper.class){
                if (instance == null){
                    instance = new UserHelper();
                }
            }
        }
        return instance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;

}
