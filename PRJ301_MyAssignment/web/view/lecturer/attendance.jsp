<%-- 
    Document   : attendance
    Created on : Mar 2, 2024, 12:01:57 AM
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
        <table border="1px solid black">
            <tr>
                <th>Group</th>
                <th>Code</th>
                <th>Name</th>
                <th>Image</th>
                <th>Status</th>
                <th>Comment</th>
                <th>Taker</th>
                <th>Record Time</th>
            </tr>

            <tr></tr>
            <c:forEach items="${requestScope.students}" var="student">
                <tr>
                    <td>
                        ${student}
                    </td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
