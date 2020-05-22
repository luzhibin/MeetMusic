package com.lzb.meetmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lzb.meetmusic.R;
import com.lzb.meetmusic.helps.UserHelper;
import com.lzb.meetmusic.utils.UserUtils;

public class MeActivity extends BaseActivity {

    private TextView mTvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }

    public void initView(){
        initNavigationBar(true,"个人中心",false);

        mTvUser = findViewById(R.id.tv_user);
        mTvUser.setText("用户名" + UserHelper.getInstance().getPhone());
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
