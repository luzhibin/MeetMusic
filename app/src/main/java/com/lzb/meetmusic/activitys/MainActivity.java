package com.lzb.meetmusic.activitys;

import android.os.Bundle;

import com.blankj.utilcode.util.Utils;
import com.lzb.meetmusic.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化Android util code
        Utils.init(this);
        initView();
    }

    private void initView(){
        initNavigationBar(false,"邂逅音乐",true);
    }
}
