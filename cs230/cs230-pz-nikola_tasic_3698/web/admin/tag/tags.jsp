<%@ page import="database.entity.Tag" %>
<%@ page import="java.util.List" %>
<%@ page import="database.dao.TagDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>Admin - Tags</title>
    <jsp:include page="../../include/head.jsp"/>
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
<jsp:include page="../../include/nav.jsp"/>
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
            <a href="${pageContext.request.contextPath}/admin/tag/tags.jsp"
               class="breadcrumb">
                Tags
            </a>
        </div>
    </div>
</nav>
<%
    List<Tag> tagList = new TagDAO().findAll();
    pageContext.setAttribute("tagList", tagList);
%>
<div class="container">
    <br/>
    <div class="row">
        <h5>Tags</h5>
        <br/>
        <div>
            <c:forEach items="${tagList}" var="tag">
                <form style="display: inline-block"
                      method="post"
                      action="${pageContext.request.contextPath}/admin/tag/delete/${tag.idTag}">
                    <button style="border: 0" type="submit" class="chip">
                            ${tag.name}
                        <i class="material-icons"
                           style="font-size: 1.25em; vertical-align: middle; line-height: 1.25em;">close</i>
                    </button>
                </form>
            </c:forEach>
        </div>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/admin/tag">
        <div class="row">
            <div class="input-field col s6">
                <input id="name" type="text" name="name" class="validate">
                <label for="name">Tag Name</label>
                <button class="btn orange" type="submit">Add</button>
            </div>
        </div>

    </form>
</div>
<br><br>
<jsp:include page="../../include/footer.jsp"/>
</body>
</html>
