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
    </head>
    <body>
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
                            <a href="view_grade?sid=${param.sid}&seid=${se.id}">${se.name}${se.year}</a>
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
                            <a href="view_grade?sid=${param.sid}&seid=${re.semester.id}&subid=${re.subject.id}">${re.subject.name}(${re.subject.id})(${re.group.name})</a>
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

</body>
</html>
