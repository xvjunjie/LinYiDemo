package com.linyidemo.bean;

import java.util.List;

/**
 * Created by 15596 on 2017/1/18.
 * 二级列表实体类
 *
 */

public class SecondItem {
    private int id;
    private String title;
    private List<ThirdItem> thirdItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ThirdItem> getThirdItems() {
        return thirdItems;
    }

    public void setThirdItems(List<ThirdItem> thirdItems) {
        this.thirdItems = thirdItems;
    }
}
