package com.sullivan.dao;

import com.sullivan.support.Admin;
import com.sullivan.support.User;

import java.sql.SQLException;

public interface AdminDao {
    void deleteAccount(User var1) throws SQLException;

    void deleteAllUsers() throws SQLException;

    void deleteAllAccounts() throws SQLException;

    Admin adminLogin(String var1, String var2) throws SQLException;
}