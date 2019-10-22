package com.sullivan.util;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class ConnectionFactory {
    private static ConnectionFactory cf = new ConnectionFactory();

    private ConnectionFactory() {
    }

    public static synchronized ConnectionFactory getInstance() {
        if (cf == null) {
            cf = new ConnectionFactory();
        }

        return cf;
    }

    public Connection getConnection() {
        Connection conn = null;

        try {
            Properties prop = new Properties();
            prop.load(new FileReader("database.properties"));
            Class.forName(prop.getProperty("driver"));
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"), prop.getProperty("password"));
        } catch (SQLException var3) {
            var3.printStackTrace();
        } catch (FileNotFoundException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        } catch (ClassNotFoundException var6) {
            var6.printStackTrace();
        }

        return conn;
    }
}
