<%@ page import="com.example.cms.database.entity.Post" %>
<%@ page import="com.example.cms.database.dao.PostDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.cms.util.UrlUtil" %>
<%@ page import="com.example.cms.config.Config" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('lang')}"/>
<fmt:setBundle basename="i18n/strings"/>

<!DOCTYPE html>
<html lang="<%=session.getAttribute("lang")%>">
<head>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title><fmt:message key="index.title"/></title>
    <jsp:include page="include/head.jsp"/>
</head>
<body>
<jsp:include page="include/nav.jsp"/>
<div class="section no-pad-bot">
    <div class="container">
        <br><br>
        <h1 class="header center orange-text"><fmt:message key="index.jumbotron.title"/></h1>
        <div class="row center">
            <h5 class="header col s12 light"><fmt:message key="index.jumbotron.text"/></h5>
        </div>
    </div>
</div>
<%
    int postLimit = Integer.parseInt(Config.getProperties().getProperty("post-limit"));
    List<Post> posts;
    String tag = request.getParameter("tag");
    if (tag != null && !tag.equals("")) {
        posts = new PostDAO().findPublishedByTagName(UrlUtil.decodeValue(tag), postLimit);
    } else {
        posts = new PostDAO().findAllPublished(postLimit);
    }

    pageContext.setAttribute("posts", posts);
%>

<div class="row">
    <c:forEach items="${posts}" var="post">
        <jsp:include page="fragment/indexPost.jsp">
            <jsp:param name="title" value="${post.title}"/>
            <jsp:param name="excerpt" value="${post.excerpt}"/>
            <jsp:param name="idUser" value="${post.idUser.idUser}"/>
            <jsp:param name="slug" value="${post.slug}"/>
            <jsp:param name="datePosted" value="${post.datePosted}"/>
            <jsp:param name="tags"
                       value="${post.tags.stream().map(t -> t.name).reduce((l, r) -> l += ',' += r).orElse('')}"/>
            <jsp:param name="display_name" value="${post.idUser.displayName}"/>
        </jsp:include>
    </c:forEach>
</div>
<br><br>
<jsp:include page="include/footer.jsp"/>
</body>
</html>
