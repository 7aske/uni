<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="include/materialize.jsp"/>
    <title>cs230::form.jsp</title>
</head>
<body>
<div class="container row">
    <ul class="collection with-header col s12 m12 l6">
        <li class="collection-header">
            <h6>Dobrodosli<%= request.getParameter("name") != null ? (" " + request.getParameter("name")) : "" %>,
                kliknite na neki od linkova da saznate vise:</h6></li>
        <%
            if (request.getParameter("fit") != null && request.getParameter("fit").equals("on")) {
                out.print("<li class=\"collection-item\"><div>Posetite FIT<a class=\"secondary-content\" href=\"http://www.metropolitan.ac.rs/osnovne-studije/fakultet-informacionih-tehnologija/\"><i class=\"material-icons\">send</i></a></div></li>");
            }
            if (request.getParameter("fdu") != null && request.getParameter("fdu").equals("on")) {
                out.print("<li class=\"collection-item\"><div>Posetite FDU<a class=\"secondary-content\" href=\"http://www.metropolitan.ac.rs/fakultet-digitalnih-umetnosti-2/\"><i class=\"material-icons\">send</i></a></div></li>");
            }
            if (request.getParameter("fam") != null && request.getParameter("fam").equals("on")) {
                out.print("<li class=\"collection-item\"><div>Posetite FAM<a class=\"secondary-content\" href=\"http://www.metropolitan.ac.rs/osnovne-studije/fakultet-za-menadzment/\"><i class=\"material-icons\">send</i></a></div></li>");
            }
        %>
    </ul>
</div>

</body>
</html>
