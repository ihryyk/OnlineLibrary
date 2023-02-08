
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="message"/>
<!DOCTYPE html>
<html>

<head>
    <title>Library</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles/myPass.css">
    <style>
        <%@include file="styles/myPass.css"%>
    </style>
</head>

<body>

<div class="container_pass">
    <h1><fmt:message key="MyOrders"/></h1>

<table>
    <colgroup>
        <col span="5">
    </colgroup>
    <tr>
        <th>User name</th>
        <th>Recording date</th>
        <th>Order Books</th>
        <th>Order type</th>
        <th>Order status</th>
    </tr>


<c:forEach var="order" items="${requestScope.orders}">

    <tr>
        <td>
            <c:out value="${order.user.name}"/>
        </td>

        <td>
            <c:out value="${order.arrivalDate}"/>
        </td>

        <td>
            <c:forEach var="orderBook" items="${order.orderBooks}">
                <br> <fmt:message key="title"/>:
                <c:out value="${ orderBook.bookTranslations.get(0).title}"/>
                <br><fmt:message key="author"/>:
                <c:out value="${orderBook.bookTranslations.get(0).author}"/>
                <br> <fmt:message key="publishingHouse"/>:
                <c:out value="${orderBook.publishingHouse}"/>
                <br> <fmt:message key="Year"/>:
                <c:out value="${orderBook.year}"/>
                <br>
            </c:forEach>
        </td>

        <td>
            <c:out value="${order.bookOption}"/>
        </td>

        <td>
            <c:out value="${order.getOrderSatus().name()}"/>
        </td>
    </tr>
</c:forEach>
</table>
</div>

</body>

</html>
