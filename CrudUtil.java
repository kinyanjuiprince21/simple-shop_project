package com.example.start;

import java.sql.*;

public class CrudUtil {

    public static Connection conn;

    public static Connection getConn() {

        String pass = System.getenv("pass");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class could not be loaded");
        }

        if(conn != null) {
            return conn;
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/shop", "albert", pass);
        } catch (SQLException e) {
            System.out.println("Connection could not be established");
        }
        return conn;
    }

    public static ResultSet executeQuery(String sql, Object... pk) {
        ResultSet rst = null;
        PreparedStatement pst;
        try {
            pst = getConn().prepareStatement(sql);

            if(pk.length > 0) {
                pst.setObject(1, pk[0]);
            }
            rst = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rst;
    }

    public static int executeUpdate(String sql, Object... params) {
        int res = 0;

        try {
            PreparedStatement pst = getConn().prepareStatement(sql);
            for(int i = 0; i < params.length; i++) {
                pst.setObject(i+1, params[i]);
            }
            res = pst.executeUpdate();
            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }
}
