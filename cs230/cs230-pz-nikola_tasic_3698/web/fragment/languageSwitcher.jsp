<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('lang')}"/>
<fmt:setBundle basename="i18n/strings"/>

<%--Disgusting not modular at all--%>

<% if (((String) session.getAttribute("lang")).equals("en")) { %>
<li><a href="<%=request.getRequestURL()%>?lang=rs"><fmt:message key="nav.lang.rs"/></a></li>
<% } else if (((String) session.getAttribute("lang")).equals("rs")) { %>
<li><a href="<%=request.getRequestURL()%>?lang=en"><fmt:message key="nav.lang.en"/></a></li>
<% } else { %>
<li><a href="<%=request.getRequestURL()%>?lang=rs"><fmt:message key="nav.lang.rs"/></a></li>
<% } %>
