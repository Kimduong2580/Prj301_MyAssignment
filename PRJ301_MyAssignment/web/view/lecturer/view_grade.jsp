<%-- 
    Document   : view_grade
    Created on : Mar 2, 2024, 9:40:50 PM
    Author     : Nguyen Kim Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View grade</title>
    </head>
    <body>
        
        <table border="1px solid black">
            <tr>
                <th>NO</th>
                <th>SUBJECT CODE</th>
                <th>SUBJECT NAME</th>
                <th>SEMESTER</th>
                <th>GROUP</th>
                <th>AVERAGE MARK</th>
                <th>STATUS</th>
            </tr>
            <c:set value="1" var="index"/>
            <c:forEach items="${requestScope.registrations}" var="r">
                <tr>
                    <td>
                        ${index}
                    </td>
                    <c:set var="index" value="${index + 1}"/>
                    <td>${r.subject.id}</td>
                    <td>${r.subject.name}</td>
                    <td>${r.semester.name}${r.semester.year}</td>
                    <td>${r.group.name}</td>
                    <c:set value="0" var="flag"/>
                    <c:forEach items="${requestScope.avgMarks}" var="avg">
                        <c:if test="${avg.registration.id eq r.id}">
                            <td style="text-align: center">${avg.averageMark}</td>
                            <c:if test="${avg.averageMark gt 5}">
                                <td style="color: green">Passed</td>
                            </c:if>
                            <c:if test="${avg.averageMark lt 5}">
                                <td style="color: red">Not Passed</td>
                            </c:if>
                            <c:set var="flag" value="${flag + 1}"/>
                        </c:if>
                    </c:forEach>
                    <c:if test="${flag == 0}">
                        <td style="text-align: center">-</td>
                        <td>future</td>
                    </c:if>           
                </tr>

            </c:forEach>
        </table>
    </body>
</html>
