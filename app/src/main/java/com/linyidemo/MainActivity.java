package com.linyidemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.linyidemo.activity.FloatView;
import com.linyidemo.activity.MyExpandListviewActivity;
import com.linyidemo.activity.PopupWindowActivity;
import com.linyidemo.activity.TabActivity;
import com.linyidemo.appbase.AppBaseActivity;

import butterknife.Bind;

public class MainActivity extends AppBaseActivity {

    @Bind(R.id.but_ToExpandAblesListview)
    Button but_ToExpandAblesListview;
    @Bind(R.id.but_TabActivity)
    Button butTabActivity;
    @Bind(R.id.btn_PopupWindowActivity)
    Button btn_PopupWindowActivity;
    @Bind(R.id.btn_FloatView)
    Button btn_FloatView;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        but_ToExpandAblesListview.setOnClickListener(this);
        butTabActivity.setOnClickListener(this);
        btn_PopupWindowActivity.setOnClickListener(this);
        btn_FloatView.setOnClickListener(this);
    }

    @Override
    public void viewClick(View v) {
        super.viewClick(v);
        switch (v.getId()) {
            case R.id.but_ToExpandAblesListview:
                startActivity(this, MyExpandListviewActivity.class);
                break;
            case R.id.but_TabActivity:
                startActivity(this, TabActivity.class);
                break;
            case R.id.btn_PopupWindowActivity:
                startActivity(this, PopupWindowActivity.class);
                break;
            case R.id.btn_FloatView:
                btn_FloatView();
                break;
        }

    }

    private void btn_FloatView() {
        FloatView.showFloatView(this,R.layout.linyi_test);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
