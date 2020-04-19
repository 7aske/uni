<%@ page import="config.Config" %>
<%
    String username = (String) session.getAttribute("username");
    String confUsername = Config.getProperties().getProperty("blog-username");
%>
<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container">
        <a id="logo-container" href="${pageContext.request.contextPath}"
           class="brand-logo">BLOG</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="${pageContext.request.contextPath}">Home</a></li>
            <%
                if (username != null && username.equals(confUsername)) {
                    out.print(String.format("<li><a href=\"%s/admin/admin.jsp\">Admin</a></li>", request.getContextPath()));
                    out.print(String.format("<li><a href=\"%s/admin/logout\">Logout</a></li>", request.getContextPath()));
                }
            %>
        </ul>
        <ul id="nav-mobile" class="sidenav">
            <li><a href="${pageContext.request.contextPath}">Home</a></li>
            <%
                if (username != null && username.equals(confUsername)) {
                    out.print(String.format("<li><a href=\"%s/admin/admin.jsp\">Admin</a></li>", request.getContextPath()));
                    out.print(String.format("<li><a href=\"%s/admin/logout\">Logout</a></li>", request.getContextPath()));
                }
            %>
        </ul>
        <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
    </div>
</nav>
<script>
    M.Sidenav.init(document.querySelector(".sidenav"), {});
</script>