<%@ page import="database.entity.BlogPost" %>
<%@ page import="java.util.List" %>
<%@ page import="database.dao.BlogPostDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>Blog - Admin</title>
    <jsp:include page="../include/head.jsp"/>
    <style>
        body {
            min-height: 100vh !important;
            display: flex;
            flex-direction: column;
        }
        .container {
            flex-grow: 4;
        }
    </style>
</head>
<body>
<jsp:include page="../include/nav.jsp"/>
<%
    List<BlogPost> blogPostList = new BlogPostDAO().findAll();
    pageContext.setAttribute("blogPostList", blogPostList);
%>
<nav class="light-blue lighten-2">
    <div class="nav-wrapper container">
        <div class="col s12">
            <a href="${pageContext.request.contextPath}" class="breadcrumb">
                Home
            </a>
            <a href="${pageContext.request.contextPath}/admin/admin.jsp"
               class="breadcrumb">
                Admin
            </a>
        </div>
    </div>
</nav>
<div class="container">
    <br/>
    <ul class="collection">
        <c:forEach items="${blogPostList}" var="post">
            <jsp:include page="../fragment/adminPostListItem.jsp">
                <jsp:param name="title" value="${post.title}"/>
                <jsp:param name="idBlogPost" value="${post.idBlogPost}"/>
                <jsp:param name="preview" value="${post.preview}"/>
            </jsp:include>
        </c:forEach>
    </ul>
</div>
<br><br>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>
