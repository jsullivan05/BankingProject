package com.sullivan.implement;

import com.sullivan.dao.UserDao;
import com.sullivan.support.User;
import com.sullivan.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public static ConnectionFactory cf = ConnectionFactory.getInstance();
    Connection conn;

    public UserDaoImpl() {
        this.conn = cf.getConnection();
    }

    public void createUser(String fname, String lname, String uname, String pass) throws SQLException {
        String sql = "INSERT INTO ACCOUNTHOLDER VALUES (NULL, ?, ?, ?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, fname);
        ps.setString(2, lname);
        ps.setString(3, uname);
        ps.setString(4, pass);
        ps.executeUpdate();
    }

    public List<User> getAccountHolders() throws SQLException {
        List<User> AccountHolders = new ArrayList();
        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNTHOLDER");
        User s = null;

        while(rs.next()) {
            s = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            AccountHolders.add(s);
        }

        return AccountHolders;
    }

    public User userLogin(String uname, String pass) throws SQLException {
        User gw = null;
        String sql = "SELECT * FROM ACCOUNTHOLDER WHERE uname=? AND pass=?";
        PreparedStatement statement = this.conn.prepareStatement(sql);
        statement.setString(1, uname);
        statement.setString(2, pass);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            gw = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        }

        return gw;
    }
}
