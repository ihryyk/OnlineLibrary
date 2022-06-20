<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Library</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles/createBook.css">
    <style>
        <%@include file="styles/createBook.css"%>
    </style>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>

<c:if test="${sessionScope.popUps=='login'}">
    <script>
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'This email is already in use',
        })
        ${sessionScope.remove("popUps")}
    </script>
</c:if>

<c:if test="${sessionScope.popUps=='valid'}">
    <script>
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'invalidate date',
        })
        ${sessionScope.remove("popUps")}
    </script>
</c:if>

<div class="container">
    <form action="${pageContext.request.contextPath}/admin/createLibrarian" method="post">
        <table style="with: 100%">
            <tr>
                <td>UserName</td>
<%--                <td><input type="text" required   title="Please check if the username and surname starts with a capital letter" pattern="^([A-Z])[a-z' ]{1,}[ ]([A-Z])[a-z' ]{1,}" title="Please your name and username"--%>
                <td><input type="text" required name="userName"/></td>
            </tr>

            <tr>
                <td>EmailAddress</td>
                <td><input type="text" required name="emailAddress"/></td>
            </tr>

            <tr>
                <td>Password</td>
                <td><input type="password" title="Password has to consist of at least four symbols and max ten" pattern="^([a-zA-Z0-9@*#]{4,10})$" required name="password"/></td>
            </tr>
        </table>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>

</html>
