<%@ taglib prefix="dt" uri="/WEB-INF/todayDate.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="message"/>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>

<head>
    <title>Library</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles/order.css">
    <style>
        <%@include file="styles/order.css"%>
    </style>
</head>
<body>
<div class="container">
    <div class="container_books">
        <h1><fmt:message key="books"/></h1>
        <ul>
            <form action="${pageContext.request.contextPath}/user/createOrder" method="get" id="2">
                <c:forEach items="${sessionScope.books}" var="book">
                    <li>
                        <div class="title">

                            <fmt:message key="title"/>
                            <c:out value="${book.bookTranslations.get(0).title}"/>
                        </div>
                        <div class="author">
                            <fmt:message key="author"/>
                            <c:out value="${book.bookTranslations.get(0).author}"/>
                        </div>
                        <div class="publishing">
                            <fmt:message key="publishingHouse"/>
                            <c:out value="${book.publishingHouse}"/>
                        </div>
                        <div class="date">
                            <fmt:message key="Year"/>
                            <c:out value="${book.year}"/>
                        </div>
                        <div>
                            <fmt:message key="number"/>
                            <c:out value="${book.number}"/>
                        </div>
                        <input type="checkbox" name="deleteId" value="${book.id}" class="checkBox">
                    </li>
                </c:forEach>
            </form>
            <input type="submit" value=" <fmt:message key="refreshOrderList"/>" form="2" class="submit"
                   style="display: none;">

            <c:if test="${sessionScope.books.size()==0}">
                <a class="profile" href=${pageContext.request.contextPath}/>
                <h1>
                    <fmt:message key="noChooseBook"/>
                </h1>
                </a>
            </c:if>
        </ul>
    </div>
    <div class="container_order">
        <h1><fmt:message key="makeOrder"/></h1>
        <form action="${pageContext.request.contextPath}/user/createOrder" method="post">
            <table>
                <tr>
                    <td></td>
                    <td><label>
                        <fmt:message key="arriveDate"/>
                        <input type="date" MIN="<dt:todayDate/>" required name="arrivalDate"/>
                    </label></td>
                </tr>
                <tr>
                    <input type="radio" id="contactChoice1" name="orderType" checked value="SUBSCRIPTION">
                    <label for="contactChoice1"><fmt:message key="subscription"/></label>

                    <input type="radio" id="contactChoice2" name="orderType" value="READING_ROOM">
                    <label for="contactChoice2"><fmt:message key="readingRoom"/></label>
                </tr>
            </table>
            <c:if test="${sessionScope.books.size()!=0}">
                <h1>
                    <input type="submit" value="<fmt:message key="Submit"/>"/>
                </h1>
            </c:if>
        </form>
    </div>
</div>

<script>
    let inputCheck = document.querySelectorAll(".checkBox");
    inputCheck.forEach(box => {
        box.addEventListener("change", () => {
            let checkboxes = document.querySelectorAll('input[type="checkbox"]');
            let checkedOne = Array.prototype.slice.call(checkboxes).some(x => x.checked);
            if (checkedOne) {
                document.querySelector(".submit").style.display = "block";
                console.log("works");
            } else {
                document.querySelector(".submit").style.display = "none";
                console.log("worksElse");
            }
        })
    })
</script>
</body>
</html>

