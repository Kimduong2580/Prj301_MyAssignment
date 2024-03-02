<%-- 
    Document   : time_table
    Created on : Mar 1, 2024, 8:27:12 PM
    Author     : Nguyen Kim Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Time_table</title>
        <link href="../view/lecturer/css/css/boostrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../view/lecturer/css/style.css" rel="stylesheet" type="text/css"/>
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
                            Lecturer: <input type="text" name="lid" value="${requestScope.lid}">
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
                                <c:forEach items="${requestScope.sessions}" var="ses">
                                    <c:set value="${ses.time_slot.id.trim()}" var="sesTime"/>
                                    <c:if test="${time.id.trim() eq sesTime.trim()}">
                                        <c:if test="${date.toString() eq ses.date.toString().trim()}">
                                            <span>
                                                <span style="color: rgb(38, 85, 204)"><a href="#">${ses.group.subject.id}</a>-</span>
                                                <a class="box_orange box" href="#">View Materials</a><br />
                                                <span>at ${ses.room.building.id}-${ses.room.number} - </span>
                                                <a class="box_grey box" href="#">Meet URL</a>
                                                <a class="box_blue box" href="#">-EduNext</a><br />
                                                <c:if test="${ses.isTaken eq true}">
                                                    (<span style="color: #009900"><a href="view_attendance?sid=${ses.id}">view</a>)-(<a href="update_attendance?sid=${ses.id}">edit</a></span>)
                                                </c:if>
                                                <c:if test="${ses.isTaken eq false}">
                                                    (<span style="color: red"><a href="attendance?sid=${ses.id}">attended</a></span>)
                                                </c:if>

                                                <c:if test="${empty ses.isTaken}">
                                                    (<span style="color: red">Not yet</span>)
                                                </c:if>
                                                <br/>
                                                <span class="box_green box">(${ses.time_slot.timeBegin} - ${ses.time_slot.timeEnd})</span>
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
