/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.sessionBeans;

import com.assignment2.entityBeans.OrderEntity;
import java.util.Date;
import java.util.List;
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
public class OrderSessionBean 
        implements OrderSessionBeanLocal, OrderSessionBeanRemote {
    @PersistenceContext(unitName = "PRJ321_SE61546_assignment2-ejbPU")
    private EntityManager em;

    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    @Override
    public List searchFromDateToDate(Date fromDate, Date toDate) {
        String jpql = "SELECT o FROM OrderEntity o WHERE o.orderDate >= :fromDate "
                + " AND o.orderDate <= :toDate ";
        
        Query query = em.createQuery(jpql);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        
        List result = query.getResultList();
        
        return result;
    }

    @Override
    public OrderEntity getOrderByIDAndCustID(String orderID, String customerID) {
        String jpql = "SELECT o FROM OrderEntity o WHERE "
                + " o.orderID = :orderID AND  o.customerID = :customerID";
        Query query = em.createQuery(jpql);
        query.setParameter("orderID", orderID);
        query.setParameter("customerID", customerID);
        
        OrderEntity result;
        try {
            result = (OrderEntity) query.getSingleResult();
        } catch (NoResultException ex) {
            result = null;
        }
        
        return result;
    }
    
    
    
    
}
