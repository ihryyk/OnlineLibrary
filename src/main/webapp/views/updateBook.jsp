<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
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
</head>

<body>


<div class="container">
    <form action="${pageContext.request.contextPath}/admin/updateBook" method="post">
        <table>
            <tr>
                <td>Id</td>
                <td><input type="text" name="bookId" value="${param.id}"/></td>
            </tr>

            <tr>
                <td>Publishing house</td>
                <td><input type="text" name="publishingHouse" value="${param.publishingHouse}"/>
                </td>

            </tr>
            <tr>
                <td>UA Title</td>
                <td><input type="text" name="uaTitle"  value="${param.UAtitle}"/></td>
            </tr>
            <tr>
                <td>UA Author</td>
                <td><input type="text" name="uaAuthor" value="${param.UAauthor}" /></td>
            </tr>

            <tr>
                <td>ENG Title</td>
                <td><input type="text" name="engTitle" value="${param.ENGtitle}"/></td>
            </tr>
            <tr>
                <td>ENG Author</td>
                <td><input type="text" name="engAuthor" value="${param.ENGauthor}"/></td>
            </tr>

            <tr>
                <td>Year</td>
                <td><input type="text" maxlength="4" name="year" value="${param.year}"/></td>
            </tr>

            <tr>
                <td>Number</td>
                <td><input type="text" name="number" value="${param.number}"/></td>
            </tr>
        </table>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>

</html>
