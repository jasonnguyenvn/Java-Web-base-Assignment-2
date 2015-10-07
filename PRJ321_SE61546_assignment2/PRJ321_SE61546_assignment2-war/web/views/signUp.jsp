<%-- 
    Document   : signUp
    Created on : Sep 24, 2015, 9:02:08 PM
    Author     : Hau
--%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign Up a new account</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
    </head>
    <body>
        <h1>Sign Up a new account</h1>
        
        <c:set var="errorObj" value="${ERROROBJ}" />
        <c:if test="${not empty errorObj}" >
            <c:if test="${not empty errorObj.usernameEmailExistedErr}" >
                <h3>
                    <font color="red" >
                        ${errorObj.usernameEmailExistedErr}
                    </font>
                </h3>
            </c:if>
        </c:if>
        <form action="ProcessLogin" method="POST">
            <table>
                <tr>
                    <td width="80px">Username:</td>
                    <td width="200px">
                        <c:if test="${empty param.accountID}" >
                            <input style="width:200px;" type="text" 
                               name="accountID" value="" />
                        </c:if>
                        <c:if test="${not empty param.accountID}" >
                            <input style="width:200px;" type="text" 
                               name="accountID" value="${param.accountID}" />
                        </c:if>
                        
                        (3 - 10 characters)
                        <c:if test="${not empty errorObj}" >
                            <c:if test="${not empty errorObj.usernameLengthErr}" >
                                <h3>
                                    <font color="red" >
                                        ${errorObj.usernameLengthErr}
                                    </font>
                                </h3>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td width="80px">Full name:</td>
                    <td width="200px">
                        <c:if test="${empty param.customerName}">
                            <input style="width:200px;" type="text" 
                               name="customerName" value="" />
                        </c:if>
                        <c:if test="${not empty param.customerName}">
                            <input style="width:200px;" type="text" 
                               name="customerName" value="${param.customerName}" />
                        </c:if>
                        
                        (3 - 50 characters)
                        <c:if test="${not empty errorObj}" >
                            <c:if test="${not empty errorObj.customerNameLengthErr}" >
                                <h3>
                                    <font color="red" >
                                        ${errorObj.customerNameLengthErr}
                                    </font>
                                </h3>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td width="80px">Password:</td>
                    <td width="200px">
                        <input style="width:200px;" type="password" 
                               name="password" value="" />
                        (6 - 20 characters)
                        <c:if test="${not empty errorObj}" >
                            <c:if test="${not empty errorObj.passwordLengthErr}" >
                                <h3>
                                    <font color="red" >
                                        ${errorObj.passwordLengthErr}
                                    </font>
                                </h3>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td width="80px">Confirm:</td>
                    <td width="200px">
                        <input style="width:200px;" type="password"
                               name="txtConfirm" value="" />
                        <c:if test="${not empty errorObj}" >
                            <c:if test="${not empty errorObj.confimNotMatchedErr}" >
                                <h3>
                                    <font color="red" >
                                        ${errorObj.confimNotMatchedErr}
                                    </font>
                                </h3>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td width="80px">Email:</td>
                    <td width="200px">
                        <c:if test="${empty param.email}">
                            <input style="width:200px;" type="text"
                               name="email" value="" />
                        </c:if>
                        <c:if test="${not empty param.email}">
                            <input style="width:200px;" type="text"
                               name="email" value="${param.email}" />
                        </c:if>
                        
                        (max 50 characters)
                        <c:if test="${not empty errorObj}" >
                            <c:if test="${not empty errorObj.emailLengthErr}" >
                                <h3>
                                    <font color="red" >
                                        ${errorObj.emailLengthErr}
                                    </font>
                                </h3>
                            </c:if>
                            
                            <c:if test="${not empty errorObj.emailLengthErr}" >
                                <h3>
                                    <font color="red" >
                                        ${errorObj.invalidEmailErr}
                                    </font>
                                </h3>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
                
                <tr>
                    <td width="80px" ></td>
                    <td>
                        <input type="submit" style="width:49%;" 
                               value="Sign Up!" name="btAction" />
                        <input type="reset" style="width:49%;" value="Reset" />
                    </td>
                </tr>
                
            </table>
        </form>
    </body>
</html>

