package com.lzb.meetmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.Utils;
import com.lzb.meetmusic.R;
import com.lzb.meetmusic.utils.SPUtils;
import com.lzb.meetmusic.utils.UserUtils;
import com.lzb.meetmusic.views.InputView;

import java.util.Map;

public class LoginActivity extends BaseActivity {

    private InputView mInputPhone;
    private InputView mInputPassword;

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

        mInputPhone = findViewById(R.id.input_phone);
        mInputPassword = findViewById(R.id.input_password);
    }

    /**
     * 跳转注册页面的=点击事件
     */
    public void onRegisterClick(View view){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        //千万别手贱加    finsh();
    }

    /**
     * 登录按钮的点击事件
     * @param view
     */
    public void onCommitClick(View view){
        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();

        //验证用户输入合法性
        if (!UserUtils.validateLogin(this,phone,password)){
            return;
        }
        //验证通过，通过intent跳转到mainActivity
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
