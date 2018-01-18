package com.example.duccao.money_hater;

/**
 * Created by hoang on 01/18/2018.
 */

public class Payment {
    public String date, title, uid;
    public long money, gid, gmoney;

    public Payment(String date, String title, String uid, long money, long gid, long gmoney) {
        this.date = date;
        this.title = title;
        this.uid = uid;
        this.money = money;
        this.gid = gid;
        this.gmoney = gmoney;
    }

    public Payment() {
    }

    public long getGmoney() {
        return gmoney;
    }

    public void setGmoney(long gmoney) {
        this.gmoney = gmoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }
}
