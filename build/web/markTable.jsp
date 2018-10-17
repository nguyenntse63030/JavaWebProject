<%-- 
    Document   : markTable
    Created on : Jun 30, 2018, 5:59:55 PM
    Author     : Mr.Long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mark Page</title>
    </head>
    <body>
        <font color="red"> 
        welcome,${sessionScope.username}
        </font><br/>
        <a href="logout">Logout</a>
        <h1>Mark Table</h1>
        <br/>
        <br/>
        Subjects' mark details <br/>
        <c:set var="markResult" value="${requestScope.markResult}"/>
        <c:if test="${not empty markResult}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>SubjectID</th>
                        <th>Subject Name</th>
                        <th>Block</th>
                        <th>Semester</th>
                        <th>Year</th>
                        <th>Avg</th>
                        <th>Status</th>
                        <th>Action</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="getFeedBackInfo">
                    <c:forEach var="dto" items="${markResult}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}

                                .</td>
                            <td>
                                ${dto.subjectID}

                            </td>
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
                                <c:set var="status" value="${dto.status}"/>
                            </td>
                            <td>
                                <c:url var="linkDetail" value="viewDetail">
                                    <c:param name="subjectID" value="${dto.subjectID}"/>
                                    <c:param name="studentID" value="${sessionScope.STUDENTID}"/>
                                    <c:param name="subjectName" value=" ${dto.subjectName}"/>
                                </c:url>
                                <a href="${linkDetail}">View Detail</a>

                            </td>
                            <td>
                                <c:set var="statusTest" value="Not Started"/>
                                <c:if test="${status == statusTest}">

                                </c:if>
                                <c:if test="${status != statusTest}">
                                    <input type="checkbox" name="subjectIdInfo" value="${dto.subjectID}" />

                                </c:if>                             
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5">
                            Pass Credits: ${requestScope.creditsPassed}
                        </td>
                        <td colspan="3">
                            GPA: ${requestScope.GPA}
                        </td>
                        <td colspan="2">
                            <input type="submit" value="Send FeedBack" />
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>

    </c:if>
    <c:if test="${empty markResult}">
        <h1>
            <font color="red">
            Server is updating please come back later
            </font>
        </h1>

    </c:if>
        <br/>
        Click <a href="feedBack.jsp">here</a> to view feedBack form
</body>
</html>
