/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.sales;

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
public class ProductDAO implements Serializable {
    public ProductDTO getProductById(String productID, DataSource ds) 
            throws SQLException {
        ProductDTO result = null;
        
        
        Connection con = ds.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM tbl_product WHERE productID=? ";
        
        try {
            stm = con.prepareCall(sql);
            stm.setString(1, productID);
            
            rs = stm.executeQuery();
            
            if(rs.next()) {
                String productName = rs.getString("productName");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                
                ProductDTO product = new ProductDTO(productID, productName, 
                                            price, quantity);
                
                result = product;             
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
