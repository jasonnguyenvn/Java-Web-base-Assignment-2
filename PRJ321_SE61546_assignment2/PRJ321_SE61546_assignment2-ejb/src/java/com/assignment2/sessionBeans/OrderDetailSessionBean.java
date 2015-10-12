/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.sessionBeans;

import com.assignment2.entityBeans.OrderDetail;
import com.assignment2.entityBeans.OrderEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Hau
 */
@Stateless
public class OrderDetailSessionBean 
        implements OrderDetailSessionBeanLocal, OrderDetailSessionBeanRemote {
    @PersistenceContext(unitName = "PRJ321_SE61546_assignment2-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    

    @Override
    public boolean deleteOrderDetail(int id, String orderID, String loginAccID) {
        OrderDetail detailInfo = em.find(OrderDetail.class, id);
        if (detailInfo == null) {
            return false;
        }
        
        try {
            double detailTotal = detailInfo.getTotal();
            System.out.println("Total: " + detailTotal);
            
            em.remove(detailInfo);
            
            String jpqlGetOrder = "SELECT o FROM OrderEntity o WHERE "
                    + "o.orderID = :orderID AND o.customerID = :customerID";
            
            Query query2 = em.createQuery(jpqlGetOrder);
            query2.setParameter("orderID", orderID);
            query2.setParameter("customerID", loginAccID);
            
            OrderEntity order = (OrderEntity) query2.getSingleResult();
            
            double orderTotal = order.getTotal() - detailTotal;
            System.out.println("[log] order total " + orderTotal);
            
            order.setTotal(orderTotal);
            
            em.merge(order);
            
            
        } catch (NoResultException  ex) {
            System.out.println("[log] something wrong! roll back!");
            return false;
        } catch (Exception ex) {
            System.out.println("[log] something wrong! roll back!");
            return false;
        }
        
        return true;
    }

    @Override
    public java.util.List getrDetailsByOrderID(String orderID) {
        Query query = em.createNamedQuery("OrderDetail.findByOrderID");
        query.setParameter("orderID", orderID);
        
        return query.getResultList();
    }

    @Override
    public boolean updateQuantity(int id, String orderID, String loginAccID, int newQuantity) {
        OrderDetail detailInfo = em.find(OrderDetail.class, id);
        if (detailInfo == null) {
            return false;
        }
        
        try {
            double detailTotalBeforeUpdate = detailInfo.getTotal();
            double detailTotal = detailInfo.getUnitPrice() * newQuantity;
            detailInfo.setQuantity(newQuantity);
            detailInfo.setTotal(detailTotal);
            
            String jpqlGetOrder = "SELECT o FROM OrderEntity o WHERE "
                    + "o.orderID = :orderID AND o.customerID = :customerID";
            
            Query query2 = em.createQuery(jpqlGetOrder);
            query2.setParameter("orderID", orderID);
            query2.setParameter("customerID", loginAccID);
            
            OrderEntity order = (OrderEntity) query2.getSingleResult();
            
            double orderTotal = order.getTotal() - detailTotalBeforeUpdate;
            orderTotal += detailTotal;
            
            order.setTotal(orderTotal);
            
            em.merge(detailInfo);
            em.merge(order);
            
            
        } catch (NoResultException  ex) {
            System.out.println("[log] something wrong! roll back!");
            return false;
        } catch (Exception ex) {
            System.out.println("[log] something wrong! roll back!");
            return false;
        }
        
        return true;
    }
    
    
    
}
