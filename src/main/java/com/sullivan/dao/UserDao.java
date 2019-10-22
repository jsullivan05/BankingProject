package com.sullivan.dao;

import com.sullivan.support.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void createUser(String var1, String var2, String var3, String var4) throws SQLException;

    List<User> getAccountHolders() throws SQLException;

    User userLogin(String var1, String var2) throws SQLException;
}
