package com.lzb.meetmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lzb.meetmusic.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    /**
     * 初始化View
     */
    private void initView(){
        initNavigationBar(false,"登录",false);
    }
}
