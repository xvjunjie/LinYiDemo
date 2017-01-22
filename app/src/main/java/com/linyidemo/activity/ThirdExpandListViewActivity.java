package com.linyidemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.linyidemo.R;
import com.linyidemo.adapter.ExpandAdapter;
import com.linyidemo.appbase.AppBaseActivity;
import com.linyidemo.bean.FirstItem;
import com.linyidemo.bean.SecondItem;
import com.linyidemo.bean.ThirdItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WESTAKE on 2017/1/19.
 */

public class ThirdExpandListViewActivity extends AppBaseActivity {

    private ExpandableListView listView;
    private List<FirstItem> firstList;
    private ExpandableListAdapter eAdpater;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_third_expandablelistview;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        listView = (ExpandableListView) findViewById(R.id.third_expandableListView);
        firstList = new ArrayList<FirstItem>();


        initDatas();

    }

    private void initDatas() {

        for (int i = 0; i < 10; i++) {
            FirstItem firstItem = new FirstItem();
            firstItem.setId(i);
            firstItem.setTitle("这是第" + i + "个");
            List<SecondItem> seList = new ArrayList<SecondItem>();
            for (int j = i; j < 10; j++) {
                SecondItem secondItem = new SecondItem();
                secondItem.setId(i);
                secondItem.setTitle("子的第" + j * 78 + "条");
                seList.add(secondItem);
                List<ThirdItem> thirdList = new ArrayList<ThirdItem>();
                for (int k = 0; k < j + 1; k++) {
                    ThirdItem thirdItem = new ThirdItem();
                    thirdItem.setId(k);
                    thirdItem.setImage("sss");
                    thirdItem.setName("张凯强" + k + j);
                    thirdItem.setTel("10086" + j + k);
                    thirdList.add(thirdItem);
                }
                secondItem.setThirdItems(thirdList);
            }
            firstItem.setSecondItems(seList);
            firstList.add(firstItem);
        }
        eAdpater = new ExpandAdapter(ThirdExpandListViewActivity.this, firstList,stvClickEvent);
        listView.setAdapter(eAdpater);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(ThirdExpandListViewActivity.this,
                        childPosition + "---ccc===" + groupPosition,
                        Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    ExpandableListView.OnChildClickListener stvClickEvent = new ExpandableListView.OnChildClickListener() {

        @Override
        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {
            // TODO Auto-generated method stub
            String msg = "parent_id = " + groupPosition + " child_id = "
                    + childPosition;
            Toast.makeText(ThirdExpandListViewActivity.this, msg,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    };


}
