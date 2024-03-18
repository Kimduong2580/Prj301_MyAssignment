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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <link href="../view/css/style.css" rel="stylesheet" type="text/css"/>
        <script>
            function submitForm() {
                var form = document.getElementById("form");
                form.submit();
            }
        </script>
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
            <div id="content" class="col-md-12">
                <div>
                    <nav>
                        <div id="nav_lef">
                            <a class="link_hover" href="../home">Home</a>&nbsp;|&nbsp;<b
                                >User detail</b>
                        </div>
                        <div id="nav_right">
                            <a class="box_green box_hover" href="#">${requestScope.displayName}</a
                            >&nbsp;|&nbsp;<a class="box_green box_hover" href="../logout">logout</a
                            >&nbsp;|&nbsp;
                            <b class="box_green">CAMPUS: FPTU-Hòa Lạc</b>
                        </div>
                    </nav>
                </div>

                <h2>Activities for ${requestScope.displayName}</h2>
                <div id="note">
                    <p>
                        <b>Note:</b> These activities do not include extra-curriculum
                        activities, such as club activities ...
                    </p>
                    <p>
                        <b>Chú thích:</b> Các hoạt động trong bảng dưới không bao gồm hoạt
                        động ngoại khóa, ví dụ như hoạt động câu lạc bộ ...
                    </p>
                    <div id="note_detail">
                        <p>Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL...</p>
                        <p>Các phòng bắt đầu bằng BE thuộc tòa nhà Beta. VD: BE...</p>
                        <p>Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201...</p>
                        <p>Các phòng tập bắt đầu bằng R thuộc khu vực sân tập Vovinam.</p>
                        <p>Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE...</p>
                        <p>Little UK (LUK) thuộc tầng 5 tòa nhà Delta.</p>
                    </div>
                </div>
                <div>
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
                    <div>
                        <b>More note / Chú thích thêm:</b>
                        <ul>
                            <li>
                                (<span style="color: green">attended</span>): ${requestScope.displayName}
                                had attended this activity / ${requestScope.displayName} đã tham gia hoạt
                                động này.
                            </li>
                            <li>
                                (<span style="color: red">absent</span>): ${requestScope.displayName} had
                                NOT attended this activity / ${requestScope.displayName} đã vắng mặt buổi
                                này.
                            </li>
                            <li><span>(-): </span> no data was given / chưa có dữ liệu.</li>
                        </ul>
                    </div>
                </div>
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
