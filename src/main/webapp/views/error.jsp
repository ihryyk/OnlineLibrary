<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ihorb
  Date: 19.03.2022
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Library</title>
    <script>
        <%@include file="styles/error.css"%>
    </script>
</head>
<body>

<div class="page-wrap d-flex flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 text-center">
                <span class="display-1 d-block">404</span>
                <div class="mb-4 lead">The page you are looking for was not found.</div>

                <c:choose>
                    <c:when test="${sessionScope.role=='admin'}">
                        <a href="/admin" class="btn btn-link">Back to Home</a>
                    </c:when>
                    <c:when test="${sessionScope.role=='librarian'}">
                        <a href="/librarian" class="btn btn-link">Back to Home</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/" class="btn btn-link">Back to Home</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
</html>
