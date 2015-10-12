/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.sessionBeans;

import javax.ejb.Remote;

/**
 *
 * @author Hau
 */
@Remote
public interface OrderDetailSessionBeanRemote {

    boolean deleteOrderDetail(int id, String orderID, String loginAccID);

    java.util.List getrDetailsByOrderID(String orderID);

    boolean updateQuantity(int id, String orderID, String loginAccID, int newQuantity);
    
}
