package com.linyidemo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.linyidemo.R;
import com.linyidemo.appbase.AppBaseActivity;

/**
 * Created by Administrator on 2017/1/20.
 */

public class PopupWindowActivity extends AppBaseActivity implements View.OnTouchListener{
    private PopupWindow mPopWindow;
    private ImageView iv_cancel , iv_said;
    private EditText et_input;
    private LinearLayout ll_layout;
    private Button btn;

    private DisplayMetrics displayMetrics;
    private float lastX=0;
    private float lastY=0;
    private int screenWidth=0;
    private int screenHeight=0;
    private int left;
    private int top;
    private int right;
    private int bottom;
    private boolean isFirst=true;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_popupwindow_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.linyi_test, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);

        //显示PopupWindow
//        View rootview = LayoutInflater.from(mActivity).inflate(R.layout.activity_popupwindow_main, null);
//        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
        mPopWindow.showAsDropDown(btn);

        iv_cancel = (ImageView)contentView.findViewById(R.id.iv_cancel);
        iv_cancel.setOnClickListener(this);
        ll_layout = (LinearLayout) contentView.findViewById(R.id.ll_layout);
        ll_layout.setOnTouchListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){

            case R.id.iv_cancel:
                mPopWindow.dismiss();
                break;

        }


    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (isFirst) {
            // 得到屏幕的宽
            displayMetrics = getResources().getDisplayMetrics();
            screenWidth = displayMetrics.widthPixels;
            // 得到标题栏和状态栏的高度
            Rect rect = new Rect();
            Window window = getWindow();
            ll_layout.getWindowVisibleDisplayFrame(rect);
            int statusBarHeight = rect.top;
            int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
            int titleBarHeight = contentViewTop - statusBarHeight;
            // 得到屏幕的高
            screenHeight = displayMetrics.heightPixels- (statusBarHeight + titleBarHeight);
            isFirst=false;
        }
        int action=event.getAction();
        switch (action) {
            //按下
            case MotionEvent.ACTION_DOWN:
                //按下处坐标
                lastX=event.getRawX();
                lastY=event.getRawY();
                break;
            //移动
            case MotionEvent.ACTION_MOVE:
                //移动的距离
                float distanceX=event.getRawX()-lastX;
                float distanceY=event.getRawY()-lastY;
                //移动后控件的坐标
                left=(int)(view.getLeft()+distanceX);
                top=(int)(view.getTop()+distanceY);
                right=(int)(view.getRight()+distanceX);
                bottom=(int)(view.getBottom()+distanceY);
                //处理拖出屏幕的情况
                if (left<0) {
                    left=0;
                    right=view.getWidth();
                }
                if (right>screenWidth) {
                    right=screenWidth;
                    left=screenWidth-view.getWidth();
                }
                if (top<0) {
                    top=0;
                    bottom=view.getHeight();
                }
                if (bottom>screenHeight) {
                    bottom=screenHeight;
                    top=screenHeight-view.getHeight();
                }
                //显示图片
                view.layout(left, top, right, bottom);
                lastX=event.getRawX();
                lastY=event.getRawY();
                break;
            //抬起
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return false;
    }
}