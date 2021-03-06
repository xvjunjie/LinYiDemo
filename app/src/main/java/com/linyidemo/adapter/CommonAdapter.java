package com.linyidemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.baselibrary.adapter.CommonViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public abstract class CommonAdapter<T> extends BaseAdapter{

    protected Context mContext;
    protected List<T> listDatas;
    protected int layoutId;

    public List<T> getList() {
        return listDatas;
    }

    public void setList(List<T> list) {
        this.listDatas = list;
    }


    public CommonAdapter(Context mContext, List<T> listDatas, int layoutId) {
        this.mContext = mContext;
        this.listDatas = listDatas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return listDatas == null ? 0 : listDatas.size();
    }

    /**
     * 获得对象
     * @param position
     * @return
     */
    @Override
    public T getItem(int position) {
        return listDatas == null ? null : listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.get(mContext ,convertView ,parent ,layoutId ,position);
        setConvertView(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    /**
     *
     * 抽象方法，用于子类实现，填充数据
     */
    protected abstract void setConvertView(CommonViewHolder viewHolder, T bean);

}
