<%-- 
    Document   : view_grade
    Created on : Mar 4, 2024, 7:57:58 PM
    Author     : Nguyen Kim Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Grade</title>
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
                            >Grade detail</b>
                    </div>

                </nav>
            </div>
            <div id="content">
                <table border="1px solid black">
                    <thead>
                    <th>Term</th>
                    <th>Course</th>
                    </thead>
                    <tbody>
                    <td>
                        <table>
                            <c:forEach items="${requestScope.semesters}" var="se">
                                <tr>
                                    <td>
                                        <a href="view_grade?seid=${se.id}">${se.name}${se.year}</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                    <td style="text-align: left; vertical-align: top;">
                        <table>
                            <c:forEach items="${requestScope.registrations}" var="re">
                                <tr>
                                    <td>
                                        <a href="view_grade?seid=${re.semester.id}&subid=${re.subject.id}">${re.subject.name}(${re.subject.id})(${re.group.name})</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>

                    </td>
                    </tbody>
                </table>
                <c:if test="${param.subid != null}">
                    <table border="1px solid black">
                        <thead>
                        <th>Grade Category</th>
                        <th>Grade Item</th>
                        <th>Weight</th>
                        <th>Value</th>
                        <th>Comment</th>
                        </thead>
                        <tbody>
                            <c:set var="category" value="${requestScope.categoryAss}"/>
                            <c:forEach items="${category.keySet()}" var="ca">
                                <tr>
                                    <td rowspan="${categoryAss[ca] + 2}">${ca}</td>
                                    <c:set var="weight" value="0"/>
                                    <c:set var="score" value="0"/>
                                    <c:forEach items="${requestScope.gss}" var="gs" >
                                        <c:if test="${gs.assessment.category eq ca}">
                                        <tr>
                                            <td>
                                                ${gs.assessment.name}
                                            </td>
                                            <td>${gs.assessment.weight}%</td>
                                            <c:set var="weight" value="${gs.assessment.weight + weight}"/>
                                            <td>${gs.grade.score}</td>
                                            <c:set var="score" value="${(gs.grade.score * gs.assessment.weight/100.0 + score)}"/>

                                            <td></td>
                                        </tr>

                                    </c:if>
                                </c:forEach>
                                <tr>
                                    <td>Total</td>
                                    <td>${weight}%</td>
                                    <td>${score/(weight/100.0)}</td>
                                    <td></td>
                                </tr>
                                </tr>
                            </c:forEach>
                            <c:if test="${requestScope.averageMark gt -1}">
                                <tr>
                                    <td colspan="2">COURSE TOTAl AVERAGE:</td>
                                    <td colspan="3">${requestScope.averageMark}</td>
                                </tr>
                                <tr>
                                    <td colspan="1"></td>
                                    <td colspan="1">Status:</td>

                                    <c:if test="${requestScope.averageMark lt 5}">
                                        <td colspan="3" style="color: red">Not pass</td>
                                    </c:if>
                                    <c:if test="${requestScope.averageMark gt 5}">
                                        <td colspan="3" style="color: green">Passed</td>
                                    </c:if>
                                </tr>
                            </c:if> 
                        </tbody>    
                    </table>
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
