<%-- 
    Document   : view_grade
    Created on : Mar 4, 2024, 2:25:13 PM
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
            <thead>
            <th>Campus/Program</th>
            <th>Term</th>
            <th>Course</th>
        </thead>
        <tbody>
            <tr>
                <td>FU-HL</td>
                <td>
                    <div>
                        <table>
                            <c:forEach items="${requestScope.semesters}" var="se">
                                <tr><td><a href="view_attendance?sid=${param.sid}&seId=${se.id}">${se.name}${se.year}</a></td></tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
                <td>
                    <div>
                        <table>
                            <c:forEach items="${requestScope.registrations}" var="r">
                                <tr><td><a href="view_attendance?sid=${param.sid}&seId=${param.seId}&subid=${r.subject.id}">${r.subject.name}</a></td></tr>
                            </c:forEach>
                        </table>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
    <table border="1px solid black">
        <thead>
        <th>No</th>
        <th>Date</th>
        <th>Slot</th>
        <th>Room</th>
        <th>Lecturer</th>
        <th>Group name</th>
        <th>Attendance status</th>
        <th>Lecturer Comment</th>
    </thead>
    <tbody>
    <c:set value="1" var="index"/>
    <c:forEach items="${requestScope.attrs}" var="attr">
        <tr>
            <td>${index}</td>
            <td>${attr.session.date}</td>
            <td>${attr.session.time_slot.id}(${attr.session.time_slot.timeBegin}-${attr.session.time_slot.timeEnd})</td>
            <td>${attr.session.room.building.id}-${attr.session.room.number}</td>
            <td>${attr.session.lecturer.id}</td>
            <td>${attr.session.group.name}</td>
            <c:if test="${attr.attendance.isPresent eq true}">
                <td>Present</td>
            </c:if>
            <c:if test="${attr.attendance.isPresent eq false}">
                <td>Absent</td>
            </c:if>
            <c:if test="${attr.attendance.isPresent eq null}">
                <td>Future</td>
            </c:if>
            <td>${attr.attendance.description}</td>
            <c:set value="${index + 1}" var="index"/>
        </tr>
    </c:forEach>
</tbody>
</table>
</body>
</html>
