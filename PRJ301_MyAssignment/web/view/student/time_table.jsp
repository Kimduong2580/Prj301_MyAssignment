<%-- 
    Document   : time_table
    Created on : Mar 3, 2024, 9:14:28 PM
    Author     : Nguyen Kim Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Time table</title>
        <link href="../view/student/css/css/boostrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../view/student/css/style.css" rel="stylesheet" type="text/css"/>
        <script>
            function submitForm() {
                var form = document.getElementById()("form");
                form.submit();
            }
        </script>
    </head>
    <body>
        <table width="100%" id="table_time">
            <thead style="background-color: cornflowerblue">
                <tr>
                    <th rowspan="2" width="9%">
                        <form id="form" action="time_table" method="post">
                            <input type="date" name="fromDate" value="${requestScope.fromDate}" onchange="submitForm()">
                            To: <input type="date" name="toDate" value="${requestScope.toDate}" onchange="submitForm()"/><br>
                            <input type="hidden" name="sid" value="${param.sid}"/>
                            <input type="submit" value="SEND">
                        </form>
                    </th>
                    <c:forEach items="${requestScope.listNameDatesInWeek}" var="nameDates">
                        <th>${nameDates}</th>
                        </c:forEach>
                </tr>
                <tr>

                    <c:forEach items="${requestScope.listDatesInWeek}" var="date">
                        <th id="${date}">${date}</th>
                <input type="hidden" value="${date}" name="date">
            </c:forEach>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.times}" var="time">
                    <tr>
                        <td id="${time.id}">
                            ${time.id}
                        </td>
                        <c:forEach items="${requestScope.listDatesInWeek}" var="date">
                            <td class="scheduling">
                                <c:forEach items="${requestScope.attendanceRecords}" var="attr">
                                    <c:set value="${attr.session.time_slot.id.trim()}" var="sesTime"/>
                                    <c:if test="${time.id.trim() eq sesTime.trim()}">
                                        <c:if test="${date.toString() eq attr.session.date.toString().trim()}">
                                            <span>
                                                <span style="color: rgb(38, 85, 204)"><a href="#">${attr.session.group.subject.id}</a>-</span>
                                                <a class="box_orange box" href="#">View Materials</a><br />
                                                <span>at ${attr.session.room.building.id}-${attr.session.room.number} - </span>
                                                <a class="box_grey box" href="#">Meet URL</a>
                                                <a class="box_blue box" href="#">-EduNext</a><br />
                                                
                                                <c:if test="${attr.attendance.isPresent eq true}">
                                                    (<span style="color: #009900">Attendance</span>)
                                                </c:if>
                                                <c:if test="${attr.attendance.isPresent eq false}">
                                                    (<span style="color: red">Absent</span>)
                                                </c:if>

                                                <c:if test="${empty attr.attendance.isPresent}">
                                                    (<span style="color: red">Not yet</span>)
                                                </c:if>
                                                <br/>
                                                <span class="box_green box">(${attr.session.time_slot.timeBegin} - ${attr.session.time_slot.timeEnd})</span>
                                            </span>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
