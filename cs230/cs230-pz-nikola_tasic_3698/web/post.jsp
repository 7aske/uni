<%@ page import="database.entity.BlogPost" %>
<%@ page import="database.dao.BlogPostDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>Starter Template - Materialize</title>
    <jsp:include page="include/head.jsp"/>
    <jsp:include page="include/marked.jsp"/>
</head>
<body>
<jsp:include page="include/nav.jsp"/>
<%
    String slug = (String) request.getAttribute("postSlug");
    if (slug == null) {
        response.sendRedirect(request.getContextPath());
    }
    BlogPost blogPost = new BlogPostDAO().findBySlug(slug);
    if (blogPost == null) {
        response.sendRedirect(request.getContextPath());
    }
    assert blogPost != null;
    request.setAttribute("post", blogPost);
%>
<nav class="light-blue lighten-2">
    <div class="nav-wrapper container">
        <div class="col s12">
            <a href="${pageContext.request.contextPath}" class="breadcrumb">
                Home
            </a>
            <a href="${pageContext.request.contextPath}/post/${pageContext.request.getAttribute("post").slug}"
               class="breadcrumb">
                <%=blogPost.getTitle()%>
            </a>
        </div>
    </div>
</nav>
<div class="section no-pad-bot">
    <div class="container">
        <br><br>
        <h2><%= blogPost.getTitle()%>
        </h2>
    </div>
</div>
<div class="container">
    <div class="section post-body">
        <%=blogPost.getBody()%>
    </div>
    <br><br>
</div>
<jsp:include page="include/footer.jsp"/>
<script type="text/javascript">
    const postBody = document.querySelector(".post-body");
    postBody.innerHTML = marked(postBody.innerHTML, {
        "baseUrl": null,
        "breaks": false,
        "gfm": true,
        "headerIds": true,
        "mangle": true,
        "sanitize": false,
        "smartLists": true,
        "smartypants": true,
        "xhtml": false
    });
</script>
</body>
</html>

