<%-- 
    Document   : login
    Created on : Sep 22, 2015, 12:29:56 PM
    Author     : Hau
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.assignment2.account.AccountLoginError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page - occurs errors</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
    </head>
    <body>
        <h1>Login Page - occurs errors</h1>
        
        <c:set var="errorObj" value="${requestScope.ERROROBJ}" />
        <c:if test="${not empty errorObj}" >
            <c:if test="${not empty errorObj.invalidUsernamePasswordErr}" >
                <h3> <font color="red">
                    ${errorObj.invalidUsernamePasswordErr}
                </font></h3>
            </c:if>
            
            <c:if test="${not empty errorObj.nullPointerErr}" >
                <h3> <font color="red">
                    ${errorObj.nullPointerErr}
                </font></h3>
            </c:if>
            
            <c:url var="urlRewriting" value="ProcessServlet" >
                <c:param name="btAction" value="signUp" />
            </c:url>
            <h4>
                <a href="${urlRewriting}">
                    Click here to Sign up a new account</a>
                <br/>
                or Try again:
            </h4>
        </c:if>
        
        
        <form action="ProcessServlet" method="POST">
            <table>
                <tr>
                    <td width="80px">Username:</td>
                    <td width="200px">
                        <c:if test="${not empty param.accountID}">
                            <input style="width:200px;" type="text" name="accountID" 
                               value="${param.accountID}" />
                        </c:if>
                        <c:if test="${empty param.accountID}">
                            <input style="width:200px;" type="text" name="accountID" 
                                value="" />
                        </c:if>
                            
                        <c:if test="${not empty errorObj.nullUsernameErr}" >
                            <h3> <font color="red">
                                ${errorObj.nullUsernameErr}
                            </font></h3>
                        </c:if>
                        
                    </td>
                </tr>
                <tr>
                    <td width="80px">Password:</td>
                    <td width="200px">
                        <input style="width:200px;" type="password" 
                               name="password" value="" />
                        <c:if test="${not empty errorObj.nullPasswordErr}" >
                            <h3> <font color="red">
                                ${errorObj.nullPasswordErr}
                            </font></h3>
                        </c:if>
                        
                    </td>
                </tr>
                <tr>
                    <td width="80px" ></td>
                    <td>
                        <input type="submit" style="width:49%;" value="Login" 
                               name="btAction" />
                        <input type="reset" style="width:49%;" value="Reset" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
