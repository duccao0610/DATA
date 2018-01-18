package com.example.duccao.money_hater;

import android.widget.ImageView;

/**
 * Created by Tran Tuan Anh on 1/17/2018.
 */

public class MainScreenItem {
    private int avatar;
    private String time;
    private String content;
    private String personalConsume;
    private String groupConsume;
    private boolean out;
    private int numberOfPeople;

    public MainScreenItem(int avatar, String time, String content, String personalConsume, String groupConsume, boolean out, int numberOfPeople) {
        this.avatar = avatar;
        this.time = time;
        this.content = content;
        this.personalConsume = personalConsume;
        this.groupConsume = groupConsume;
        this.out = out;
        this.numberOfPeople = numberOfPeople;
    }

    public int getAvatar() {
        return avatar;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getPersonalConsume() {
        return personalConsume;
    }

    public String getGroupConsume() {
        return groupConsume;
    }

    public boolean isOut() {
        return out;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }
}
