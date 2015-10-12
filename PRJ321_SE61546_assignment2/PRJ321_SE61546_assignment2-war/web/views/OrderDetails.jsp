<%-- 
    Document   : OrderDetails
    Created on : Sep 22, 2015, 1:07:50 PM
    Author     : Hau
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Details</title>
    </head>
    <body>
        <%@include file="welcome.jsp" %>
        
        <c:set var="errorObj" value="${requestScope.ERROROBJ}" />
        <c:if test="${not empty errorObj}" >
            <c:if test="${not empty errorObj.couldNotDeleteOrderDetail}" >
                <h1>
                    <font color="red">
                    ${errorObj.couldNotDeleteOrderDetail}
                    </font>
                </h1>
            </c:if>
        </c:if>
        
        <c:set var="updateErrorObj" value="${requestScope.UPDATEERROROBJ}" />
        <c:if test="${not empty updateErrorObj}" >
             <c:if test="${not empty updateErrorObj.couldNotUpdateErr}" >
                <h1>
                    <font color="red">
                    ${updateErrorObj.couldNotUpdateErr}
                    </font>
                </h1>
            </c:if>
             
             <c:if test="${not empty updateErrorObj.invalidQuantityValueErr}" >
                <h1>
                    <font color="red">
                    ${updateErrorObj.invalidQuantityValueErr}
                    </font>
                </h1>
            </c:if>
             
             <c:if test="${not empty updateErrorObj.quantityLessThanOneErr}" >
                <h1>
                    <font color="red">
                    ${updateErrorObj.quantityLessThanOneErr}
                    </font>
                </h1>
            </c:if>
        </c:if>
        
        <c:set var="order" value="${requestScope.ORDEROBJ}" />
        
        <h1>Order Details</h1>
        
        <table  >
            <tr>
                <td>Order ID</td>
                <td>
                    ${order.orderID}
                </td>
                
                <td>Date</td>
                <td>
                    ${order.orderDate}
                </td>
            </tr>
            <tr>
                <c:set var="orderCust" value="${requestScope.ORDERCUST}" />
                <td>Customer</td>
                <td>
                    ${orderCust.customerName}
                </td>
                
                <td>Phone</td>
                <td>
                    ${order.phone}
                </td>
            </tr>
            <tr>
                <td>Address</td>
                <td colspan="3">
                    ${order.address}
                </td>
            </tr>
        </table>


        <c:set var="itemList" value="${requestScope.ITEMLIST}" />
        <h4>Detail</h4>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Product</th>
                    <th>Unit</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${itemList}" varStatus="counter" >     
                        <c:url var="deleteUrl" value="/ProcessServlet" >
                            <c:param name="btAction" value="del_detail" />
                            <c:param name="orderID" value="${order.orderID}" />
                            <c:param name="ID" value="${item.id}" />
                            <c:param name="txtFromDate" value="${param.txtFromDate}" />
                            <c:param name="txtToDate" value="${param.txtToDate}" />
                        </c:url>
                         <form action="ProcessServlet" method="POST">
                        <tr>
                            <td>
                               ${counter.count}
                            .</td>
                            <td>
                                ${item.productID}
                            </td>
                            <td>
                                ${item.unitItem}
                            </td>
                            <td>
                                <input type="text" name="txtQuantity" 
                                       value="${item.quantity}" />
                            </td>
                            <td>
                                ${item.unitPrice}
                            </td>
                            <td>
                                ${item.total}
                            </td>
                            <td>
                                <input type="hidden" name="orderID" 
                                       value="${order.orderID}" />
                                <input type="hidden" name="ID" 
                                       value="${item.id}" />
                                <input type="hidden" name="txtFromDate" 
                                       value="${param.txtFromDate}" />
                                <input type="hidden" name="txtToDate" 
                                       value="${param.txtToDate}" />
                                <input type="submit" value="Update" name="btAction" />
                            </td>
                            <td align="center" >
                                <a href="${deleteUrl}">X</a>
                            </td>
                        </tr>
                         </form>
                </c:forEach>       
                   
                
                
            </tbody>
        </table>
        
        <c:url var="searchUrlRewriting" value="/ProcessServlet" >
            <c:param name="btAction" value="Search" />
            <c:param name="txtFromDate" value="${param.txtFromDate}" />
            <c:param name="txtToDate" value="${param.txtToDate}" />
        </c:url>
        
        <h4>Total: ${order.total}</h4>
        
        <h3><a href="${searchUrlRewriting}">Click here to return search page</a></h3>

        <!--
            REMEMBER TO ADD LINK HERE
        -->
    </body>
</html>
