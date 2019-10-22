package com.sullivan.support;

public class Account {
    private int aid;
    private int uid;
    private double bal;
    private String accname;

    public Account(int aid, int uid, double bal, String accname) {
        this.aid = aid;
        this.uid = uid;
        this.bal = bal;
        this.accname = accname;
    }

    public int getAid() {
        return this.aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getBal() {
        return this.bal;
    }

    public void setBal(double bal) {
        this.bal = bal;
    }

    public String getAccname() {
        return this.accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String toString() {
        return "Account [aid=" + this.aid + ", uid=" + this.uid + ", bal=" + this.bal + ", accname= " + this.accname + "]";
    }
}
