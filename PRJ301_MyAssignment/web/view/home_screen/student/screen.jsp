<%-- 
    Document   : screen
    Created on : Mar 10, 2024, 7:49:46 PM
    Author     : Nguyen Kim Duong
--%>

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
                        >&nbsp;|&nbsp;<a class="box_green box_hover" href="logout">Logout</a
                        >&nbsp;|&nbsp;
                        <b class="box_green">CAMPUS: FPTU-Hòa Lạc</b>
                    </div>
                </nav>
            </div>
            <div id="content">
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
                                <a class="btn btn-primary custom_a" href="student/time_table">Time table</a>
                            </div>
                        </div>
                        <br><!-- comment -->
                        <div class="row">
                            <div class="col-md-12">
                                    <a class="btn btn-primary custom_a" href="student/view_attendance">View attendance</a>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-12">
                                <a class="btn btn-primary custom_a" href="student/view_grade">View Grade</a>
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


