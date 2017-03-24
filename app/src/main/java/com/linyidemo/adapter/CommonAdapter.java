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

    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 数据源
     */
    protected List<T> listDatas;
    /**
     * Item布局ID
     */
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
        CommonViewHolder holder = CommonViewHolder.get(mContext ,convertView ,parent ,layoutId ,position);
        setConverView(holder, getItem(position));
        return holder.getConvertView();
    }

    /**
     *
     * 抽象方法，用于子类实现，填充数据
     * @param holder
     */
    protected abstract void setConverView(CommonViewHolder holder, T bean);

}
