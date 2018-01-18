package com.example.duccao.money_hater;

/**
 * Created by hoang on 01/18/2018.
 */

public class User {
    public long cash;
    public String mail;

    public User(long cash, String mail) {
        this.cash = cash;
        this.mail = mail;
    }

    public User() {
    }

    public long getCash() {
        return cash;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
