package com.example.duccao.money_hater;

/**
 * Created by hoang on 01/20/2018.
 */

public class Relation {
    public long gid, spentwith;
    public String uid;

    public Relation(long gid, long spentwith, String uid) {
        this.gid = gid;
        this.spentwith = spentwith;
        this.uid = uid;
    }

    public Relation() {
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public long getSpentwith() {
        return spentwith;
    }

    public void setSpentwith(long spentwith) {
        this.spentwith = spentwith;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
