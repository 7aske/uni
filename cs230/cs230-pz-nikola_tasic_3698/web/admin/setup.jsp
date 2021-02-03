<%@ page import="com.example.cms.database.dao.UserDAO" %>
<%@ page import="com.example.cms.database.RoleNames" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('lang')}"/>
<fmt:setBundle basename="i18n/strings"/>

<!DOCTYPE html>
<html lang="<%=session.getAttribute("lang")%>">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title><fmt:message key="admin.setup.title"/></title>
    <jsp:include page="../include/head.jsp"/>
    <jsp:include page="../include/materializeOverride.jsp"/>
</head>
<body>
<jsp:include page="../include/nav.jsp"/>
<br/>
<jsp:include page="../fragment/errorList.jsp"/>
<%
    if (new UserDAO().findByRoleName(RoleNames.ADMIN).size() == 1) {
        response.sendRedirect(request.getContextPath() + "/");
    }
%>
<jsp:include page="../fragment/errorList.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="../fragment/adminUserCreateForm.jsp">
            <jsp:param name="setup" value="true"/>
        </jsp:include>
    </div>
    <br><br>
</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>
