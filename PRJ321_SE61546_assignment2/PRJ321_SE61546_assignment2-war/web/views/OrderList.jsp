

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="fromDate" value="${param.txtFromDate}" />
<c:set var="toDate" value="${param.txtToDate}" />

<c:set var="dateFormatter" value="${requestScope.DATEFORMATTER}" />
<c:set var="orderList" value="${requestScope.ORDERLIST}" />

<h1>Order List</h1>

<div id="date" style="width:100%">
    <div  id="from" style="float:left;margin-right:5px">
        From: ${fromDate}
    </div>
    <div style="float:left;" id="to">
        To: ${toDate}
    </div>
</div>
<br />
<h4>Result:</h4>

<c:if test="${empty orderList}" >
    <h3>No result matched.</h3>
</c:if>
<c:if test="${not empty orderList}" >
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>OrderID</th>
                <th>Date</th>
                <th>Customer</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${orderList}" varStatus="counter" >
                <c:url var="detailUrl" 
                       value="/ProcessServlet" >
                    <c:param name="btAction" value="view_detail" />
                    <c:param name="orderID" value="${item.orderID}" />
                    <c:param name="txtFromDate" value="${fromDate}" />
                    <c:param name="txtToDate" value="${toDate}" />
                </c:url>
                <tr>
                    <td>
                        ${counter.count}
                    .</td>
                    <td>
                        ${item.orderID}
                    </td>
                    <td>
                        ${dateFormatter.format(item.orderDate)}
                    </td>
                    <td>
                        ${item.customerID}
                    </td>
                    <td>
                        ${item.total}
                    </td>
                    <td>
                        
                        <a href="${detailUrl}">Detail</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

