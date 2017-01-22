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
import android.widget.Toast;

import com.linyidemo.R;
import com.linyidemo.bean.FirstItem;
import com.linyidemo.bean.SecondItem;
import com.linyidemo.bean.ThirdItem;
import com.linyidemo.config.Constants;

import java.util.List;

/**
 * Created by 15596 on 2017/1/19.
 * 第二个适配器
 */

public class ExpandAdapter2 extends BaseExpandableListAdapter {
    private Context context;
    private List<FirstItem> firstList;

    private int cpostion;//父位置
    private int gposition;//子位置
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

        return firstList.get(gposition).getSecondItems().size();
    }



    @Override
    public Object getGroup(int groupPosition) {
        return firstList.get(gposition).getSecondItems().get(groupPosition);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
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
                .get(groupPosition); // 关键步，赋值
        childViewHolder.second_title.setText(secondItem.getTitle());

        /**
         * 展开监听
         * @param groupPosition
         * @return
         */
        treeView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int position) {
                // TODO Auto-generated method stub

                if (treeView.getChildCount() == firstList.get(gposition)
                        .getSecondItems().size()) {
                    if (secondlength > 0) {
                        secondlength += firstList.get(gposition)
                                .getSecondItems().get(position).getThirdItems()
                                .size()
                                * Constants.THIRD_ITEM_HEIGHT;
                    } else {
                        secondlength += firstList.get(gposition)
                                .getSecondItems().size()
                                * Constants.SECOND_ITEM_HEIGHT
                                + firstList.get(gposition).getSecondItems()
                                .get(position).getThirdItems().size()
                                * Constants.THIRD_ITEM_HEIGHT;
                    }
                } else {
                    secondlength += firstList.get(gposition).getSecondItems()
                            .get(position).getThirdItems().size()
                            * Constants.THIRD_ITEM_HEIGHT;
                }
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, secondlength);
                treeView.setLayoutParams(lp);
            }
        });

        /***
         * 缩放监听
         */
        treeView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int position) {
                // TODO Auto-generated method stub

                secondlength -= firstList.get(gposition).getSecondItems()
                        .get(position).getThirdItems().size()
                        * Constants.THIRD_ITEM_HEIGHT;
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT, secondlength);
                treeView.setLayoutParams(lp);
            }
        });
        return convertView;

    }





    @Override
    public int getChildrenCount(int groupPosition) {

        return firstList.get(gposition).getSecondItems().get(groupPosition).getThirdItems().size();
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
        ThirdViewHolder childViewHolder = null;
        if (convertView == null) {
            childViewHolder = new ThirdViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_third, null);
            childViewHolder.third_name = (TextView) convertView
                    .findViewById(R.id.third_name);
            childViewHolder.third_image = (ImageView) convertView
                    .findViewById(R.id.third_image);
            childViewHolder.third_tel = (TextView) convertView
                    .findViewById(R.id.third_tel);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ThirdViewHolder) convertView.getTag();
        }
        ThirdItem thirdItem = firstList.get(gposition).getSecondItems()
                .get(groupPosition).getThirdItems().get(childPosition);//赋值点

        childViewHolder.third_name.setText(thirdItem.getName());
        childViewHolder.third_tel.setText(thirdItem.getTel());
        //获取选中的内容
        treeView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView arg0, View arg1, int groupPosition,
                                        int childPosition, long arg4) {
                // TODO Auto-generated method stub
                String msg = "-ppp--"+gposition+"parent_id = " + groupPosition + " child_id = "
                        + childPosition;
                Toast.makeText(context, msg,
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
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
