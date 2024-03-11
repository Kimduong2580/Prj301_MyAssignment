<%-- 
    Document   : update_attendance
    Created on : Mar 2, 2024, 2:57:06 PM
    Author     : Nguyen Kim Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update attendance</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <link href="../view/lecturer/css/time_table.css" rel="stylesheet" type="text/css"/>
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
                        <a class="link_hover" href="../mainScreen">Home</a>&nbsp;|&nbsp;<b
                            >Attendance detail</b>
                    </div>

                </nav>
            </div>
            <div id="content">
                <form action="update_attendance" method="post">
                    <table border="1px solid black">
                        <tr>
                            <th>No</th>
                            <th>Group</th>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Image</th>
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

                                        </td>

                                        <td>
                                            Attendance<input type="radio" value="true" 
                                                             <c:if test="${att.isPresent eq true}">checked="checked"</c:if>
                                                             name="attendance-${student.id}"/>
                                            Absent<input  type="radio" value="false" 
                                                          <c:if test="${att.isPresent eq false}">checked="checked"</c:if>
                                                          name="attendance-${student.id}"/>
                                        </td>
                                        <td>
                                            <input type="text" placeholder="${att.description}" value="${att.description}" name="description-${student.id}"/>
                                        </td>

                                    </tr>
                                    <c:set value="${index + 1}" var="index"/>
                                </c:if>

                            </c:forEach>
                        </c:forEach>
                    </table>
                    <input type="hidden" value="${param['sid']}" name="sid"/>

                    <input type="submit" value="UPDATE"/>
                </form>

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
