<%-- 
    Document   : markDetail
    Created on : Jun 30, 2018, 7:29:25 PM
    Author     : Mr.Long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mark Detail Page</title>
    </head>
    <body>
        <font color="red"> 
        welcome,${sessionScope.username}
        </font><br/>
        <a href="logout">Logout</a>
        <br/>
        Subjects' mark details 
        <br/>
        <br/>
        Subject name: ${requestScope.subjectID}
        <c:set var="markResult" value="${requestScope.resultDetailMark}"/>
        <c:if test="${not empty markResult}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>

                        <th>Subject Name</th>
                        <th>Block</th>
                        <th>Semester</th>
                        <th>Year</th>
                        <th>Avg</th>
                        <th>Status</th>

                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="dto" items="${markResult}" varStatus="counter">

                        <tr>
                            <td>
                                ${counter.count}
                                <c:set var="numberOfStudying" value="${counter.count}"/>
                                .</td>

                            <td>
                                ${dto.subjectName}
                            </td>
                            <td>
                                ${dto.block}
                            </td>
                            <td>
                                ${dto.semester}
                            </td>
                            <td>
                                ${dto.year}
                            </td>
                            <td>
                                ${dto.subjectAvg}
                            </td>
                            <td>
                                ${dto.status}
                                <c:set var="status2" value="${dto.status}"/>
                            </td>                           
                        </tr>

                    </c:forEach>
                    <tr>
                        <td colspan="6">
                            <c:set var="statusTest2" value="Not Started"/>                              
                            <c:if test="${status2 == statusTest2}">
                                Number of studying : ${numberOfStudying - 1}
                            </c:if>
                            <c:if test="${status2 != statusTest2}">
                                Number of studying : ${numberOfStudying}
                            </c:if>     
                        </td>
                    </tr>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty markResult}">
            <h1>
                Server is updating please come back later
            </h1>
        </c:if>
        Click <a href="showMarkTable">here</a> to come back Mark Table Page
    </body>
</html>
