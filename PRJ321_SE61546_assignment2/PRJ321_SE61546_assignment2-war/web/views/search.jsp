<%-- 
    Document   : search
    Created on : Sep 22, 2015, 12:30:29 PM
    Author     : Hau
--%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Order</title>
    </head>
    <body>
        <%@include file="welcome.jsp" %>
        
        
        
        <h1>Search Order</h1>
       
        <c:set var="errorObj" value="${requestScope.SEARCHERROROBJ}" />
        <c:set var="txtFromDate" value="${param.txtFromDate}" />
        <c:set var="txtToDate" value="${param.txtToDate}" />
        
        <form action="ProcessServlet" method="GET">
            <table>
                <tr>
                    <td width="80px">From Date:</td>
                    <td width="200px">
                        <c:if test="${empty txtFromDate}" >
                            <input style="width:200px;" type="text" 
                               name="txtFromDate" value="" />
                        </c:if>
                        <c:if test="${not empty txtFromDate}" >
                            <input style="width:200px;" type="text" 
                               name="txtFromDate" value="${txtFromDate}" />
                        </c:if>
                        
                        <c:if test="${not empty errorObj}" >
                            <c:if test="${not empty errorObj.invalidFromDateFormatErr}" >
                                <h4>
                                    <font color="red">
                                    ${errorObj.invalidFromDateFormatErr}
                                    </font>
                                </h4>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td width="80px">To Date:</td>
                    <td width="200px">
                        <c:if test="${empty txtToDate}" >
                            <input style="width:200px;" type="text" 
                               name="txtToDate" value="" />
                        </c:if>
                        <c:if test="${not empty txtToDate}" >
                            <input style="width:200px;" type="text" 
                               name="txtToDate" value="${txtToDate}" />
                        </c:if>
                        
                        <c:if test="${not empty errorObj}" >
                            <c:if test="${not empty errorObj.invalidToDateFormatErr}" >
                                <h4>
                                    <font color="red">
                                    ${errorObj.invalidToDateFormatErr}
                                    </font>
                                </h4>
                            </c:if>
                            
                            <c:if test="${not empty errorObj.toDateEalierThanFromDateErr}" >
                                <h4>
                                    <font color="red">
                                    ${errorObj.toDateEalierThanFromDateErr}
                                    </font>
                                </h4>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td width="80px" ></td>
                    <td>
                        <input type="submit" style="width:49%;"
                               value="Search" name="btAction" />
                        <input type="reset" style="width:49%;" value="Reset" />
                    </td>
                </tr>
            </table>
        </form>
        
        <c:if test="${(not empty txtFromDate) and (not empty txtToDate)}" >
            <%@include file="OrderList.jsp" %>
        </c:if>
        
    </body>
</html>
