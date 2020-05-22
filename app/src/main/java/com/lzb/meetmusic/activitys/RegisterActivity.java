package com.lzb.meetmusic.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lzb.meetmusic.R;
import com.lzb.meetmusic.utils.UserUtils;
import com.lzb.meetmusic.views.InputView;

public class RegisterActivity extends BaseActivity {

    private InputView mInputPhone;
    private InputView mInputPassword;
    private InputView mInputPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    /**
     * 初始化View
     */
    private void initView(){
        initNavigationBar(true,"注册",false);
        mInputPhone = findViewById(R.id.input_phone);
        mInputPassword = findViewById(R.id.input_password);
        mInputPasswordConfirm = findViewById(R.id.input_password_confirm);
    }

    /**注册按钮点击事件
     *  1.用户输入合法性验证：
     *      ①用户输入的手机号是否合法
     *      ②用户是否输入了密码，且两次输入的密码是否相同
     *      ③输入的手机号是否已经被注册
     *  2.保存用户输入的手机号和密码
     *      ①需要对用户的密码进行MD5加密，再存进数据库
     */
    public void onRegisterClick(View v){
        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();
        String passwordConfirm = mInputPasswordConfirm.getInputStr();

        boolean result = UserUtils.registerUser(this, phone, password, passwordConfirm);

        if (!result){
            return;
        }else{
            //后退到登录页面
            onBackPressed();
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        }

    }
}
