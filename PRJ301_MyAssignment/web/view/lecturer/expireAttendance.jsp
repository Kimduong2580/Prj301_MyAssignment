<%-- 
    Document   : expireAttendance
    Created on : Mar 2, 2024, 3:29:58 PM
    Author     : Nguyen Kim Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        
        <form id="form" action="time_table" method="post">
            
        </form>
        <script>
            var listener = confirm("Attendance expires");
            if(listener == true) {
                document.getElementById("form").submit();
            }else {
                
            }
        </script>
    </body>
</html>
