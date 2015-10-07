/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assignment2.entityBeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau
 */
@Entity
@Table(name = "tbl_orderDetail", catalog = "PRJ321_Assignment", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"productID", "orderID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetail.findAll", query = "SELECT o FROM OrderDetail o"),
    @NamedQuery(name = "OrderDetail.findById", query = "SELECT o FROM OrderDetail o WHERE o.id = :id"),
    @NamedQuery(name = "OrderDetail.findByProductID", query = "SELECT o FROM OrderDetail o WHERE o.productID = :productID"),
    @NamedQuery(name = "OrderDetail.findByQuantity", query = "SELECT o FROM OrderDetail o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "OrderDetail.findByUnitPrice", query = "SELECT o FROM OrderDetail o WHERE o.unitPrice = :unitPrice"),
    @NamedQuery(name = "OrderDetail.findByUnitItem", query = "SELECT o FROM OrderDetail o WHERE o.unitItem = :unitItem"),
    @NamedQuery(name = "OrderDetail.findByTotal", query = "SELECT o FROM OrderDetail o WHERE o.total = :total"),
    @NamedQuery(name = "OrderDetail.findByOrderID", query = "SELECT o FROM OrderDetail o WHERE o.orderID = :orderID")})
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "productID", nullable = false, length = 10)
    private String productID;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unitPrice", precision = 53)
    private Double unitPrice;
    @Basic(optional = false)
    @Column(name = "unitItem", nullable = false, length = 10)
    private String unitItem;
    @Column(name = "total", precision = 53)
    private Double total;
    @Basic(optional = false)
    @Column(name = "orderID", nullable = false, length = 10)
    private String orderID;

    public OrderDetail() {
    }

    public OrderDetail(Integer id) {
        this.id = id;
    }

    public OrderDetail(Integer id, String productID, int quantity, String unitItem, String orderID) {
        this.id = id;
        this.productID = productID;
        this.quantity = quantity;
        this.unitItem = unitItem;
        this.orderID = orderID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitItem() {
        return unitItem;
    }

    public void setUnitItem(String unitItem) {
        this.unitItem = unitItem;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetail)) {
            return false;
        }
        OrderDetail other = (OrderDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.assignment2.entityBeans.OrderDetail[ id=" + id + " ]";
    }
    
}
