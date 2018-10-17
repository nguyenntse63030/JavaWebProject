<%-- 
    Document   : writeContents
    Created on : Jul 4, 2018, 1:46:14 PM
    Author     : Mr.Long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contents Page</title>
    </head>
    <body>
        <h1>Contents From</h1>

        <form action="getContents">
            Subject ID: ${requestScope.subjectID}<br/>
            <input type="hidden" name="subjectID" value="${requestScope.subjectID}" />

            Block: ${requestScope.block}<br/>
            <input type="hidden" name="block" value="${requestScope.block}" />

            Your Status: <input type="text" name="txtContents" value="" size="150" />(0-250 character)<br/>
            <c:set var="errors" value="${requestScope.contentsErr}"/>
            <c:if test="${not empty errors.contentsLengthErr}">
                <font color="red">
                ${errors.contentsLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Update" />
            <input type="reset" value="Reset" /><br/>
            click <a href="feedBack.jsp">Here</a> to come back feedBack Form
        </form>
    </body>
</html>
