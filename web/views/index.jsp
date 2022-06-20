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
    <link rel="stylesheet" type="text/css" href="styles/index.css">
    <style>
        <%@include file="styles/index.css"%>
    </style>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
<c:if test="${sessionScope.popUps=='registration'}">
    <script>

        Swal.fire({
            icon: 'success',
            title: '<fmt:message key="EverythingOK"/>',
            text: '<fmt:message key="RegisteredInAccount"/>',
        })
    </script>
    ${sessionScope.remove("popUps")}
</c:if>

<c:if test="${sessionScope.popUps=='order'}">
    <script>

        Swal.fire({
            icon: 'success',
            title: '<fmt:message key="EverythingOK"/>',
            text: '<fmt:message key="AcceptedOrder"/>',
        })
    </script>
    ${sessionScope.remove("popUps")}
    ${sessionScope.remove("books")}
</c:if>

<c:if test="${sessionScope.popUps=='profile'}">
    <script>

        Swal.fire({
            icon: 'success',
            title: '<fmt:message key="EverythingOK"/>',
            text: '<fmt:message key="UpdateProfile"/>',
        })
    </script>
    ${sessionScope.remove("popUps")}
</c:if>


<header>
    <nav>
        <c:if test="${sessionScope.role!='user'}">
            <a href=${pageContext.request.contextPath}/login>
                <fmt:message key="Login"/>
            </a>
        </c:if>

        <c:if test="${sessionScope.role=='user'}">
        <c:if test="${sessionScope.role=='user' and sessionScope.lockStatus=='BLOCKED'}">
            <small>${sessionScope.userName}</small>
            <small>block</small>
        </c:if>
            <c:if test="${sessionScope.role=='user' and sessionScope.lockStatus=='UNLOCKED'}">
                <c:out value="${sessionScope.userName}"/>
            </c:if>
            <a href=<%=request.getContextPath()%>/logout>
                <fmt:message key="Logout"/>
            </a>
            <c:if test="${sessionScope.role=='user' and sessionScope.lockStatus=='UNLOCKED'}">
                <a class="profile" href=${pageContext.request.contextPath}/user/myProfile>
                    <fmt:message key="MyProfile"/>
                </a>
            </c:if>
            <a class="profile" href=${pageContext.request.contextPath}/user/myPasses>
                <fmt:message key="MyPasses"/>
            </a>

            <a class="profile" href=${pageContext.request.contextPath}/user/myOrders>
                <fmt:message key="MyOrders"/>
            </a>

        </c:if>
        <div class="language">

            <c:if test="${sessionScope.language=='eng'}">
                <a class="UA" href=${pageContext.request.contextPath}/?lang=ua>
                    UA
                </a>
            </c:if>
            <c:if test="${sessionScope.language=='ua'}">
                <a class="ENG" href=${pageContext.request.contextPath}/?lang=eng>
                    ENG
                </a>
            </c:if>
        </div>
    </nav>
</header>

<div class="forms">
    <form action="${pageContext.request.contextPath}/bookList" method="get" class="search">
        <input type="text" placeholder="<fmt:message key="Search"/>" required name="parameter">

        <input type="radio" id="contactChoice1" name="searchBy" value="title" checked>
        <label for="contactChoice1">
            <fmt:message key="title"/>
        </label>

        <input type="radio" id="contactChoice2" name="searchBy" value="author">
        <label for="contactChoice2">
            <fmt:message key="author"/>
        </label>

        <input type="submit" value="<fmt:message key="Search" />" class="btn_submit"/>
    </form>
</div>

<div class="wrapper">

    <div class="container_sort">
        <h2><fmt:message key="SortBy"/></h2>
        <form action="${pageContext.request.contextPath}/bookList" method="get" class="forms_form form_sort">
            <div>
                <input type="radio" id="contactChoice3" name="groupBy" value="title" checked>
                <label for="contactChoice3">
                    <fmt:message key="title"/>
                </label>
            </div>

            <div>
                <input type="radio" id="contactChoice4" name="groupBy" value="author">
                <label for="contactChoice4">
                    <fmt:message key="author"/>
                </label>
            </div>

            <div>
                <input type="radio" id="contactChoice5" name="groupBy" value="publishingHouse">
                <label for="contactChoice5">
                    <fmt:message key="publishingHouse"/>
                </label>
            </div>

            <div>
                <input type="radio" id="contactChoice6" name="groupBy" value="year">
                <label for="contactChoice6">
                    <fmt:message key="Year"/>
                </label>
            </div>

            <input type="submit" value="<fmt:message key="Sort" />" class="btn_submit"/>
        </form>
    </div>

    <div class="container">
        <a href=${pageContext.request.contextPath}/?default=true>
            <h1>
                <fmt:message key="books"/>
            </h1>
        </a>
        <ul style="list-style: none">
            <form action="${pageContext.request.contextPath}/user/createOrder" method="get" id="2">
                <ul>
                    <c:forEach items="${requestScope.books}" var="book">

                            <li>
                            <div class="title">
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
                            <c:if test="${book.number>0}">
                                <c:if test="${sessionScope.role=='user'and sessionScope.lockStatus!='BLOCKED'}">
                                    <input type="checkbox" name="id" value="${book.id}" class="checkBox">
                                </c:if>
                                </li>
                            </c:if>
                    </c:forEach>
                </ul>
            </form>
            <input type="submit" value="<fmt:message key="Submit"/>" form="2" class="submit" style="display: none;">
        </ul>
        <c:forEach var="i" begin="0" end="${requestScope.count/5}">
            <c:choose>
                <c:when test="${requestScope.searchBy=='author'}">
                    <a href=${pageContext.request.contextPath}/bookList?page=${i}&searchBy=author>
                        <c:out value="${i+1}"/>
                    </a>
                </c:when>
                <c:when test="${requestScope.searchBy=='title'}">
                    <a href=${pageContext.request.contextPath}/bookList?page=${i}&searchBy=title>
                        <c:out value="${i+1}"/>
                    </a>
                </c:when>
                <c:when test="${requestScope.groupBy=='title'}">
                    <a href=${pageContext.request.contextPath}/bookList?page=${i}&groupBy=title>
                        <c:out value="${i+1}"/>
                    </a>
                </c:when>
                <c:when test="${requestScope.groupBy=='year'}">
                    <a href=${pageContext.request.contextPath}/bookList?page=${i}&groupBy=year>
                        <c:out value="${i+1}"/>
                    </a>
                </c:when>
                <c:when test="${requestScope.groupBy=='author'}">
                    <a href=${pageContext.request.contextPath}/bookList?page=${i}&groupBy=author>
                        <c:out value="${i+1}"/>
                    </a>
                </c:when>
                <c:when test="${requestScope.groupBy=='publishingHouse'}">
                    <a href=${pageContext.request.contextPath}/bookList?page=${i}&groupBy=publishingHouse>
                        <c:out value="${i+1}"/>
                    </a>
                </c:when>
                <c:otherwise>
                    <a href=${pageContext.request.contextPath}/?page=${i}>
                        <c:out value="${i+1}"/>
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
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