/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server.model;

import java.sql.PreparedStatement;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.PrintWriter;

/**
 *
 * @author kuuga
 */
public class User {

    private PrintWriter out = null;
    private Connection connection = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs;
    private String result;
    private String query;

    public User() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/localserver","root","milofa");

        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    public String swipeCardSql(String cardId) {
        try {

            query = "SELECT user_name, user_card_no FROM user where user_card_no = ?";
            pstmt = connection.prepareStatement(query); // create a statement
            pstmt.setString(1, cardId);
            rs = pstmt.executeQuery();
            if (rs.next() == false) {
                System.out.println(": : : NOT FOUND ON DB:.");
                System.out.println(": : : USER WITH CARD NOT FOUND OR NOT REGISTERED:.");
                result = "01|USER WITH CARD NOT FOUND";
            } else {
                System.out.println(": : : USER FOUND, PREPARE TO GIVE RESULT TO CLIENT:.");
                System.out.println(": : : NAME USER              [" + rs.getString(1) + "]");
                result = "00|" + rs.getString(1);
            }
            connection.close();
            return result;

        } catch (Throwable e) {
            e.printStackTrace();
            result = "UE|UNEXPECTED ERR0R";
            return result;
        }
    }

    public String fingerPrintSql(String fingerId) {
        try {
            query = "SELECT user_name, user_finger_print_id FROM user where user_finger_print_id = ?";
            pstmt = connection.prepareStatement(query); // create a statement
            pstmt.setString(1, fingerId);
            rs = pstmt.executeQuery();
            if (rs.next() == false) {
                System.out.println(": : : NOT FOUND ON DB:.");
                System.out.println(": : : FINGERPRINT NOT MATCH IN DB, GIVE RESULT TO RETRY:.");
                result="01|FINGERPRINT NOT MATCH";
            } else {
                System.out.println(": : : USER FOUND, PREPARE TO GIVE RESULT TO CLIENT:.");
                System.out.println(": : : NAME USER              [" + rs.getString(1) + "]");
                result = "00|" + rs.getString(1);
            }
            connection.close();
            return result;

        } catch (Throwable e) {
            e.printStackTrace();
            result = "UE|UNEXPECTED ERR0R";
            return result;
        }
    }
}
