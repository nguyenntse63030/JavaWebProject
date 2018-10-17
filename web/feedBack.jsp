<%-- 
    Document   : feedBack
    Created on : Jul 3, 2018, 2:54:19 PM
    Author     : Mr.Long
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FeedBack Page</title>
    </head>
    <body>
        <h1>Feedback form</h1>
        <br/>
        <br/>
        Student ID: ${sessionScope.STUDENTID}<br/>
        Student Name: ${sessionScope.studentFullName}
        <br/>
        <br/>
        Some marks of courses is not correct. Please, explain them for me 
        <c:set var="listFeedBack" value="${sessionScope.feedBackInfo}"/>

        <c:if test="${not empty listFeedBack}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Subject ID</th>
                        <th>Subject Name</th>
                        <th>Block</th>
                        <th>Semester</th>
                        <th>Year</th>
                        <th>AVG</th>
                        <th>Status</th>
                        <th>Write contents</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <form action="sendOrRemoveFeedBack">
                    <c:set var="flagCheck" value="${requestScope.flagCheck}"/>
                    <c:forEach var="dto" items="${listFeedBack}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count} 
                                .</td>
                            <td>
                                ${dto.subjectID}
                                <c:set var="subjectID" value="${dto.subjectID}"/>
                            </td>
                            <td>
                                ${dto.subjectName}
                            </td>
                            <td>
                                ${dto.block}
                                <c:set var="block" value="${dto.block}"/>
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

                                <c:if test="${not empty flagCheck}">
                                    <c:forEach var="flagWhereErr" items="${flagCheck}">

                                        <c:if test="${flagWhereErr.value == counter.count}">                                           
                                            <font color="red">                                          
                                            Status can not be null, please write contents
                                            </font>
                                        </c:if>

                                        <c:if test="${flagWhereErr.value != counter.count}">
                                            <c:set var="status" value="true"/>
                                        </c:if>

                                    </c:forEach>
                                </c:if>

                                <c:if test="${not empty status}">
                                    ${dto.status}
                                </c:if>

                                <c:if test="${empty flagCheck}">
                                    ${dto.status}
                                </c:if>    

                            </td>
                            <td>
                                <c:url var="writeContentsLink" value="writeContents">
                                    <c:param name="subjectID" value="${dto.subjectID}"/>
                                    <c:param name="block" value="${dto.block}"/>
                                </c:url>
                                <a href="${writeContentsLink}">Write Status</a>
                            </td>
                            <td>
                                <input type="checkbox" name="actionFeedBAck" value="${subjectID}_${block}_${counter.count}"/>                              
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5">
                            <input type="submit" value="Send FeedBack" name="btnAction"/>
                        </td>
                        <td colspan="5">
                            <input type="submit" value="Remove" name="btnAction"/>
                        </td>
                    </tr>
                </form>
            </tbody>
        </table>
                    
        <c:set var="success" value="${requestScope.success}"/>
        <c:if test="${not empty success}">
            <font color="red">${requestScope.success}</font>
        </c:if>
            
        <c:set var="fall" value="${requestScope.fall}"/>
        <c:if test="${not empty success}">
            <font color="red">${requestScope.fall}</font>
        </c:if>
            
    </c:if>
            
    <c:if test="${empty listFeedBack}">
        <h1>
            <font color="red">
            No feedBack ready to send please add subject you want to send feedBack
            </font>
        </h1>
    </c:if>
    <br/>
    Click <a href="showMarkTable">Here</a> to view Mark Table Page
</body>
</html>
