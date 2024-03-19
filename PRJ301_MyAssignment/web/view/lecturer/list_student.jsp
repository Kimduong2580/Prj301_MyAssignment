<%-- 
    Document   : list_student
    Created on : Mar 3, 2024, 10:03:42 AM
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
        <title>List student</title>

        <script>
            function submitForm(index) {
                // Lấy giá trị của input hidden tương ứng với chỉ số index
                var gidValue = document.getElementById('gid_' + index).value;
                // Thiết lập giá trị của input hidden có name là "gid" thành giá trị vừa lấy
                document.getElementsByName('gid')[0].value = gidValue;
                // Submit form
                document.getElementById('groupForm').submit();
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
            <div>
                <nav>
                    <div id="nav_lef">
                        <a class="link_hover" href="../home">Home</a>&nbsp;|&nbsp;<b
                            >User detail</b>
                    </div>
                    <div id="nav_right">
                        <a class="box_green box_hover" href="#">${requestScope.lname}</a
                        >&nbsp;|&nbsp;<a class="box_green box_hover" href="../logout">logout</a
                        >&nbsp;|&nbsp;
                        <b class="box_green">CAMPUS: FPTU-Hòa Lạc</b>
                    </div>
                </nav>
            </div>
            <hr>
            <div id="content">
                <div id="form_group">
                    The group list is instructed by: ${requestScope.lname}
                    <c:if test="${empty requestScope.groups}">
                        <h4>${requestScope.emptyGroups}</h4>
                    </c:if>
                    <c:if test="${not empty requestScope.groups}">
                        <form action="list_student" id="groupForm" method="post">
                            <input type="hidden" name="lid" value="${requestScope.lid}"/>
                            <c:forEach items="${requestScope.groups}" var="g" varStatus="loop">
                                <input type="hidden" value="${g.id}" name="gid" id="gid_${loop.index}"/>
                                <input 
                                    <c:if test="${requestScope.gname eq g.name}">class="btn btn-success"</c:if>
                                    <c:if test="${!(requestScope.gname eq g.name)}">class="btn btn-primary"</c:if>
                                    type="button" value="${g.name}" onclick="submitForm(${loop.index})"/>
                            </c:forEach>
                        </form>
                    </c:if>
                </div>
                <div id="list_student">
                    <c:if test="${not empty requestScope.students}">
                        <table class="content_table_1 content_table" border = "1px solid black">
                            <thead id="header_table">
                            <th style="width: 5%">Index</th>
                            <th style="width: 10%">Image</th>
                            <th>Member</th>
                            <th>Sex</th>
                            <th>Code</th>
                            <th>Name</th>
                            </thead>
                            <tbody>
                                <c:set var="index" value="1"/>
                                <c:forEach items="${requestScope.students}" var="s">
                                    <tr>
                                        <td>${index}</td>
                                        <c:set var="index" value="${index + 1}"/>
                                        <td>
                                            <img width="100%" src="../view/image/${s.avatar}" alt=".." />
                                        </td>
                                        <td>
                                            <a href="view_grade?lid=${requestScope.lid}&sid=${s.id}">View grade</a>
                                        </td>
                                        <c:if test="${s.sex eq true}"><td>Male</td></c:if>
                                        <c:if test="${s.sex eq false}"><td>Female</td></c:if>
                                        <td>${s.id}</td>
                                        <td>${s.name}</td>
                                    </tr>

                                </c:forEach>
                            </tbody>

                        </table>
                    </c:if>

                </div>
            </div>
            <br>
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
    </body>
</html>
