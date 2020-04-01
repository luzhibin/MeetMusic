package com.lzb.meetmusic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.lzb.meetmusic.R;

/**    除了使用include标签引入layout外，还可以使用自定义控件进行布局
 *  1、input_icon：输入框前面的图标
 *  2、input_hint：输入框的提示内容
 *  3、is_password：输入的内容是否需要以密文的形式展示
 */
public class InputView extends FrameLayout {

    private int inputIcon;      //输入框小图标
    private String inputHint;   //输入框提示内容
    private boolean isPassword; //是否密文

    private View mView;
    private ImageView mIvIcon;
    private EditText mEtInput;

    public InputView(Context context) {
        super(context);
        init(context,null);
    }

    public InputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public InputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**InputView的初始化方法
     * @param context
     * @param  attributeSet
     */
    private void init(Context context,AttributeSet attributeSet){
        if (attributeSet == null){
            return;
        }
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.inputView);
        inputIcon = typedArray.getResourceId(R.styleable.inputView_input_icon, R.mipmap.logo);
        inputHint = typedArray.getString(R.styleable.inputView_input_hint);
        isPassword = typedArray.getBoolean(R.styleable.inputView_is_password,false);
        //释放typedArray
        typedArray.recycle();

        //绑定layout布局
        mView = LayoutInflater.from(context).inflate(R.layout.input_view,InputView.this,false);
        mIvIcon = mView.findViewById(R.id.iv_icon);
        mEtInput = mView.findViewById(R.id.et_input);

        //关联属性
        mIvIcon.setImageResource(inputIcon);
        mEtInput.setHint(inputHint);
        //“|”为 位运算符
        //|按位或和&按位与计算方式都是转换二进制再计算，不同的是运算规则(一个为真即为真)1|0 = 1 , 1|1 = 1 , 0|0 = 0 , 0|1 = 1
        mEtInput.setInputType(isPassword ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_PHONE);

        addView(mView);
    }

    /**
     * 返回输入的内容
     * @return
     */
    public String getInputStr(){
        return mEtInput.getText().toString().trim(); //.trim方法清除输入框的空格
    }
}
