<%@ page import="com.example.cms.database.entity.Post" %>
<%@ page import="com.example.cms.database.dao.PostDAO" %>
<%@ page import="com.example.cms.config.Config" %>
<%@ page import="com.example.cms.database.entity.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.example.cms.database.entity.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('lang')}"/>
<fmt:setBundle basename="i18n/strings"/>

<%
    String slug = (String) request.getAttribute("postSlug");
    if (slug == null) {
        response.sendRedirect(request.getContextPath());
        return;
    }
    Post post = new PostDAO().findBySlug(slug);
    if (post == null) {
        response.sendRedirect(request.getContextPath());
        return;
    }

    request.setAttribute("post", post);

    List<Comment> commentList = post.getCommentList();
    request.setAttribute("commentList", commentList);
%>
<%
    String username = (String) session.getAttribute("username");
    Long idUser = (Long) session.getAttribute("idUser");
    Collection<Role> roles = (Collection<Role>) session.getAttribute("roles");
    boolean loggedIn = username != null && idUser != null && !roles.isEmpty();
%>
<!DOCTYPE html>
<html lang="<%=session.getAttribute("lang")%>">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>
        <fmt:message key="post.title"/><%= post.getTitle()%>
    </title>
    <jsp:include page="include/head.jsp"/>
    <jsp:include page="include/markdown.jsp"/>
    <style>
        .post-body img {
            max-width: 100%;
        }

        .show-less, .show-more {
            background-color: transparent;
            box-shadow: 0 0 0 transparent;
            color: #ff9800;
            border: none;
            text-transform: uppercase;
            cursor: pointer;
            margin: 0 !important;
        }

        .show-less:active, .show-more:active {
            background-color: transparent;
        }

        .comment-body.truncate ~ .show-less {
            display: none;
        }

        .comment-body:not(.truncate) ~ .show-more {
            display: none;
        }
    </style>
</head>
<body>
<jsp:include page="include/nav.jsp"/>
<nav class="light-blue lighten-2">
    <div class="nav-wrapper container">
        <div class="col s12">
            <a href="${pageContext.request.contextPath}" class="breadcrumb">
                <fmt:message key="nav.home"/>
            </a>
            <a href="${pageContext.request.contextPath}/post/${pageContext.request.getAttribute("post").slug}"
               class="breadcrumb">
                <%= post.getTitle()%>
            </a>
        </div>
    </div>
</nav>
<div class="section no-pad-bot">
    <div class="container">
        <h2>
            <% if (loggedIn) { %>
            <a class="btn orange"
               href="<%=request.getContextPath()%>/admin/post/edit/<%=post.getIdPost()%>">
                <fmt:message key="post.edit"/><i class="material-icons right">edit</i>
            </a>
            <% } %>
        </h2>
    </div>
</div>
<div class="container">
    <span><%=post.getDatePosted()%></span>
    <div class="post-body"><%=post.getBody()%>
    </div>
    <br/>
    <span>Posted on <%=post.getDatePosted()%></span> by <span
        class="orange-text"><%=post.getIdUser().getDisplayName()%></span>
    <br/><br/>
</div>
<div class="container">
    <h4><fmt:message key="post.comments.title"/></h4>
    <br/>
    <jsp:include page="fragment/errorList.jsp"/>
    <jsp:include page="fragment/commentForm.jsp">
        <jsp:param name="idPost" value="${post.idPost}"/>
    </jsp:include>
    <c:forEach items="${commentList}" var="comment">
        <jsp:include page="fragment/comment.jsp">
            <jsp:param name="idComment" value="${comment.idComment}"/>
            <jsp:param name="idPost" value="${comment.idPost}"/>
            <jsp:param name="commenterEmail" value="${comment.commenterEmail}"/>
            <jsp:param name="commenterName" value="${comment.commenterName}"/>
            <jsp:param name="body" value="${comment.body}"/>
            <jsp:param name="dateCommented" value="${comment.dateCommented}"/>
        </jsp:include>
    </c:forEach>
</div>

<jsp:include page="include/footer.jsp"/>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", () => {
        const postBody = document.querySelector(".post-body");
        // const converter = new showdown.Converter();
        // postBody.innerHTML = converter.makeHtml(postBody.innerHTML);
        postBody.innerHTML = marked(postBody.innerHTML, {
            "baseUrl": null,
            "breaks": false,
            "gfm": true,
            "headerIds": true,
            "highlight": null,
            "mangle": true,
            "sanitize": false,
            "smartLists": true,
            "smartypants": true,
            "xhtml": false
        });
    });
</script>
</body>
</html>

