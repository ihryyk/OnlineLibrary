
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html>

<head>
    <title>Library</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles/myProfile.css">
    <style>
        <%@include file="styles/myProfile.css"%>
    </style>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
<c:if test="${sessionScope.popUps=='valid'}">
    <script>
        Swal.fire({
            icon: 'error',
            title: '<fmt:message key="Error"/>',
            text: 'invalidate date',
        })
        ${sessionScope.remove("popUps")}
    </script>
</c:if>
<div class="profile">
    <h1><fmt:message key="Profile"/></h1>
    <form action="${pageContext.request.contextPath}/user/myProfile" method="post">
        <table>
            <tr>
                <td><fmt:message key="UserName"/></td>
                <td><input class="profile_input" type="text"
                           name="userName" value="${sessionScope.userName}"/></td>
            </tr>
            <tr>
                <td><fmt:message key="Password"/></td>
                <td><input class="profile_input" type="password" name="password" pattern="^([a-zA-Z0-9@*#]{4,10})$" value="${sessionScope.password}"/></td>
            </tr>
        </table>
        <input type="submit" value="<fmt:message key="Submit"/>"/>
    </form>
</div>
</body>
</html>
