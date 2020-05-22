package com.lzb.meetmusic.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * 由于全局只需要用一个播放器，所以可以创建单例模式
 */
public class MediaPlayerHelp {

    private static MediaPlayerHelp instance;

    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private String mPath;
    private onMediaPlayerHelperListener onMediaPlayerHelperListener;

    public void setOnMediaPlayerHelperListener(MediaPlayerHelp.onMediaPlayerHelperListener onMediaPlayerHelperListener) {
        this.onMediaPlayerHelperListener = onMediaPlayerHelperListener;
    }

    public static MediaPlayerHelp getInstance(Context context){
        if (instance == null){
            synchronized (MediaPlayerHelp.class){
                if (instance == null){
                    instance = new MediaPlayerHelp(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayerHelp(Context context){
        this.mContext = context;
        mMediaPlayer = new MediaPlayer();
    }

    /**MediaPlayerHelp需要的方法
     * 1、setPath：当前需要播放的音乐的路径地址
     * 2、start：播放音乐
     * 3、pause：暂停播放
     */

    //当前需要播放的音乐的路径地址
    public void setPath(String path){
        /**
         * 1、音乐正在播放，需要重置音乐播放状态
         * 2、设置播放的音乐的路径
         * 3、准备播放
         */

        mPath = path;

        //1、音乐正在播放，需要重置音乐播放状态
        if (mMediaPlayer.isPlaying()){
            mMediaPlayer.reset();
        }
        //2、设置播放的音乐的路径
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3、准备播放
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (onMediaPlayerHelperListener != null){
                    onMediaPlayerHelperListener.onPrepared(mp);
                }
            }
        });
    }

    /**
     * @return 返回正在播放的音乐路径
     */
    public String getPath(){
        return mPath;
    }

    //播放音乐
    public void start(){
        //验证当前是否在播放音乐
        if (mMediaPlayer.isPlaying()){
            return;
        }
        mMediaPlayer.start();
    }

    //暂停播放
    public void pause(){
        mMediaPlayer.pause();
    }

    public interface onMediaPlayerHelperListener{
        void onPrepared(MediaPlayer mp);
    }
}
