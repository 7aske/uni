<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.DayOfWeek" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><% out.print("JSP Scriptlet Demo"); %></title>
</head>
<body>
<form action="form.jsp?test=test" method="post">
    <input type="text" name="test"/>
    <input type="submit"/>
</form>
<% out.print("Scriptlet Demo"); %>
<%= new Date() %>
<%if (LocalDate.now().getDayOfWeek() == DayOfWeek.WEDNESDAY) {%>
<h4>Danas je sreda</h4>
<% } else { %>
<h4>Danas nije sreda</h4>
<% } %>
<pre>
<%! int i = 1; %>
<%
    for (i = 1; i < 100; i++) {
        out.print(i + " ");
        if (i % 3 == 0) {
            out.print("fizz");
        }
        if (i % 5 == 0) {
            out.print("buzz");
        }
        out.println();
    }
%>
</pre>
<jsp:include page="footer.jsp"/>
<%@ include file="footer.jsp" %>
</body>
</html>
