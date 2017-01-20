package com.linyidemo.bean;

import java.util.List;

/**
 * Created by 15596 on 2017/1/18.
 * 一级列表实体类
 *
 */

public class FirstItem {
    private int id;
    private String title;
    private String image;
    private List<SecondItem> secondItems;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<SecondItem> getSecondItems() {
        return secondItems;
    }

    public void setSecondItems(List<SecondItem> secondItems) {
        this.secondItems = secondItems;
    }
}
