package com.sullivan.support;

public class User {
    private int uid;
    private String fname;
    private String lname;
    private String uname;
    private String pw;
    private String type;

    public User() {
    }

    public User(String fname, String lname, String uname, String pw) {
        this.fname = fname;
        this.lname = lname;
        this.uname = uname;
        this.pw = pw;
    }

    public User(int uid, String fname, String lname, String uname, String pw) {
        this.uid = uid;
        this.fname = fname;
        this.lname = lname;
        this.uname = uname;
        this.pw = pw;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPw() {
        return this.pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "User [fname=" + this.fname + ", lname=" + this.lname + ", uname=" + this.uname + ", pw=" + this.pw + "]";
    }
}
