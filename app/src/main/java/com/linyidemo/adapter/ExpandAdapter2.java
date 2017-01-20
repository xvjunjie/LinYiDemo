package com.linyidemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.linyidemo.R;
import com.linyidemo.bean.FirstItem;
import com.linyidemo.bean.SecondItem;

import java.util.List;

/**
 * Created by 15596 on 2017/1/19.
 */

public class ExpandAdapter2 extends BaseExpandableListAdapter {
    private Context context;
    private List<FirstItem> firstList;

    private int cpostion;
    private int gposition;
    private ExpandableListView treeView;
    private ExpandableListView.OnChildClickListener stvClickEvent;//外部回调函数
    private int secondlength;

    public ExpandAdapter2(Context context, List<FirstItem> firstList, int cpostion, int gposition, ExpandableListView treeView, ExpandableListView.OnChildClickListener stvClickEvent) {
        this.context = context;
        this.firstList = firstList;
        this.cpostion = cpostion;
        this.gposition = gposition;
        this.treeView = treeView;
        this.stvClickEvent = stvClickEvent;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }



    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }


    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }




    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return firstList.get(gposition).getSecondItems()
                .get(groupPosition).getThirdItems().get(childPosition);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SecondViewHolder childViewHolder = null;
        if (convertView == null) {
            childViewHolder = new SecondViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_second, null);
            childViewHolder.second_title = (TextView) convertView
                    .findViewById(R.id.second_title);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (SecondViewHolder) convertView.getTag();
        }

        SecondItem secondItem = firstList.get(gposition).getSecondItems()
                .get(groupPosition);
        childViewHolder.second_title.setText(secondItem.getTitle());




        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ThirdViewHolder {
        ImageView third_image;
        TextView third_name;
        TextView third_tel;
    }

    class SecondViewHolder {
        TextView second_title;
        ImageView second_arrow;
    }
}
