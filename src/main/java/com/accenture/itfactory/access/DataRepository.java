package com.accenture.itfactory.access;

import java.sql.*;
import java.util.ArrayList;

public class DataRepository {

    private static DataRepository instance = null;

    private DataRepository() { }

    public static DataRepository getInstance() {
        if(instance == null){
            synchronized (DataRepository.class) {
                if(instance == null){
                    instance = new DataRepository();
                }
            }
        }
        return instance;
    }

    public int getId(String tableName){
        Connection con=DatabaseHandler.getInstance().getConnection();
        Statement stmt = null;
        ResultSet rs;
        int id=0;
        try {
            stmt = con.createStatement();
            con.setAutoCommit(false);
            rs = stmt.executeQuery("select * from "+tableName);
            if(rs.last()){
                id=rs.getInt(1);
                id++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
        return id;
    }

    public boolean request(String sql){
        Connection con=DatabaseHandler.getInstance().getConnection();
        Statement stmt = null;
        boolean complete=false;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            complete=true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
        return complete;
    }

    public ArrayList<String> select(String sql){
        Connection con = DatabaseHandler.getInstance().getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd;
        ArrayList<String> list = new ArrayList<String>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rsmd = rs.getMetaData();
            while (rs.next()){
                String str="";
                for(int i=1; i<=rsmd.getColumnCount();i++){
                    str=str+rs.getString(i)+" ";
                }
                list.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
        return list;
    }

    public void update(String sql){
        Connection con = DatabaseHandler.getInstance().getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try{ if (con != null) con.close(); } catch (Exception e) {};
        }
    }

}
