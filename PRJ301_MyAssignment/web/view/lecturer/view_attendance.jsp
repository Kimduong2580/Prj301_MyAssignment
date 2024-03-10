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
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        
    </head>
    <body>
        <form action="time_table" method="post">
            <table border="1px solid black">
                <tr>
                    <th>No</th>
                    <th>Group</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th style="width: 10%">Image</th>
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
                                    <img width="100%" src="../view/image/${student.avatar}" alt=".." />
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
