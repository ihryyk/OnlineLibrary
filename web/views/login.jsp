<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="message"/>

<!DOCTYPE html>
<html>

<head>
    <title>Library</title>
    <meta charset="utf-8">
    <title>Insert title here</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles/login.css">
    <style>
        <%@include file="styles/login.css"%>
    </style>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
<c:if test="${sessionScope.popUps=='registration'}">
    <script>
        Swal.fire({
            icon: 'error',
            title: '<fmt:message key="Error"/>',
            text: '<fmt:message key="IncorrectPasswordOrEmail"/>',
            footer: '<a href="${pageContext.request.contextPath}/registration"><fmt:message key="DoYouWantToRegister"/>'
        })
        ${sessionScope.remove("popUps")}
    </script>
</c:if>
<c:if test="${sessionScope.popUps=='valid'}">
    <script>
        Swal.fire({
            icon: 'error',
            title: '<fmt:message key="Error"/>',
            text: '<fmt:message key="validUserLogin"/>',
        })
        ${sessionScope.remove("popUps")}
    </script>
</c:if>

<div class="login">
    <h1><fmt:message key="LoginForm"/></h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <table style="width: 100%">
            <tr>
                <td><fmt:message key="EmailAddress"/></td>
                <td><input type="text" required name="emailAddress"/></td>
            </tr>

            <tr>
                <td><fmt:message key="Password"/></td>
                <td><input type="password" title="Password has to consist of at least four symbols and max ten" required
                            name="password"/></td>
            </tr>
        </table>
        <input type="submit" value="<fmt:message key="Entry"/>"/>
    </form>
    <a href=${pageContext.request.contextPath}/registration>
        <td><fmt:message key="Registration"/></td>
    </a>
</div>
</body>
</html>