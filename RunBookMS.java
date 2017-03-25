/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment.Assignment4;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author dinhtungtp
 */
public class RunBookMS {
    String dbUrl, userName, password, dbName;
    static Connection connection;
    Statement stmt;

    /**
     * @param args the command line arguments
     */
    
    // Set look and feel
    public static void main(String[] args) {
               
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.out.println("Can not apply Look and Feel Design for Java Program");
        }
               
        // connect to databse
        RunBookMS runBookMS = new RunBookMS();
        runBookMS.connectDatabase();
        
        // initiate login
        new Login().setVisible(true);

    }

    private void connectDatabase() {
        try {
            // Get a connection to database
            dbUrl = "jdbc:mysql://localhost:3307/";
            userName = "root";
            password = "";
            connection = DriverManager.getConnection(dbUrl, userName, password);

            // Create statement
            stmt = connection.createStatement();

            // Check database exits
            dbName = "asm4_tungnd";

            boolean isExist = false;
            ResultSet dbResultSet = connection.getMetaData().getCatalogs();
            while (dbResultSet.next()){
                String database = dbResultSet.getString(1);
                if (database.equals(dbName)){
                    isExist = true;
                    break;
                }
            }
            if (!isExist){
                stmt.execute("CREATE DATABASE " + dbName);
                stmt.execute("USE " + dbName);
                
                // create users table
                stmt.execute("CREATE TABLE users (" +
                        "UserID int AUTO_INCREMENT PRIMARY KEY," +
                        "UserName varchar(255)," +
                        "Password varchar(255)" +
                        ");");

                // create book sytem table
                stmt.execute("CREATE TABLE book_system (" +
                        "Code varchar(255) UNIQUE," +
                        "Title varchar(255)," +
                        "Author varchar(255)," +
                        "Bought int DEFAULT 0," +
                        "Sold int DEFAULT 0" +
                        ");");
                
                // sample book
                stmt.execute("INSERT INTO book_system(Code, Title, Author, Bought, Sold) Values"
                        + "('C1', 'Cuon theo chieu gio', 'Unknown', 5, 3),"
                        + "('C2', 'Co mot pho vua di qua pho', 'Pham Dinh Nguyen', 3, 1);");
            }

            // update database connection
            dbUrl = dbUrl + dbName;
            connection = DriverManager.getConnection(dbUrl, userName, password);

        } catch (SQLException e) {
            System.out.println("My local port has been changed to 3307, usually port for mysql is 3306. Update your username & password as well");
        }
    }
    
}
