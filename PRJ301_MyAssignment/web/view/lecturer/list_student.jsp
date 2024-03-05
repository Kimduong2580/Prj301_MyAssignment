<%-- 
    Document   : list_student
    Created on : Mar 3, 2024, 10:03:42 AM
    Author     : Nguyen Kim Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border = "1px solid black">
            <tr>
                <th>Index</th>
                <th>Image</th>
                <th>Member</th>
                <th>Code</th>
                <th>Name</th>
            </tr>
            <c:set var="index" value="1"/>
            <c:forEach items="${requestScope.students}" var="s">
                <tr>
                    <td>${index}</td>
                    <c:set var="index" value="${index + 1}"/>
                    <td>
                        <img width="15%" src="../view/image/${s.avatar}" alt=".." />
                    </td>
                    <td>
                        <a href="view_grade?sid=${s.id}">View grade</a>
                    </td>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                </tr>

            </c:forEach>
        </table>
    </body>
</html>
