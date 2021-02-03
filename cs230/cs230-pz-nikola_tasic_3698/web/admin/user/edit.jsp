<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('lang')}"/>
<fmt:setBundle basename="i18n/strings"/>

<!DOCTYPE html>
<html lang="<%=session.getAttribute("lang")%>">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title><fmt:message key="admin.user.edit.title"/></title>
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
            <a href="${pageContext.request.contextPath}/admin/user/users.jsp"
               class="breadcrumb">
                <fmt:message key="nav.users"/>
            </a>
            <a href="#" class="breadcrumb">
                <fmt:message key="nav.edit"/>
            </a>
        </div>
    </div>
</nav>
<div class="container">
    <br/>
    <jsp:include page="../../fragment/errorList.jsp"/>
    <jsp:include page="../../fragment/adminUserCreateForm.jsp"/>
</div>
<br><br>
<jsp:include page="../../include/footer.jsp"/>
</body>
</html>
