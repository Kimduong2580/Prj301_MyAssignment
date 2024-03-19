<%-- 
    Document   : login.
    Created on : Mar 10, 2024, 7:20:06 PM
    Author     : Nguyen Kim Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Login</title>
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
        <link rel="stylesheet" href="view/authentication/login/css/login.css" />
    </head>
    <body>

        <div class="container">
            <div id="header">
                <header>
                    <h1>FPT University Academic Portal</h1>
                    <table width="40%" border="none">
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

                </nav>
            </div>
            <div id="content">
                <table id="table_content" border="10px solid white" style="width: 100%">
                    <tr>
                        <td style="width: 50%;">
                            <div>
                                <button type="button" class="button-orange">Phụ Huynh</button>
                            </div>
                        </td>
                        <td style="width: 50%;">
                            <div>
                                <button type="button" class="button-orange">
                                    Sinh viên, Giảng viên, Cán bộ ĐH-FPT
                                </button>
                            </div>
                        </td>
                    </tr>
                    <tr style="height: 150px">
                        <td style="width: 50%;">
                            <div style="text-align: center">
                                <button type="button" class="btn btn-primary">Đăng nhập</button>
                            </div>
                        </td>
                        <td style="width: 50%;">
                            <div id="login_ot">
                                <select name="campus" id="select_campus">
                                    <option value="Select Campus">Select Campus</option>
                                    <option value="Hoa Lac">FU-Hòa Lạc</option>
                                    <option value="Ho Chi Minh">FU-Hồ Chí Minh</option>
                                    <option value="Can Tho">FU-Cần Thơ</option>
                                    <option value="Da Nang">FU-Đà Nẵng</option>
                                    <option value="Quy Nhon">FU-Quy Nhơn</option>
                                </select>
                                <form action="login" method="post" id="form">
                                    <div class="login">Username: <input type="text" required="required" name="username" /><br /></div>
                                    <div class="login-pass">
                                        Password: <input id="input_pass" type="password" required="required" name="password" /><br />
                                    </div>
                                    <input class="btn btn-secondary" type="submit" value="Login" />
                                </form>
                            </div>
                        </td>
                    </tr>
                </table>
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
        </div>
    </body>
</html>
