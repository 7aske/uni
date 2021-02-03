<%@ page import="com.example.cms.database.dao.RoleDAO" %>
<%@ page import="com.example.cms.database.entity.Role" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.cms.database.RoleNames" %>
<%@ page import="com.example.cms.database.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('lang')}"/>
<fmt:setBundle basename="i18n/strings"/>

<%
    boolean setup = request.getParameter("setup") != null && request.getParameter("setup").equals("true");
    User user = (User) request.getAttribute("user");
%>
<% if (setup) { %>
<form class="col s12" method="post" action="${pageContext.request.contextPath}/admin/create">
        <%} else {%>
    <form class="col s12" method="post"
          action="${pageContext.request.contextPath}/admin/user/edit<%= user != null ? "/" + user.getIdUser() : ""%>">
        <%}%>
        <div class="row">
            <br/>
            <br/>
            <% if (setup) { %>
            <div class="row col s12 m12">
                <div class="input-field col s12 m12 l6 xl6">
                    <p><fmt:message key="admin.user.create.form.setup.p1"/></p>
                    <p><fmt:message key="admin.user.create.form.setup.p2"/></p>
                    <p><fmt:message key="admin.user.create.form.setup.p3"/></p>
                </div>
            </div>
            <% } %>
            <label for="idUser">
                <input id="idUser" name="idUser" type="text" class="validate" hidden
                       value="<%= user != null ? user.getIdUser() : "" %>">
            </label>
            <div class="row col s12 m12">
                <div class="input-field col s12 m12 l6 xl6">
                    <input id="email" name="email" type="email" class="validate"
                           value="${pageContext.request.getAttribute("user").email}" required>
                    <label for="email"><fmt:message key="admin.user.create.form.email.label"/></label>
                </div>
            </div>
            <div class="col s12 m12"></div>
            <div class="row col s12 m12">
                <div class="input-field col s12 m12 l6 xl6">
                    <input id="username" name="username"
                           type="text" <%=request.getAttribute("user") != null ? "readonly" : ""%> class="validate"
                           value="${pageContext.request.getAttribute("user").username}" required>
                    <label for="username"><fmt:message key="admin.user.create.form.username.label"/></label>
                    <span class="helper-text"><fmt:message key="admin.user.create.form.username.help"/></span>
                </div>
            </div>
            <div class="row col s12 m12">
                <div class="input-field col s12 m12 l6 xl6">
                    <input id="display_name" name="display_name" type="text"
                           value="${pageContext.request.getAttribute("user").displayName}" class="validate" required>
                    <label for="display_name"><fmt:message key="admin.user.create.form.display_name.label"/></label>
                    <span class="helper-text"><fmt:message key="admin.user.create.form.display_name.help"/></span>
                </div>
            </div>
            <div class="row col s12 m12">
                <div class="input-field col s12 m12 l6 xl6">
                    <input id="password" name="password" type="password" class="validate" required>
                    <label for="password"><fmt:message key="admin.user.create.form.password.label"/></label>
                </div>
            </div>
            <div class="row col s12 m12">
                <div class="input-field col s12 m12 l6 xl6">
                    <input id="password_confirm" name="password_confirm" type="password" class="validate" required>
                    <label for="password_confirm"><fmt:message key="admin.user.create.form.password_confirm.label"/></label>
                    <span class="helper-text"><fmt:message key="admin.user.create.form.password_confirm.help"/></span>
                </div>
            </div>
            <% if (setup) { %>
            <label>
                <input type="checkbox" name="setup" checked class="hidden">
            </label>
            <% } else { %>
            <% }%>
            <div class="row col s12 m12">
                <div class="input-field col s12 m12 l6 xl6">
                    <h6><fmt:message key="admin.user.create.form.roles"/></h6>
                    <% List<Role> roles = new RoleDAO().findAll(); %>
                    <% for (Role role : roles) { %>
                    <p>
                        <label>
                            <input name="roles" value="<%=role.getRoleName()%>" type="checkbox"
                                   class="filled-in"
                                    <%= user != null && user.getRoles().contains(role) ? "checked" : "" %>
                                    <%= role.getRoleName().equals(RoleNames.ADMIN) && setup ? "checked" : "" %>
                                    <%= role.getRoleName().equals(RoleNames.AUTHOR) && !setup ? "checked" : "" %>
                                    <%= role.getRoleName().equals(RoleNames.ADMIN) && setup ? "disabled" : "" %>
                                    <%= role.getRoleName().equals(RoleNames.AUTHOR) && !setup ? "disabled" : "" %>
                            />
                            <span><%=role.getRoleName().toUpperCase().charAt(0) + role.getRoleName().substring(1)%></span>
                        </label>
                    </p>
                    <% } %>
                </div>
            </div>
            <div class="row col s12 m12">
                <button class="light-blue btn" type="submit"><fmt:message key="admin.user.create.form.submit"/></button>
            </div>
        </div>
    </form>