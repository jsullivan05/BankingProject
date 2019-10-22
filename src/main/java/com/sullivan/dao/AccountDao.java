package com.sullivan.dao;

import com.sullivan.support.Account;
import com.sullivan.support.User;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao {
    void createNewAccount(User var1, double var2, String var4) throws SQLException;

    List<Account> getAllAccounts() throws SQLException;

    List<Account> getAllAccountsSingleUser(User var1) throws SQLException;

    void deleteEmptyAccounts(User var1) throws SQLException;

    double withdrawFromAccount(User var1, double var2, String var4) throws SQLException;

    double depositIntoAccount(User var1, double var2, String var4) throws SQLException;
}
