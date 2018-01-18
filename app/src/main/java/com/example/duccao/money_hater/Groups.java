package com.example.duccao.money_hater;

/**
 * Created by Duc Cao on 1/18/2018.
 */

public class Groups {
    private String Name,Id,Pass;

    public Groups(){

    }

    public Groups(String name, String id, String pass) {
        Name = name;
        Id = id;
        Pass = pass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
