
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
    <h1><fmt:message key="Passes"/></h1>

    <table>
        <colgroup>
            <col span="6">
        </colgroup>
        <tr>
            <th><fmt:message key="PassType"/></th>
            <th><fmt:message key="PassStartDay"/></th>
            <th><fmt:message key="PassEndDay"/></th>
            <th><fmt:message key="books"/></th>
            <th><fmt:message key="Penalty"/></th>
            <th><fmt:message key="passStatus"/></th>
        </tr>

        <c:forEach var="pass" items="${requestScope.passes}">
            <tr>
                <td>
                    <c:out value="${pass.order.bookOption}"/>
                </td>
                <td>
                    <c:out value="${pass.startDate}"/>
                </td>
                <td>
                    <c:out value="${pass.endDate}"/>
                </td>
                <td>
                    <c:forEach var="passBook" items="${pass.getOrder().getOrderBooks()}">
                        <br> <fmt:message key="title"/>:
                        <c:out value="${ passBook.bookTranslations.get(0).title}"/>
                        <br><fmt:message key="author"/>:
                        <c:out value="${passBook.bookTranslations.get(0).author}"/>
                        <br> <fmt:message key="publishingHouse"/>:
                        <c:out value="${passBook.publishingHouse}"/>
                        <br> <fmt:message key="Year"/>:
                        <c:out value="${passBook.year}"/>
                        <br>
                    </c:forEach>
                    <c:if test="${pass.penalty!=0}">
                    <td>
                        <h2 style="color: #d91717"><c:out value="${pass.penalty}"/> <fmt:message key="hryvnya"/></h2>
                    </td>
                    </c:if>
                    <c:if test="${pass.penalty==0}">
                    <td>
                        <h2 style="color: #d91717"><fmt:message key="Absent"/></h2>
                    </td>
                    </c:if>
                </td>
                <td>
                    <c:out value="${pass.passStatus}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>

</html>