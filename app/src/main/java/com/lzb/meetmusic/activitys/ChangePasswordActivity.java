package com.lzb.meetmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lzb.meetmusic.R;
import com.lzb.meetmusic.utils.UserUtils;
import com.lzb.meetmusic.views.InputView;

public class ChangePasswordActivity extends BaseActivity {

    private InputView mOldPassword;
    private InputView mPassword;
    private InputView mPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initView();
    }

    private void initView(){
        initNavigationBar(true,"修改密码",false);

        mOldPassword = findViewById(R.id.input_old_password);
        mPassword = findViewById(R.id.input_password);
        mPasswordConfirm = findViewById(R.id.input_password_confirm);
    }

    public void onChangePasswordClick(View v){
        String oldPassword = mOldPassword.getInputStr();
        String password = mPassword.getInputStr();
        String passwordConfirm = mPasswordConfirm.getInputStr();

        boolean result = UserUtils.changePassword(this, oldPassword, password, passwordConfirm);
        if (!result){
            return;
        }else{
            UserUtils.logout(this);
            Toast.makeText(this, "修改密码成功，请重新登录", Toast.LENGTH_LONG).show();
        }
    }
}
