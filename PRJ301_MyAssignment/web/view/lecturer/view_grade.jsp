<%-- 
    Document   : view_grade
    Created on : Mar 2, 2024, 9:40:50 PM
    Author     : Nguyen Kim Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View grade</title>
    </head>
    <body>
        <form action="view_grade" method="post">
            Student ID: <input type="text" name="sid"/>
            <input type="submit" value="Search"/>
        </form>
        <table border="1px solid black">
            <tr>
                <th>SUBJECT CODE</th>
                <th>SUBJECT NAME</th>
                <th>SEMESTER</th>
                <th>GROUP</th>
                <th>AVERAGE MARK</th>
                <th>STATUS</th>
            </tr>
            
        </table>
    </body>
</html>
