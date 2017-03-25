package com.linyidemo.adapter;

import android.content.Context;

import com.android.baselibrary.adapter.CommonViewHolder;
import com.linyidemo.R;
import com.linyidemo.bean.User;

import java.util.List;



/**
 * Created by Administrator on 2017/3/24.
 */

public class TestComAdapter extends CommonAdapter<User> {


    public TestComAdapter(Context mContext, List<User> listDatas, int layoutId) {
        super(mContext, listDatas, layoutId);
    }

    @Override
    protected void setConvertView(CommonViewHolder viewHolder, User bean) {
        viewHolder.setText(R.id.tv_name ,bean.getName());
        viewHolder.setText(R.id.tv_phon ,bean.getPhone());

    }


}
