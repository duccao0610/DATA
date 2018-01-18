package com.example.duccao.money_hater;

/**
 * Created by hoang on 01/18/2018.
 */

public class User {
    public String mail;
    public long cash;

    public User() {
    }

    public User(String mail, long cash) {
        this.mail = mail;
        this.cash = cash;
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
