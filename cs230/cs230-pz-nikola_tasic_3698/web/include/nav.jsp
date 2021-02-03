<%@ page import="com.example.cms.database.entity.Role" %>
<%@ page import="com.example.cms.database.RoleNames" %>
<%@ page import="com.example.cms.util.Util" %>
<%@ page import="java.util.HashSet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('lang')}"/>
<fmt:setBundle basename="i18n/strings"/>

<%
    String username = (String) session.getAttribute("username");
    Long idUser = (Long) session.getAttribute("idUser");
    Iterable<Role> roles = session.getAttribute("roles") != null ? ((Iterable<Role>) session.getAttribute("roles")) : new HashSet<Role>();
    boolean loggedIn = username != null && idUser != null;
%>
<jsp:include page="navMaterializeOverride.jsp"/>
<nav class="light-blue lighten-1" role="navigation">
    <ul id="dropdown1" class="dropdown-content">
        <li><a href="<%=request.getContextPath()%>/admin/admin.jsp"><fmt:message key="nav.posts"/></a></li>
        <% if (Util.hasRole(roles, RoleNames.ADMIN)) { %>
        <li><a href="<%=request.getContextPath()%>/admin/user/users.jsp"><fmt:message key="nav.users"/></a></li>
        <%}%>
        <li><a href="<%=request.getContextPath()%>/admin/tag/tags.jsp"><fmt:message key="nav.tags"/></a></li>
    </ul>
    <div class="nav-wrapper container">
        <a id="logo-container" href="${pageContext.request.contextPath}" class="brand-logo"><fmt:message key="nav.title"/></a>
        <a href="#" data-target="nav-mobile" class="sidenav-trigger right"><i class="material-icons">menu</i></a>
        <ul class="hide-on-med-and-down right">
            <li><a href="${pageContext.request.contextPath}"><fmt:message key="nav.home"/></a></li>
            <%if (loggedIn) { %>
            <li><a class="dropdown-trigger" href="#" data-target="dropdown1"><fmt:message key="nav.more"/><i class="material-icons right">arrow_drop_down</i></a>
            </li>
            <li><a href="<%=request.getContextPath()%>/admin/logout"><fmt:message key="nav.logout"/></a></li>
            <% } %>
            <jsp:include page="../fragment/languageSwitcher.jsp"/>
        </ul>
        <ul id="nav-mobile" class="sidenav">
            <%if (loggedIn) { %>
            <li><a href="<%=request.getContextPath()%>/admin/admin.jsp"><fmt:message key="nav.posts"/></a></li>
            <% if (Util.hasRole(roles, RoleNames.ADMIN)) { %>
            <li><a href="<%=request.getContextPath()%>/admin/user/users.jsp"><fmt:message key="nav.users"/></a></li>
            <% } %>
            <li><a href="<%=request.getContextPath()%>/admin/tag/tags.jsp"><fmt:message key="nav.tags"/></a></li>
            <li><a href="<%=request.getContextPath()%>/admin/logout"><fmt:message key="nav.logout"/></a></li>
            <% } %>
            <li><a href="${pageContext.request.contextPath}"><fmt:message key="nav.home"/></a></li>
            <jsp:include page="../fragment/languageSwitcher.jsp"/>
        </ul>
    </div>
</nav>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", () => {
        M.Dropdown.init(document.querySelector(".dropdown-trigger"), {});
        M.Sidenav.init(document.querySelector(".sidenav"), {edge: "right"});
    });
</script>