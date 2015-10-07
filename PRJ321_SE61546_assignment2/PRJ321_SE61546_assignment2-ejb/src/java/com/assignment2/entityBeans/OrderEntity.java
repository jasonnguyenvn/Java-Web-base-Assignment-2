/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.entityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau
 */
@Entity
@Table(name = "tbl_order", catalog = "PRJ321_Assignment", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderEntity.findAll", query = "SELECT o FROM OrderEntity o"),
    @NamedQuery(name = "OrderEntity.findByOrderID", query = "SELECT o FROM OrderEntity o WHERE o.orderID = :orderID"),
    @NamedQuery(name = "OrderEntity.findByOrderDate", query = "SELECT o FROM OrderEntity o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "OrderEntity.findByCustomerID", query = "SELECT o FROM OrderEntity o WHERE o.customerID = :customerID"),
    @NamedQuery(name = "OrderEntity.findByTotal", query = "SELECT o FROM OrderEntity o WHERE o.total = :total"),
    @NamedQuery(name = "OrderEntity.findByAddress", query = "SELECT o FROM OrderEntity o WHERE o.address = :address"),
    @NamedQuery(name = "OrderEntity.findByPhone", query = "SELECT o FROM OrderEntity o WHERE o.phone = :phone")})
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "orderID", nullable = false, length = 10)
    private String orderID;
    @Basic(optional = false)
    @Column(name = "orderDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Basic(optional = false)
    @Column(name = "customerID", nullable = false, length = 10)
    private String customerID;
    @Basic(optional = false)
    @Column(name = "total", nullable = false)
    private double total;
    @Basic(optional = false)
    @Column(name = "address", nullable = false, length = 250)
    private String address;
    @Basic(optional = false)
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    public OrderEntity() {
    }

    public OrderEntity(String orderID) {
        this.orderID = orderID;
    }

    public OrderEntity(String orderID, Date orderDate, String customerID, double total, String address, String phone) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.total = total;
        this.address = address;
        this.phone = phone;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderID != null ? orderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderEntity)) {
            return false;
        }
        OrderEntity other = (OrderEntity) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.assignment2.entityBeans.OrderEntity[ orderID=" + orderID + " ]";
    }
    
}
