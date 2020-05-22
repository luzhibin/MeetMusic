package com.lzb.meetmusic.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.lzb.meetmusic.R;
import com.lzb.meetmusic.helps.MediaPlayerHelp;

public class PlayMusicView extends FrameLayout {

    private Context mContext;
    private MediaPlayerHelp mMediaPlayerHelp;
    private boolean isPlaying;
    private String mPath;
    private View mView;
    private FrameLayout mFlPlayMusic;
    private ImageView mIvIcon,mIvNeedle,mIvPlay;

    private Animation mPlayMusicAnim,mPlayNeedleAnim,mStopNeedleAnim;


    public PlayMusicView(Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        mContext = context;

        mView = LayoutInflater.from(mContext).inflate(R.layout.play_music, this, false);

        mFlPlayMusic = mView.findViewById(R.id.fl_play_music);
        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trigger();
            }
        });
        mIvIcon = mView.findViewById(R.id.iv_icon);
        mIvNeedle = mView.findViewById(R.id.iv_needle);
        mIvPlay = mView.findViewById(R.id.iv_play);


        /**
         * 一、定义需要执行的动画
         *  1.光盘转动的动画
         *  2.指针指向光盘的动画
         *  3.指针离开光盘的动画
         *
         * 二、通过startAnimation方法让view执行动画
         */
        mPlayMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music_anim);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needle_anim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);

        addView(mView);

        mMediaPlayerHelp = MediaPlayerHelp.getInstance(mContext);
    }

    /**
     * 切换播放状态
     */
    private void trigger(){
        if (isPlaying){
            stopMusic();
        }else{
            playMusic(mPath);
        }
    }

    /**
     * 播放音乐
     * 要播放音乐  需要用到安卓提供的MediaPlayer类
     */
    public void playMusic(String path){
        mPath = path;
        isPlaying = true;
        mIvPlay.setVisibility(View.GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mIvNeedle.startAnimation(mPlayNeedleAnim);

        /**当playMusic方法被调用时
         *  1、判断当前音乐是否已经在播放状态中
         *      是：执行start方法
         *      否：重置MediaPlayer的状态，即调用setPath方法 指定一个当前需要播放的音乐
         */
        if (mMediaPlayerHelp.getPath()!=null && mMediaPlayerHelp.getPath().equals(path)){
            mMediaPlayerHelp.start();
        }else{
            mMediaPlayerHelp.setPath(path);
            mMediaPlayerHelp.setOnMediaPlayerHelperListener(new MediaPlayerHelp.onMediaPlayerHelperListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayerHelp.start();
                }
            });
        }
    }

    /**
     * 停止播放
     */
    public void stopMusic(){
        isPlaying = false;
        mIvPlay.setVisibility(View.VISIBLE);
        mFlPlayMusic.clearAnimation();
        mIvNeedle.startAnimation(mStopNeedleAnim);

        mMediaPlayerHelp.pause();
    }

    /**
     * 设置光盘中显示的音乐封面图片
     * @param icon 图片的地址
     */
    public void setMusicIcon(String icon){
        Glide.with(mContext)
                .load(icon)
                .into(mIvIcon);
    }
}
