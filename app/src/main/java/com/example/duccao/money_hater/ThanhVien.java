package com.example.duccao.money_hater;

/**
 * Created by VuDuc on 18-Jan-18.
 */

public class ThanhVien {
    private int anh;
    private String ten;
    private boolean checked = false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public ThanhVien(int anh, String ten) {
        this.anh = anh;
        this.ten = ten;
    }

    public int getAnh() {
        return anh;
    }

    public String getTen() {
        return ten;
    }
}
