package com.accenture.itfactory.access;

import com.accenture.itfactory.configurations.Config;

import java.sql.*;

public class DatabaseHandler extends Config {

    private static DatabaseHandler instance = null;
    private Connection connection;

    private DatabaseHandler() { }

    public static DatabaseHandler getInstance() {
        if(instance == null){
            synchronized (DatabaseHandler.class) {
                if(instance == null){
                    instance = new DatabaseHandler();
                }
            }
        }
        return instance;
    }

    public Connection getConnection(){
        String connectionString="jdbc:h2:tcp://"+dbHost+"/~/"+dbName;
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  connection;
    }



}
