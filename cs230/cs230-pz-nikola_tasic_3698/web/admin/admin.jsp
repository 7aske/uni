<%@ page import="com.example.cms.database.entity.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.cms.database.dao.PostDAO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('lang')}"/>
<fmt:setBundle basename="i18n/strings"/>

<!DOCTYPE html>
<html lang="<%=session.getAttribute("lang")%>">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title><fmt:message key="admin.title"/></title>
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
    List<Post> postList = new PostDAO().findAll();
    pageContext.setAttribute("blogPostList", postList);
%>
<nav class="light-blue lighten-2">
    <div class="nav-wrapper container">
        <div class="col s12">
            <a href="${pageContext.request.contextPath}" class="breadcrumb">
                <fmt:message key="nav.home"/>
            </a>
            <a href="${pageContext.request.contextPath}/admin/admin.jsp"
               class="breadcrumb">
                <fmt:message key="nav.posts"/>
            </a>
        </div>
    </div>
</nav>
<div class="container">
    <br/>
    <a class="btn orange" href="${pageContext.request.contextPath}/admin/post/edit"><i class="material-icons right">note_add</i><fmt:message key="admin.new_post"/></a>
    <ul class="collection">
        <c:forEach items="${blogPostList}" var="post">
            <jsp:include page="../fragment/adminPostListItem.jsp">
                <jsp:param name="title" value="${post.title}"/>
                <jsp:param name="slug" value="${post.slug}"/>
                <jsp:param name="idPost" value="${post.idPost}"/>
                <jsp:param name="excerpt" value="${post.excerpt}"/>
                <jsp:param name="published" value="${post.published}"/>
            </jsp:include>
        </c:forEach>
    </ul>
</div>
<br><br>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>
