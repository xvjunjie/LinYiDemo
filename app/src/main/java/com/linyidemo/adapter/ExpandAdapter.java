package com.linyidemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.linyidemo.R;
import com.linyidemo.bean.FirstItem;
import com.linyidemo.config.Constants;

import java.util.List;

/**
 * Created by 15596 on 2017/1/18.
 * 第一个适配器
 */

public class ExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<FirstItem> firstList;
    private ExpandableListView.OnChildClickListener stvClickEvent;//外部回调函数

    public ExpandAdapter(Context context, List<FirstItem> firstList, ExpandableListView.OnChildClickListener stvClickEvent) {
        this.context = context;
        this.firstList = firstList;
        this.stvClickEvent = stvClickEvent;
    }

    /**
     * 父部分
     * @return
     */
    @Override
    public int getGroupCount() {
        return firstList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return firstList.get(groupPosition);
    }
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        FirstHolder holder = null;
        if (convertView == null) {
            holder = new FirstHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_first, null);
            holder.first_arrow = (ImageView) convertView
                    .findViewById(R.id.first_arrow);
            holder.first_image = (ImageView) convertView
                    .findViewById(R.id.first_image);
            holder.first_title = (TextView) convertView
                    .findViewById(R.id.first_title);
            convertView.setTag(holder);
        } else {
            holder = (FirstHolder) convertView.getTag();
        }
        FirstItem firstItem = firstList.get(groupPosition);
        holder.first_title.setText(firstItem.getTitle());
        return convertView;
    }


    /**
     * 子部分
     * @param groupPosition
     * @return
     */

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return firstList.get(groupPosition).getSecondItems();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ExpandableListView treeView = getExpandableListView(firstList.get(groupPosition).getSecondItems().size());
        ExpandAdapter2 expandAdapter2 = new ExpandAdapter2(context, firstList,
                childPosition, groupPosition,treeView,stvClickEvent);
        //treeView.setOnChildClickListener(stvClickEvent);
        treeView.setAdapter(expandAdapter2);//注意点

        return treeView;
    }


    //设置布局参数
    public ExpandableListView getExpandableListView(int position) {
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, Constants.SECOND_ITEM_HEIGHT * position);
        ExpandableListView superTreeView = new ExpandableListView(context);
        superTreeView.setLayoutParams(params);
        return superTreeView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }



    class FirstHolder {
        TextView first_title;
        ImageView first_image;
        ImageView first_arrow;
    }

    class SecondHolder {
        TextView second_title;
        ImageView second_arrow;
    }
}
