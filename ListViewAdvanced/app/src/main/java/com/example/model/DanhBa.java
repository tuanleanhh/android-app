package com.example.model;

import java.io.Serializable;

public class DanhBa implements Serializable {
    private int ma;
    private String name;
    private String phoneNumber;

    public DanhBa() {
    }

    public DanhBa(int ma, String name, String phoneNumber) {
        this.ma = ma;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
