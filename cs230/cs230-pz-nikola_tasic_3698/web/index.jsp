<%@ page import="database.entity.BlogPost" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="database.dao.BlogPostDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>Starter Template - Materialize</title>

    <jsp:include page="include/head.jsp"/>
</head>
<body>
<jsp:include page="include/nav.jsp"/>
<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <br><br>
        <h1 class="header center orange-text">Blog Template</h1>
        <div class="row center">
            <h5 class="header col s12 light">A modern responsive front-end framework based on Material Design</h5>
        </div>
    </div>
</div>
<div class="container">
    <div class="section">
        <%
            List<BlogPost> blogPosts = new BlogPostDAO().findAll();
            pageContext.setAttribute("blogPosts", blogPosts);
        %>
        <div class="row">
            <c:forEach items="${blogPosts}" var="post">
                <jsp:include page="fragment/indexPost.jsp">
                    <jsp:param name="title" value="${post.title}"/>
                    <jsp:param name="preview" value="${post.preview}"/>
                    <jsp:param name="author" value="${post.author}"/>
                    <jsp:param name="slug" value="${post.slug}"/>
                    <jsp:param name="datePosted" value="${post.datePosted}"/>
                </jsp:include>
            </c:forEach>
        </div>
    </div>
    <br><br>
</div>
<jsp:include page="include/footer.jsp"/>
</body>
</html>

