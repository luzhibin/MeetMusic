package com.lzb.meetmusic.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

//直接继承ImageView会给出一个警告：不应该直接继承ImageView，而应该继承它的扩展类：AppCompatImageView
public class WEqualsH_ImageView extends AppCompatImageView {
    public WEqualsH_ImageView(Context context) {
        super(context);
    }

    public WEqualsH_ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualsH_ImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * @param widthMeasureSpec      这两个参数都是用来测量View宽高的变量，但不是具体的尺寸的数值
     * @param heightMeasureSpec     这两个参数都是用来测量View宽高的变量，但不是具体的尺寸的数值
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

/*        //获取View宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //获取View的模式（match_parent、warp_content、具体dp）
        int mode = MeasureSpec.getMode(widthMeasureSpec);*/
    }
}
