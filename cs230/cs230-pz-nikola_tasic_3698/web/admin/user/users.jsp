<%@ page import="java.util.List" %>
<%@ page import="com.example.cms.database.dao.UserDAO" %>
<%@ page import="com.example.cms.database.entity.User" %>
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
    <title><fmt:message key="admin.user.users.title"/></title>
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
<%
    UserDAO userDAO = new UserDAO();
    List<User> userList = userDAO.findAll();
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
            <a href="${pageContext.request.contextPath}/admin/user/users.jsp"
               class="breadcrumb">
                <fmt:message key="nav.users"/>
            </a>
        </div>
    </div>
</nav>
<div class="container">
    <br/>
    <a class="btn orange" href="${pageContext.request.contextPath}/admin/user/edit"><i class="material-icons right">note_add</i>
        <fmt:message key="admin.user.users.add_user"/>
    </a>
    <ul class="collection with-header">
        <li class="collection-header"><h5>Users</h5></li>
        <%for (User user : userList) { %>
        <li class="collection-item avatar">
            <i class="material-icons circle <%=user.getActive() ? "teal lighten-2" : "grey"%>">verified_user</i>
            <span class="title"><%=user.getUsername()%></span><span
                class="grey-text">&nbsp;&nbsp;<%=user.getDisplayName()%></span>
            <a href="${pageContext.request.contextPath}/admin/user/edit/<%=user.getIdUser()%>"
               class="secondary-content"><i class="material-icons orange-text">edit</i></a>
        </li>
        <% } %>
    </ul>
</div>
<br><br>
<jsp:include page="../../include/footer.jsp"/>
</body>
</html>
