/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.sessionBeans;

import com.assignment2.entityBeans.Account;
import javax.ejb.Local;

/**
 *
 * @author Hau
 */
@Local
public interface AccountSessionBeanLocal {

    boolean checkLogin(String username, String password);

    boolean createAccount(String accountID, String customerName, String password, String email);

    Account getAccountByAccountID(String accountID);
    
}
