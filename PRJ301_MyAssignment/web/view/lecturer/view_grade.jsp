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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="../view/css/style.css"/>
        <title>View grade</title>
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
                        <a class="link_hover" href="../home">Home</a>><a class="link_hover" href="list_student?lid=${requestScope.lid}">List student</a>&nbsp;|&nbsp;<b
                            >Grade detail</b>
                    </div>

                </nav>
            </div>
            <div id="content">
                <div id="list_group">
                    <form id="form_list" action="view_grade" method="post">
                        
                    </form>
                </div>
                <table class="content_table content_table_1" border="1px solid black">
                    <thead>
                    <th>NO</th>
                    <th>SUBJECT CODE</th>
                    <th>SUBJECT NAME</th>
                    <th>SEMESTER</th>
                    <th>GROUP</th>
                    <th>AVERAGE MARK</th>
                    <th>STATUS</th>
                    </thead>
                    <tbody>
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
                                <td style="text-align: center">${r.averageMark}</td>

                                <c:if test="${r.status eq true}">
                                    <td style="color: green">Passed</td>
                                </c:if>
                                <c:if test="${r.status eq false}">
                                    <td style="color: red">Not Pass</td>
                                </c:if>
                                <c:if test="${r.status eq null}">
                                    <td style="color: green">Studying</td>
                                </c:if>
                            </tr>

                        </c:forEach>
                    </tbody>

                </table>

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
