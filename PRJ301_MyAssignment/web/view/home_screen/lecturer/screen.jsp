<%-- 
    Document   : screen.jsp
    Created on : Mar 10, 2024, 7:49:22 PM
    Author     : Nguyen Kim Duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Home</title>
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
            crossorigin="anonymous"
            />
        <script
            src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"
        ></script>
        <link rel="stylesheet" href="view/home_screen/css/main_screen.css" />
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
                                    <img width="60%" src="view/image/pngegg (1).png" alt="app store"/>
                                </td>
                                <td>
                                    <img width="60%" src="view/image/pngegg.png" alt="google play" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </header>
            </div>
            <div>
                <nav>
                    <div id="nav_lef">

                    </div>
                    <div id="nav_right">
                        <a class="box_green box_hover" href="#">${requestScope.account.displayName}</a
                        >&nbsp;|&nbsp;<a class="box_green box_hover" href="logout">logout</a
                        >&nbsp;|&nbsp;
                        <b class="box_green">CAMPUS: FPTU-Hòa Lạc</b>
                    </div>
                </nav>
            </div>
            <div class="row"  style="height: 500px">
                <div id="notification" class="col-md-6 border border-bottom-0 custom-width">
                    <div class="alert alert-warning" role="alert">
                        <h4>News</h4>
                    </div>
                </div>
                <div class="col-md-6 border border-bottom-0 custom-width" >
                    <div class="alert alert-info" role="alert">
                        <h4>Academic Information</h4> 
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <a class="btn btn-primary custom_a" href="lecturer/time_table">Time table</a>
                        </div>
                    </div>
                    <br><!-- comment -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="dropdown">
                                <a class="btn btn-secondary dropdown-toggle custom_a" href="#" role="button" data-toggle="dropdown" aria-expanded="false">
                                    Attendance
                                </a>
                                <ul class="dropdown-menu">
                                    <c:forEach items="${requestScope.sessions}" var="se">
                                        <li>${se.group.name}|${se.time_slot.id}|${se.date} - 
                                            <c:if test="${se.isTaken eq true}">
                                                Status: (<a style="color: #009900" href="lecturer/view_attendance?seid=${se.id}">view</a>)-(<a href="lecturer/update_attendance?seid=${se.id}">edit</a>)
                                            </c:if>
                                            <c:if test="${se.isTaken eq false}">
                                                <a style="color: red" class="dropdown-item" href="lecturer/attendance?seid=${se.id}">attended</a>
                                            </c:if>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>


                    <br><!-- comment -->
                    <div class="row">
                        <div class="col-md-12">
                            <a class="btn btn-primary custom_a" href="lecturer/list_student?lid=${requestScope.account.code}">List student</a>
                        </div>
                    </div>

                </div>
            </div>

            <div id="footer">
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
    </body>
</html>


