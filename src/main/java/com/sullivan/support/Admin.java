package com.sullivan.support;

public class Admin {
    private int admin_id;
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(int admin_id, String username, String password) {
        this.admin_id = admin_id;
        this.username = username;
        this.password = password;
    }

    public int getAdmin_id() {
        return this.admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "Admin [admin_id=" + this.admin_id + ", username=" + this.username + ", password=" + this.password + "]";
    }
}
