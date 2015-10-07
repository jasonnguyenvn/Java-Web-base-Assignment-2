/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.sessionBeans;

import com.assignment2.entityBeans.Account;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hau
 */
@Stateless
public class AccountSessionBean 
        implements AccountSessionBeanLocal, AccountSessionBeanRemote {
    @PersistenceContext(unitName = "PRJ321_SE61546_assignment2-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean checkLogin(String username, String password) {
        String jpql = "SELECT a FROM Account a WHERE a.accountID = :username "
                + " AND a.password = :password ";
        
        Query query = em.createQuery(jpql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    @Override
    public boolean createAccount(String accountID, String customerName, 
            String password, String email) {
        Account acc = em.find(Account.class, accountID);
        if (acc != null) {
            return false;
        }
        
        acc = new Account(accountID, customerName, password, email);
        
        em.persist(acc);
        
        return true;
    }
    
    
    
    
}
