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
    <link rel="stylesheet" href="styles/adminIndex.css">
    <style>
        <%@include file="styles/adminIndex.css"%>
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

<c:if test="${sessionScope.userName!=null}">
    <c:out value="${sessionScope.role}"/>
    <c:out value="${sessionScope.userName}"/>
    <a class="logout" href=<%=request.getContextPath()%>/logout>
        logout
    </a>
</c:if>

<div class="wrapper">
    <div class="container_book">
        <h2>Books</h2>
        <ul>
            <c:forEach items="${requestScope.books}" var="book">
                <li>
                    <div class="title">
                        <c:out value="${book.bookTranslations.get(0).title}"/> <c:out value="/"/>
                        <c:out value="${book.bookTranslations.get(1).title}"/>
                    </div>
                    <div class="author">
                        Author:
                        <c:out value="${book.bookTranslations.get(0).author}"/> <c:out value="/"/>
                        <c:out value="${book.bookTranslations.get(1).author}"/>
                    </div>

                    <div class="publishing">
                        Publishing house:
                        <c:out value="${book.publishingHouse}"/>
                    </div>
                    <div class="date">
                        Year:
                        <c:out value="${book.year}"/>
                    </div>
                    <div>
                        Number:
                        <c:out value="${book.number}"/>
                    </div>

                    <div class="btns">

                        <div>
                            <a class="btn_link"
                               href="${pageContext.request.contextPath}/admin/updateBook?id=${book.id}
                               &year=${book.year}&publishingHouse=${book.publishingHouse}
                               &ENGtitle=${book.bookTranslations.get(0).title}
                               &ENGauthor=${book.bookTranslations.get(0).author}
                               &UAtitle=${book.bookTranslations.get(1).title}
                               &UAauthor=${book.bookTranslations.get(1).author}&number=${book.number}">
                                update
                            </a>
                        </div>


                        <form action="${pageContext.request.contextPath}/admin/deleteBook?bookId=${book.id}"
                              method="post">
                            <input class="btn_link btn_book" type="submit" value="delete">
                        </form>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <a class="btn_link" href=<%=request.getContextPath()%>/admin/createBook>
            Create Book
        </a>
        <c:forEach var="i" begin="0" end="${requestScope.count/5}">
            <a href=<%=request.getContextPath()%>/admin?page=${i}>
                <c:out value="${i+1}"/>

            </a>
        </c:forEach>
    </div>

    <div class="container_librarians">
        <h2>Librarians</h2>

        <table>
            <colgroup>
                <col span="5">
            </colgroup>
            <tr>
                <th>Librarian id</th>
                <th>Librarian name</th>
                <th>Librarian email address</th>
                <th></th>
            </tr>
            <c:forEach items="${requestScope.librarians}" var="librarian">
                <tr>
                    <td>
                        <c:out value="${librarian.id}"/>
                    </td>
                    <td>
                        <c:out value="${librarian.name}"/>
                    </td>
                    <td>
                        <c:out value="${librarian.emailAddress}"/>
                    </td>
                    <td>
                        <form
                                action="${pageContext.request.contextPath}/admin/deleteLibrarian?librarianId=${librarian.id}"
                                method="post">
                            <input type="submit" value="delete" class="btn_table">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <td>
            <a class="btn_librarian btn_link" href=${pageContext.request.contextPath}/admin/createLibrarian>
                create librarian
            </a>
        </td>

        <table>
            <colgroup>
                <col span="5">
            </colgroup>
            <tr>
                <th>User id</th>
                <th>User name</th>
                <th>User email address</th>
                <th>User status</th>
                <th></th>
            </tr>

            <c:forEach items="${requestScope.users}" var="user">
                <c:if test="${user.getRole().getName()=='user'}">
                    <tr>
                    <td>
                        <c:out value="${user.id}"/>
                    </td>
                    <td>
                        <c:out value="${user.name}"/>
                    </td>
                    <td>
                        <c:out value="${user.emailAddress}"/>
                    </td>
                    <td>
                        <c:out value="${user.lockStatus}"/>
                    </td>

                    <c:if test="${user.lockStatus=='UNLOCKED'}">
                        <td>
                            <form action="${pageContext.request.contextPath}/admin/blockUser?userId=${user.id}"
                                  method="post">
                                <input type="submit" value="block">
                            </form>
                        </td>
                    </c:if>

                    <c:if test="${user.lockStatus=='BLOCKED'}">
                        <td>
                            <form action="${pageContext.request.contextPath}/admin/unlockUser?userId=${user.id}"
                                  method="post">
                                <input type="submit" value="unlock">
                            </form>
                        </td>
                        </tr>
                    </c:if>

                </c:if>

            </c:forEach>
        </table>
    </div>
</div>
</body>

</html>


