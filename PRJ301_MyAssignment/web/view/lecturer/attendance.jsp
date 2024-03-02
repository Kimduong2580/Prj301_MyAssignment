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
        <form action="attendance" method="post">
            <table border="1px solid black">
                <tr>
                    <th>Group</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Image</th>
                    <th>Status</th>
                    <th>Comment</th>
                </tr>
                <c:forEach items="${requestScope.students}" var="student">

                    <tr>
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
                            Attendance<input type="radio" value="true" name="attendance-${student.id}"/>
                            Absent<input  type="radio" value="false" name="attendance-${student.id}"/>
                        </td>
                        <td>
                            <input type="text" name="description-${student.id}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input type="hidden" value="${param['sid']}" name="sid"/>
            
            <input type="submit" value="save"/>
        </form>
    </body>
</html>
