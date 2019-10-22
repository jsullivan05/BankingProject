package com.sullivan.implement;

import com.sullivan.dao.AdminDao;
import com.sullivan.support.Admin;
import com.sullivan.support.User;
import com.sullivan.util.ConnectionFactory;

import java.sql.*;

public class AdminDaoImpl implements AdminDao {
    public static ConnectionFactory cf = ConnectionFactory.getInstance();
    Connection conn;

    public AdminDaoImpl() {
        this.conn = cf.getConnection();
    }

    public void deleteAccount(User gone) throws SQLException {
        String sql = "DELETE FROM ACCOUNT WHERE userid = ?";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, gone.getUid());
        ps.executeUpdate();
        System.out.println("Account Deleted");
    }

    public void deleteAllUsers() throws SQLException {
        this.deleteAllAccounts();
        String sql = "DELETE FROM ACCOUNTHOLDER";
        Statement ps = this.conn.createStatement();
        ps.executeQuery(sql);
        System.out.println("It's all gone!");
    }

    public void deleteAllAccounts() throws SQLException {
        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate("TRUNCATE TABLE ACCOUNT");
        System.out.println("Accounts Deleted");
    }

    public Admin adminLogin(String uname, String pass) throws SQLException {
        Admin gw = null;
        String sql = "SELECT * FROM ADMINISTRATOR WHERE aduname=? AND adpass=?";
        PreparedStatement statement = this.conn.prepareStatement(sql);
        statement.setString(1, uname);
        statement.setString(2, pass);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            gw = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3));
        }

        return gw;
    }
}
