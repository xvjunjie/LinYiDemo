package com.linyidemo.activity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.linyidemo.R;
import com.linyidemo.adapter.ExpandAbleListviewAdapter;
import com.linyidemo.appbase.AppBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/1/11.
 * 多级类表
 */

public class MyExpandListviewActivity extends AppBaseActivity {
    @Bind(R.id.expandableListView)
    ExpandableListView expandableListView;

    private List<String> groupDatas;//父级类表数据
    private List<List<String>> childDatas;//父级类表数据
    private ExpandAbleListviewAdapter expandAbleListviewAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_expendablelistview;
    }


    @Override
    public void initData() {
        super.initData();

        groupDatas = new ArrayList<>();
        childDatas = new ArrayList<List<String>>();

        addInfoDatas("北京",new String[]{"朝阳","海淀","东城区","西城区"});
        addInfoDatas("河北", new String[]{"邯郸","石家庄","邢台"});
        addInfoDatas("广东", new String[]{"广州","深圳","珠海"});

    }

    private void addInfoDatas(String s, String[] strChar) {
        groupDatas.add(s);
        List<String> listDatas = new ArrayList<>();
        for (int i = 0 ; i<strChar.length; i++){
            listDatas.add(strChar[i]);
        }
        childDatas.add(listDatas);
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        expandAbleListviewAdapter = new ExpandAbleListviewAdapter(this,groupDatas , childDatas);
        expandableListView.setAdapter(expandAbleListviewAdapter);
    }



}
