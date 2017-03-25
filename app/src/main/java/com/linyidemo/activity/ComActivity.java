package com.linyidemo.activity;

import android.widget.ListView;

import com.linyidemo.R;
import com.linyidemo.adapter.TestComAdapter;
import com.linyidemo.appbase.AppBaseActivity;
import com.linyidemo.bean.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/24.
 */

public class ComActivity extends AppBaseActivity {
    @Bind(R.id.lv_com_adapter)
    ListView lvComAdapter;
    private List<User> datasList;
    private TestComAdapter comAdapter;
    private ListView listView;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_com_adapter;
    }

    @Override
    public void initData() {
        super.initData();
        datasList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("jj" + i);
            user.setPhone("10086" + i);
            datasList.add(user);
        }

        comAdapter = new TestComAdapter(this, datasList, R.layout.item_com_adapter);
        listView.setAdapter(comAdapter);
    }



}
