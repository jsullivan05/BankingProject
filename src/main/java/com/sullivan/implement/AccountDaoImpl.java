package com.sullivan.implement;

import com.sullivan.dao.AccountDao;
import com.sullivan.support.Account;
import com.sullivan.support.Transaction;
import com.sullivan.support.User;
import com.sullivan.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    public static ConnectionFactory cf = ConnectionFactory.getInstance();

    public AccountDaoImpl() {
    }

    public void createNewAccount(User nu, double balance, String aname) throws SQLException {
        Connection conn = cf.getConnection();
        String sql = "INSERT INTO ACCOUNT VALUES (NULL, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, nu.getUid());
        ps.setDouble(2, balance);
        ps.setString(3, aname);
        ps.executeUpdate();
    }

    public List<Account> getAllAccounts() throws SQLException {
        List<Account> Accounts = new ArrayList();
        Connection conn = cf.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNT");
        Account s = null;

        while(rs.next()) {
            s = new Account(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4));
            Accounts.add(s);
        }

        return Accounts;
    }

    public List<Account> getAllAccountsSingleUser(User nu) throws SQLException {
        List<Account> Accounts = new ArrayList();
        Connection conn = cf.getConnection();
        String sql = "SELECT * FROM ACCOUNT WHERE userid = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, nu.getUid());
        ResultSet rs = ps.executeQuery();
        Account s = null;

        while(rs.next()) {
            s = new Account(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4));
            Accounts.add(s);
        }

        return Accounts;
    }

    public void deleteEmptyAccounts(User u) throws SQLException {
        Connection conn = cf.getConnection();
        String sql = "DELETE FROM ACCOUNT WHERE userid = ? AND bal = 0";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, u.getUid());
        ps.executeUpdate();
        System.out.println("Account Deleted");
    }

    public double withdrawFromAccount(User u, double w, String aname) throws SQLException {
        Connection conn = cf.getConnection();
        new Transaction();
        List<Account> Accounts = this.getAllAccountsSingleUser(u);
        Account uact = null;
        Iterator var10 = Accounts.iterator();

        while(var10.hasNext()) {
            Account i = (Account)var10.next();
            if (i.getAccname().equals(aname)) {
                uact = i;
            }
        }

        double new_bal = Transaction.withdrawal(uact.getBal(), w);
        System.out.println("Preparing to withdraw $" + w + " from account " + aname);
        String sql = "UPDATE ACCOUNT SET bal = ? WHERE userid = ? AND acc_name = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, new_bal);
        stmt.setInt(2, uact.getUid());
        stmt.setString(3, aname);
        stmt.executeUpdate();
        return new_bal;
    }

    public double depositIntoAccount(User u, double d, String aname) throws SQLException {
        Connection conn = cf.getConnection();
        new Transaction();
        List<Account> Accounts = this.getAllAccountsSingleUser(u);
        Account uact = null;
        Iterator var10 = Accounts.iterator();

        while(var10.hasNext()) {
            Account i = (Account)var10.next();
            if (i.getAccname().equals(aname)) {
                uact = i;
            }
        }

        double new_bal = Transaction.deposit(uact.getBal(), d);
        System.out.println("Preparing to deposit $" + d + " into account " + aname);
        String sql = "UPDATE ACCOUNT SET bal = ? WHERE userid = ? AND acc_name = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, new_bal);
        stmt.setInt(2, uact.getUid());
        stmt.setString(3, aname);
        stmt.executeUpdate();
        return new_bal;
    }
}
