package com.example.daily_khoroch.Model;

public class Khoroch_Model {
    private int amount;
    private String desc;

    public Khoroch_Model(int amount, String desc) {
        this.amount = amount;
        this.desc = desc;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
