package com.lzb.meetmusic.helps;

import com.lzb.meetmusic.models.UserModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Realm帮助类
 */
public class RealmHelper {

    private Realm mRealm;

    public RealmHelper(){
        //获取Realm实例
        mRealm = Realm.getDefaultInstance();
    }

    //关闭数据库的方法
    public void close(){
        if (mRealm != null && !mRealm.isClosed()){
            mRealm.close();
        }
    }

    /**
     * 保存用户信息
     * @param userModel
     */
    public void saveUser(UserModel userModel){
        //开启一个事务
        mRealm.beginTransaction();
        mRealm.insert(userModel);
        //提交事务
        mRealm.commitTransaction();
    }

    /**
     * 返回所有用户
     */
    public List<UserModel> getAllUser(){
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        return results;
    }

    /**
     * 验证用户信息
     */
    public boolean validateUser(String phone,String password){
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        query = query.equalTo("phone", phone)
                .equalTo("password", password);
        UserModel userModel = query.findFirst();

        if (userModel != null){
            return true;
        }
        return false;
    }

    /**
     * 获取当前用户
     * @return
     */
    public UserModel getUser(){
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        UserModel userModel = query.equalTo("phone", UserHelper.getInstance().getPhone()).findFirst();
        return userModel;
    }

    /**
     * 修改密码
     * @param password
     */
    public void changePassword(String password){
        UserModel userModel = getUser();
        mRealm.beginTransaction();
        userModel.setPassword(password);
        mRealm.commitTransaction();
    }
}
