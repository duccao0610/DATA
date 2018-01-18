package com.example.duccao.money_hater;

/**
 * Created by Tran Tuan Anh on 1/18/2018.
 */

public class AddMemberItem {
    private int avatar;
    private String name;
    private String email;

    public AddMemberItem(int avatar, String name, String email) {
        this.avatar = avatar;
        this.name = name;
        this.email = email;
    }

    public int getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
