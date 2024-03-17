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
        <title>View Attendance For Student</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <link href="../view/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="container">
            <div id="header">
                <header>
                    <h1>FPT University Academic Portal</h1>
                    <table width="40%">
                        <tbody>
                            <tr id="intro_app">
                                <td colspan="2"><b>Fap mobile app (myFAP) is ready at</b></td>
                            </tr>
                            <tr id="img_app_fap">
                                <td>
                                    <img width="60%" src="../view/image/pngegg (1).png" alt="app store"/>
                                </td>
                                <td>
                                    <img width="60%" src="../view/image/pngegg.png" alt="google play" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </header>
            </div>
            <div>
                <nav>
                    <div id="nav_lef">
                        <a class="link_hover" href="../home">Home</a>&nbsp;|&nbsp;<b
                            >Attendance detail</b>
                    </div>

                </nav>
            </div>
            <div id="content" class="row">
                <div class="col-md-4">
                    <table class="content_table content_table_1" border="1px solid black" >
                        <thead>
                        <th>Campus / Program</th>
                        <th>Term</th>
                        <th>Course</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="text-align: left; vertical-align: top;">FU-HL</td>
                                <td style="text-align: left; vertical-align: top;">
                                    <div>
                                        <table>
                                            <c:forEach items="${requestScope.semesters}" var="se">
                                                <tr><td><a href="view_attendance?seId=${se.id}">${se.name}${se.year}</a></td></tr>
                                                    </c:forEach>
                                        </table>
                                    </div>
                                </td>
                                <td style="text-align: left; vertical-align: top;">
                                    <div>
                                        <table>
                                            <c:forEach items="${requestScope.registrations}" var="r">
                                                <tr><td><a href="view_attendance?seId=${requestScope.semesterId}&subid=${r.subject.id}">${r.subject.name}(${r.subject.id})(${r.group.name}, ${r.dateBegin})</a></td></tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <c:if test="${!(param.subid eq null)}" >
                    <div class="col-md-8">
                        <table class="content_table content_table_1 table_main" id="table_attendance" border="1px solid black">
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
                    </div>
                </c:if>
                <div id="footer">
                    <div id="contact">
                        <p>
                            <span><b>Mọi góp ý, thắc mắc xin liên hệ: </b></span> Phòng dịch
                            vụ sinh viên: Email:
                            <a class="link_hover" href="#">dichvusinhvien@fe.edu.vn.</a> Điện
                            thoại:
                            <span><b>(024)7308.13.13</b></span>
                        </p>
                    </div>
                    <div>
                        <p>
                            © Powered by FPT University |
                            <span><a class="link_hover" href="#">FPT University</a></span> |
                            <span><a class="link_hover" href="#">CMS</a></span> |
                            <span><a class="link_hover" href="#">library</a></span> |
                            <span><a class="link_hover" href="#">book24x7</a></span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
