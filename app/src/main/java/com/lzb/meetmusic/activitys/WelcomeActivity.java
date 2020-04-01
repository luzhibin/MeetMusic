package com.lzb.meetmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lzb.meetmusic.R;

import java.util.Timer;
import java.util.TimerTask;

/*     1.来到WelcomeActivity的时候需要延迟3秒
 *     2.延迟3秒后跳转页面
 */
public class WelcomeActivity extends BaseActivity {

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();
    }

    /*初始化*/
    private void init(){
        //Timer类用来在一个后台线程按指定的计划来执行指定的任务，即设置计划任务。
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.e("WelcomeActivity","当前线程为："+Thread.currentThread());
                toMain();
                toLogin();
            }
        },3 * 1000);    //3*1000ms=3秒
    }

    /*跳转到主界面的方法*/
    /*Intent是Android程序中各组件之间进行交互的一种重要方式，一般用于不同组件之间传递数据，启动活动，启动服务等*/
    private void toMain(){
        //该构造函数要求接收两个参数，第一个参数Context要求提供一个启动活动的上下文，第二个参数Class则是指定想要启动的目标活动
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);  //该方法用于专门启动服务
        finish();
    }

    /*跳转到LoginActivity*/
    private void toLogin(){
        Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
