<%@ page import="com.example.cms.database.entity.Tag" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.cms.database.dao.TagDAO" %>
<%@ page import="com.example.cms.util.UrlUtil" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('lang')}"/>
<fmt:setBundle basename="i18n/strings"/>

<!DOCTYPE html>
<html lang="<%=session.getAttribute("lang")%>">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title><fmt:message key="admin.tag.tags.title"/></title>
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
                <fmt:message key="nav.home"/>
            </a>
            <a href="${pageContext.request.contextPath}/admin/admin.jsp"
               class="breadcrumb">
                <fmt:message key="nav.posts"/>
            </a>
            <a href="${pageContext.request.contextPath}/admin/tag/tags.jsp"
               class="breadcrumb">
                <fmt:message key="nav.tags"/>
            </a>
        </div>
    </div>
</nav>
<jsp:include page="../../fragment/errorList.jsp"/>
<%
    List<Tag> tagList = new TagDAO().findAll();
%>
<div class="container">
    <br/>
    <div class="row">
        <h5><fmt:message key="admin.tag.tags.title2"/></h5>
        <br/>
        <div>
            <% for (Tag tag : tagList) { %>
            <form style="display: inline-block" method="post"
                  action="${pageContext.request.contextPath}/admin/tag/delete/<%=tag.getIdTag()%>">
                <button style="border: 0" type="submit" class="chip">
                    <%=UrlUtil.decodeValue(tag.getName())%>
                    <i class="material-icons" style="font-size: 1.25em; vertical-align: middle; line-height: 1.25em;">close</i>
                </button>
            </form>
            <% } %>
        </div>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/admin/tag">
        <div class="row">
            <div class="input-field col s6">
                <input id="name" type="text" name="name" class="validate">
                <label for="name"><fmt:message key="admin.tag.tags.tag_name"/></label>
                <button class="btn orange" type="submit"><fmt:message key="admin.tag.tags.tag_add"/></button>
            </div>
        </div>
    </form>
</div>
<br><br>
<jsp:include page="../../include/footer.jsp"/>
</body>
</html>
