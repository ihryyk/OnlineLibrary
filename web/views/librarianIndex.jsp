
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "dt" uri = "/WEB-INF/todayDate.tld"%>
<!DOCTYPE html>
<html>

<head>
    <title>Library</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles/librarianIndex.css">
    <style>
        <%@include file="styles/librarianIndex.css"%>
    </style>
</head>

<body>


<c:if test="${sessionScope.userName!=null}">
    <c:out value="${sessionScope.role}"/>
    <c:out value="${sessionScope.userName}"/>
    <a class="logout" href=${pageContext.request.contextPath}/logout>
        logout
    </a>
</c:if>

<div class="container_orders">
    <h1>Orders</h1>

    <ul>


        <c:forEach var="order" items="${requestScope.orders}">

            <li>
                <div class="order_details">
                    <div class="title">
                        Information about user:
                    </div>
                    User id:
                    <c:out value="${order.user.id}"/>
                    <br>User name:
                    <c:out value="${order.user.name}"/>
                    <br>Email adress:
                    <c:out value="${order.user.emailAddress}"/>
                    <div class="title">
                        Information about order:
                    </div>
                    Order id:
                    <c:out value="${order.id}"/>
                    <br>According day
                    <c:out value="${order.arrivalDate}"/>
                    <br> Order type: <c:out value="${order.bookOption}"/>
                    <div>
                        Order status: <c:out value="${order.getOrderSatus().name()}"/>
                    </div>

                </div>
                <br>
                <div class="title">
                    Books:
                </div>
                <c:forEach var="orderBook" items="${order.orderBooks}">
                    Title:
                    <c:out value="${orderBook.bookTranslations.get(0).title}"/>
                    <div class="author">
                        Author:
                        <c:out value="${orderBook.bookTranslations.get(0).author}"/>
                    </div>
                    <div class="publishing">
                        Publishing house:
                        <c:out value="${orderBook.publishingHouse}"/>
                    </div>
                    <div class="date">
                        Year
                        <c:out value="${orderBook.year}"/>
                    </div>
                    <br>
                </c:forEach>
                <c:if test="${sessionScope.userName!=null}">
                    <c:if
                            test="${order.getOrderSatus().name()!='SUBMITTED' and order.getOrderSatus().name()!='CANCELED' }">
                        <div class="btns_order">
                            <form action="${pageContext.request.contextPath}/librarian/cancelOrder?cancelId=${order.id}"
                                  method="post">
                                <input type="submit" value="cancel">
                            </form>
                            <form action="${pageContext.request.contextPath}/librarian/applyOrder?applyId=${order.id}"
                                  method="post">
                                <tr>
                                    <td></td>
                                    <td><label>
                                        Start order day:
                                        <input type="date" min="<dt:todayDate/>" required name="startDate"/>
                                    </label></td>

                                    <td><label>
                                        End order day:
                                        <input type="date" min="<dt:todayDate/>" required name="endDate"/>
                                    </label></td>
                                </tr>
                                <input type="submit" value="apply">
                            </form>
                        </div>
                    </c:if>
                </c:if>
            </li>
        </c:forEach>
    </ul>
</div>

<div class="container_passes">
    <h1>Passes</h1>

    <table>
        <colgroup>
            <col span="9">
        </colgroup>
        <tr>
            <th>Pass id</th>
            <th>User id</th>
            <th>User name</th>
            <th>Pass type</th>
            <th>Pass start day</th>
            <th>Pass end day</th>
            <th>Books</th>
            <th>Penalty</th>
            <th></th>
        </tr>
        <c:forEach var="pass" items="${requestScope.passes}">
            <tr>
                <td>
                    <c:out value="${pass.id}"/>
                </td>
                <td>
                    <c:out value="${pass.order.user.id}"/>
                </td>
                <td>
                    <c:out value="${pass.order.user.name}"/>
                </td>
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
                    <c:forEach var="passBook" items="${pass.order.orderBooks}">

                        <br> Title:
                        <c:out value="${ passBook.bookTranslations.get(0).title}"/>
                        <br>Author:
                        <c:out value="${passBook.bookTranslations.get(0).author}"/>
                        <br> Publishing house:
                        <c:out value="${passBook.publishingHouse}"/>
                        <br> Year:
                        <c:out value="${passBook.year}"/>
                        <br>
                    </c:forEach>
                </td>
                <c:if test="${pass.penalty!=0}">
                    <td>
                        <c:out value="${pass.penalty} UAH"/>
                    </td>
                </c:if>
                <c:if test="${pass.penalty==0}">
                    <td>
                        Absent
                    </td>
                </c:if>
                <td>
                    <c:if test="${pass.passStatus=='ACTIVE'}">
                        <form action="${pageContext.request.contextPath}/librarian/finishPass?finishId=${pass.id}"
                              method="post">
                            <input type="submit" value="finish">
                        </form>
                    </c:if>
                </td>
            </tr>

        </c:forEach>
    </table>
</div>
</body>

</html>