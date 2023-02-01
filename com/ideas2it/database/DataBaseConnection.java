package com.ideas2it.database;

import java.lang.ClassNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
* DataBaseConnection class connect EmployeeDao with MySQL  
*
* @version 1.0 01-09-2022
*
* @author Rohit A P 
*/
public class DataBaseConnection {

    private static DataBaseConnection dataBaseConnection; 

    private DataBaseConnection() {
    }

    /**
     * 
     * get connction with MYSQL
     * 
     * @param 
     * @return connection 
     */      
    public static Connection getConnection()throws ClassNotFoundException, SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/EMPLOYEE_DATABASE", "root", "12345");
        return connection;
    }

    /**
     * 
     * create object for class DataBaseConnection
     * 
     * @param 
     * @return connection 
     */      
    public static DataBaseConnection getInstance() {
	
        if (dataBaseConnection == null) {
            dataBaseConnection = new DataBaseConnection();
	}
	return dataBaseConnection;
    }
}