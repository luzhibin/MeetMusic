package com.lzb.meetmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lzb.meetmusic.R;
import com.lzb.meetmusic.utils.UserUtils;

public class MeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }

    public void initView(){
        initNavigationBar(true,"个人中心",false);
    }

    /**
     * 修改密码点击事件
     */
    public void onChangeClick(View view){
        startActivity(new Intent(MeActivity.this,ChangePasswordActivity.class));
    }

    /**
     * 退出登录点击事件
     */
    public void onLogoutClick(View view){
        UserUtils.logout(MeActivity.this);
    }
}
