package com.example.duccao.money_hater;

/**
 * Created by hoang on 01/18/2018.
 */

public class Group {
    public long total;
    public String name;

    public Group() {
    }

    public Group(long total, String name) {
        this.total = total;
        this.name = name;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
