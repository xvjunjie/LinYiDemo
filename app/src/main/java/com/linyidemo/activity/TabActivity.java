package com.linyidemo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.linyidemo.R;
import com.linyidemo.appbase.AppBaseActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.Bind;

/**
 * Created by 15596 on 2017/1/11.
 * 这是多标签选择
 */

public class TabActivity extends AppBaseActivity {
    @Bind(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;

    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView"};


    @Override
    public int getLayoutResId() {
        return R.layout.activity_tabflowlayout;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        idFlowlayout.setAdapter(new TagAdapter<String>(mVals) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                final LayoutInflater mInflater = LayoutInflater.from(TabActivity.this);
                TextView textView = (TextView) mInflater.inflate(R.layout.tv, parent , false);
                textView.setText(s);
                return textView;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
