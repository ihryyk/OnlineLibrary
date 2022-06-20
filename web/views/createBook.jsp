<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ihorb
  Date: 02.03.2022
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
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
    <form action="${pageContext.request.contextPath}/admin/createBook" method="post">
        <table>

            <tr>
                <td>Publishing house</td>
                <td><input type="text" required name="publishingHouse"/></td>
            </tr>
            <tr>
                <td>UA Title</td>
                <td><input type="text" required name="uaTitle"/></td>
            </tr>
            <tr>
                <td>UA Author</td>
                <td><input type="text" required name="uaAuthor"/></td>
            </tr>

            <tr>
                <td>ENG Title</td>
                <td><input type="text" required name="engTitle"/></td>
            </tr>
            <tr>
                <td>ENG Author</td>
                <td><input type="text" required name="engAuthor"/></td>
            </tr>

            <tr>
                <td>Year</td>
                <td><input type="text" maxlength="4" required name="year"/></td>
            </tr>

            <tr>
                <td>Number</td>
                <td><input type="text" required name="number"/></td>
            </tr>
        </table>
        <input type="submit" value="Submit"/>
    </form>
</div>

</body>

</html>