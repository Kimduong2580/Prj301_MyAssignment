<%-- 
    Document   : view_attendance
    Created on : Mar 2, 2024, 10:50:30 AM
    Author     : Nguyen Kim Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Attendance</title>
    </head>
    <body>
        <form action="time_table" method="post">
            <table border="1px solid black">
                <tr>
                    <th>No</th>
                    <th>Group</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Image</th>
                    <th>Status</th>
                    <th>Comment</th>
                </tr>
                <c:set value="1" var="index"/>
                <c:forEach items="${requestScope.students}" var="student">
                    <c:forEach items="${requestScope.attendances}" var="att">
                        <c:if test="${student.id eq att.student.id}">
                            <tr>
                                <td>${index}</td>
                                <td>
                                    ${requestScope.gname}
                                </td>
                                <td>
                                    ${student.id}
                                </td>
                                <td>
                                    ${student.name}
                                </td>
                                <td>

                                </td>

                                <td>
                                    Attendance<input type="radio" value="true" disabled="disable"
                                                     <c:if test="${att.isPresent eq true}">checked="checked"</c:if>
                                                     name="attendance-${student.id}"/>
                                    Absent<input  type="radio" value="false" disabled="disable"
                                                  <c:if test="${att.isPresent eq false}">checked="checked"</c:if>
                                                  name="attendance-${student.id}"/>
                                </td>
                                <td>
                                    <input type="text" value="${att.description}" name="description-${student.id}"/>
                                </td>

                            </tr>
                            <c:set value="${index + 1}" var="index"/>
                        </c:if>

                    </c:forEach>
                </c:forEach>
            </table>
            <input type="hidden" value="${param['sid']}" name="sid"/>

            <input type="submit" value="RETURN"/>
        </form>
    </body>
</html>
