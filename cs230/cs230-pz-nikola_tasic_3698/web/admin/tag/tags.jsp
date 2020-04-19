<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>Admin - Tags</title>
    <jsp:include page="../../include/head.jsp"/>
</head>
<body>
<jsp:include page="../../include/nav.jsp"/>
<nav class="light-blue lighten-2">
    <div class="nav-wrapper container">
        <div class="col s12">
            <a href="${pageContext.request.contextPath}" class="breadcrumb">
                Home
            </a>
            <a href="${pageContext.request.contextPath}/admin/admin.jsp"
               class="breadcrumb">
                Admin
            </a>
            <c:if test="${blogPost != null}">
                <a href="${pageContext.request.contextPath}/admin/edit/${blogPost.idBlogPost}"
                   class="breadcrumb">
                        ${blogPost.title}
                </a>
            </c:if>
        </div>
    </div>
</nav>

<br><br>
<jsp:include page="../../include/footer.jsp"/>
</body>
</html>
