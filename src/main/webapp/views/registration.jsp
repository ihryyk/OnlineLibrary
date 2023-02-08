<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Library</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles/registration.css">
    <style>
        <%@include file="styles/registration.css"%>
    </style>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
<c:if test="${sessionScope.popUps=='login'}">
    <script>
        Swal.fire({
            icon: 'error',
            title: '<fmt:message key="Error"/>',
            text: '<fmt:message key="EmailIsUse"/>',
            footer: '<a href="${pageContext.request.contextPath}/login"><fmt:message key="Login"/>'
        })
        ${sessionScope.remove("popUps")}
    </script>
</c:if>


<c:if test="${sessionScope.popUps=='valid'}">
    <script>
        Swal.fire({
            icon: 'error',
            title: '<fmt:message key="Error"/>',
            text: '<fmt:message key="validUserRegister"/>',
        })
        ${sessionScope.remove("popUps")}
    </script>
</c:if>



<div class="registr">
    <h1><fmt:message key="registrationForm"/></h1>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <table>
            <tr>
                <td><fmt:message key="UserName"/></td>
                <td><input type="text" required
                           title="Please check if the username and surname starts with a capital letter"
                           name="userName"/></td>
            </tr>
            <tr>
                <td><fmt:message key="EmailAddress"/></td>
                <td><input type="text" required name="emailAddress"/></td>
            </tr>

            <tr>
                <td><fmt:message key="Password"/></td>
                <td><input type="password"  title="Password has to consist of at least four symbols and max ten" name="password"/></td>
            </tr>
        </table>
        <input type="submit" value="<fmt:message key="Submit"/>"/>
    </form>
</div>
</body>

</html>