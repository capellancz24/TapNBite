package com.example.tapnbite_original.Data;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class dbConnection {
    protected String db = "dbtapnbite";
    protected String ip = "localhost";
    protected String port = "3306";
    protected String username = "root";
    protected String password = "";

    public Connection CONN (){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionString = "jdbc:mysql://" + ip + ":" + port + "/" + db;
            conn = DriverManager.getConnection(connectionString, username, password);
        } catch (Exception e){
            Log.e("ERROR", Objects.requireNonNull(e.getMessage()));
        }
        return conn;
    }
}
