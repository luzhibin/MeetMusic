package com.lzb.meetmusic.activitys;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzb.meetmusic.R;

/**
 * 顶部导航栏 navigation_bar 需要在多个activity复用
 * 并且标题、后退按钮、个人中心按钮都是多变的
 * 为了处理这个问题  需要引入一些属性控制控件的显示
 */
public class BaseActivity extends Activity {

    private ImageView mIvBack;  //返回按钮  mIvBack：my ImageView Back
    private ImageView mIvMe;    //个人中心按钮
    private TextView mTvTitle;  //标题

    /**
     * 该方法用于初始化导航栏
     * @param ：isShowBack  是否显示导航栏的后退按钮
     * @param ： title  导航栏的标题
     * @param ：NavigationBar中最右侧的"个人中心"按钮是否显示
     */
    protected void initNavigationBar(boolean isShowBack,String title,boolean isShowMe){
        mIvBack = findViewById(R.id.iv_back);
        mTvTitle = findViewById(R.id.tv_title);
        mIvMe = findViewById(R.id.iv_me);

        //设置按钮的显示
        mIvBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        mIvMe.setVisibility(isShowMe ? View.VISIBLE : View.GONE);
        mTvTitle.setText(title);

        //给IvBack设置一个点击事件
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击时执行后退操作
                onBackPressed();
            }
        });

    }
}
