package com.android.baselibrary.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.baselibrary.fragment.BaseFragment;
import com.android.baselibrary.interfaces.IActivity;
import com.android.baselibrary.interfaces.IRegister;
import com.android.baselibrary.utils.KeyBoardUtil;
import com.android.baselibrary.utils.SPreUtil;

import butterknife.ButterKnife;

/**
 * Created by 15596 on 2016/10/19.
 */

public abstract class BaseActivity extends AppCompatActivity
        implements View.OnClickListener, IActivity, IRegister{
    private static final String SP_NAME = "firstConfig";

    private   int          mActivityState;
    private BaseFragment mCurrentFragment;
    protected Activity mActivity;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        initPre();
        BaseActivityStack.getInstance().addActivity(this);
        setContentView(getLayoutResId());

        ButterKnife.bind(this);
        SPreUtil spUtil = new SPreUtil(this, SP_NAME);
        final String simpleName = this.getClass().getSimpleName();
        if (spUtil.getBooleanValue(simpleName, true)) {
            onFirst();
            spUtil.putBooleanValue(simpleName, false);
        }
        initData();
        initView(savedInstanceState);
        register();
    }



    @Override public void onFirst() { }
    @Override public void initPre() { }
    @Override public void initData() { }
    @Override public void initView(Bundle savedInstanceState) { }
    @Override public void showProgress() { }
    @Override public void hideProgress() { }
    @Override public void register() { }
    @Override public void unRegister() { }
    @Override public void viewClick(View v) { }


    @Override public void onClick(View v) {
        viewClick(v);
    }


    @Override public void skipActivity(Activity aty, Class<?> cls) {
        startActivity(aty, cls);
        aty.finish();
    }


    @Override public void skipActivity(Activity aty, Intent it) {
        startActivity(aty, it);
        aty.finish();
    }


    @Override public void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
        startActivity(aty, cls, extras);
        aty.finish();
    }


    @Override public void startActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }


    @Override public void startActivity(Activity aty, Intent it) {
        aty.startActivity(it);
    }


    @Override public void startActivity(Activity aty, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    /**
     * 获取当前Activity状态
     *     {@link IActivity#RESUME},
     *     {@link IActivity#PAUSE},
     *     {@link IActivity#STOP},
     *     {@link IActivity#DESTROY}.
     *
     * @return
     */
    public int getActivityState() {
        return mActivityState;
    }

    /**
     * 获取当前显示的Fragment
     * @return
     */
    public BaseFragment getFragment() {
        return mCurrentFragment;
    }

    /**
     * 用Fragment替换视图
     *
     * @param resView 将要被替换掉的视图
     * @param targetFragment 用来替换的Fragment
     */
    public void changeFragment(int resView, BaseFragment targetFragment) {
        if (targetFragment.equals(mCurrentFragment)) {
            return;
        }
        android.support.v4.app.FragmentTransaction transaction
                = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(resView, targetFragment, targetFragment.getClass().getName());
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
            targetFragment.onFragmentShow();
        }
        if (mCurrentFragment != null && mCurrentFragment.isVisible()) {
            transaction.hide(mCurrentFragment);
            mCurrentFragment.onFragmentHide();
        }
        mCurrentFragment = targetFragment;
        transaction.commit();
    }


    @Override protected void onResume() {
        super.onResume();
        mActivityState = RESUME;
    }


    @Override protected void onPause() {
        super.onPause();
        mActivityState = PAUSE;
    }


    @Override protected void onStop() {
        super.onStop();
        mActivityState = STOP;
    }


    @Override public void finish() {
        KeyBoardUtil.hide(getWindow().getDecorView());//强制关闭输入法
        super.finish();
    }


    @Override protected void onDestroy() {
        unRegister();
        super.onDestroy();
        ButterKnife.unbind(this);
        mActivityState = DESTROY;
        BaseActivityStack.getInstance().finishActivity(this);

    }



}