package com.e.demoaplplication.bean;

import androidx.annotation.NonNull;

public class FavoriteList {
    private  String itemImage;
    private String itemName;
    private String itemLogin;
    private String favstatus;

    public String getItemImage() {
        return itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemLogin() {
        return itemLogin;
    }

    public String getFavstatus() {
        return favstatus;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemLogin(String itemLogin) {
        this.itemLogin = itemLogin;
    }

    public void setFavstatus(String favstatus) {
        this.favstatus = favstatus;
    }
}
