/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Hau
 */
public class AccountDAO implements Serializable {
    
    public boolean createAccount(String accountID, String customerName,
            String password, String email, DataSource ds) 
            throws  SQLException {
        boolean result = false;
        Connection con = ds.getConnection();
        PreparedStatement stm = null;
        
        String sql = "INSERT INTO tbl_account(accountID, customerName, password,"
                        + " email) VALUES(?, ?, ?, ?) ";
        
        try {
            stm = con.prepareCall(sql);
            stm.setString(1, accountID);
            stm.setString(2, customerName);
            stm.setString(3, password);
            stm.setString(4, email);
            
            int rs = stm.executeUpdate();
            if (rs > 0) {
                result = true;
            }
            
        } finally {
            if (stm!=null) {
                stm.close();
            }
            
            con.close();
        }
        
        return result;
    }
    
    public AccountDTO checkLogin(String accountID, String password, DataSource ds) 
            throws  SQLException {
        AccountDTO result = null;
        Connection con = ds.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM tbl_account WHERE accountID=? "
                + "AND password=? ";
        
        try {
            stm = con.prepareCall(sql);
            stm.setString(1, accountID);
            stm.setString(2, password);
            
            rs = stm.executeQuery();
            
            if(rs.next()) {
                String customerName = rs.getString("customerName");
                String email = rs.getString("email");
                
                AccountDTO acc = new AccountDTO(accountID, customerName, 
                        password, email);
                result = acc;             
            }
            
        } finally {
            if (rs!=null) {
                rs.close();
            }
            if (stm!=null) {
                stm.close();
            }
            
            con.close();
        }
        
        return result;
    }
    
    public AccountDTO getAccountById(String accountID, DataSource ds) 
            throws SQLException {
        AccountDTO result = null;
        Connection con = ds.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM tbl_account WHERE accountID=? ";
        
        try {
            stm = con.prepareCall(sql);
            stm.setString(1, accountID);
            
            rs = stm.executeQuery();
            
            if(rs.next()) {
                String customerName = rs.getString("customerName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                
                AccountDTO acc = new AccountDTO(accountID, customerName, 
                        password, email);
                result = acc;             
            }
            
        } finally {
            if (rs!=null) {
                rs.close();
            }
            if (stm!=null) {
                stm.close();
            }
            
            con.close();
        }
        
        return result;
    }
}
