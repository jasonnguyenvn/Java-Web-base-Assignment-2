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
public interface AccountSessionBeanRemote {

    boolean checkLogin(String username, String password);

    boolean createAccount(String accountID, String customerName, String password, String email);
    
}
