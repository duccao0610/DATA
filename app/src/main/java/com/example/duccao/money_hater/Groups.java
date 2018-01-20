package com.example.duccao.money_hater;

/**
 * Created by Duc Cao on 1/18/2018.
 */

public class Groups {
    public long total;
    public String name;

    public Groups() {
    }

    public Groups(long total, String name) {
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
