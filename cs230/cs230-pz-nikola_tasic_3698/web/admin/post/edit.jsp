<%@ page import="database.entity.BlogPost" %>
<%@ page import="database.dao.BlogPostDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>Admin - Edit</title>
    <jsp:include page="../../include/head.jsp"/>
    <jsp:include page="../../include/mde.jsp"/>
</head>
<body>
<jsp:include page="../../include/nav.jsp"/>
<%
    BlogPost blogPost = null;
    try {
        long idBlogPost = Long.parseLong((String) request.getAttribute("idBlogPost"));
        blogPost = new BlogPostDAO().find(idBlogPost);
    } catch (NumberFormatException ignored) {
    }
    pageContext.setAttribute("blogPost", blogPost);
%>
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
<div class="container">
    <jsp:include page="../../fragment/postEditForm.jsp">
        <jsp:param name="idBlogPost" value="${blogPost.idBlogPost}"/>
        <jsp:param name="title" value="${blogPost.title}"/>
        <jsp:param name="slug" value="${blogPost.slug}"/>
        <jsp:param name="preview" value="${blogPost.preview}"/>
        <jsp:param name="body" value="${blogPost.body}"/>
        <jsp:param name="published" value="${blogPost.published}"/>
    </jsp:include>
</div>
<br><br>
<script type="text/javascript">
    M.updateTextFields();
    M.CharacterCounter.init(document.querySelectorAll("[data-length]"));
</script>
<script type="text/javascript">
    const simplemde = new SimpleMDE({
        autofocus: true,
        autosave: {
            enabled: true,
            delay: 1000,
        },
        blockStyles: {
            bold: "__",
            italic: "_"
        },
        element: document.getElementById("body"),
        forceSync: true,
        indentWithTabs: false,
        insertTexts: {
            horizontalRule: ["", "\n\n-----\n\n"],
            image: ["![](http://", ")"],
            link: ["[", "](http://)"],
            table: ["", "\n\n| Column 1 | Column 2 | Column 3 |\n| -------- | -------- | -------- |\n| Text     | Text      | Text     |\n\n"],
        },
        lineWrapping: true,
        parsingConfig: {
            allowAtxHeaderWithoutSpace: true,
            strikethrough: false,
            underscoresBreakWords: true,
        },
        placeholder: "Type here...",
        promptURLs: true,
        renderingConfig: {
            singleLineBreaks: false,
            codeSyntaxHighlighting: true,
        },
        shortcuts: {
            drawTable: "Cmd-Alt-T"
        },
        showIcons: ["code", "table"],
        spellChecker: false,
        styleSelectedText: false,
        tabSize: 4,
        toolbarTips: false,
    });
</script>
<jsp:include page="../../include/footer.jsp"/>
</body>
</html>
