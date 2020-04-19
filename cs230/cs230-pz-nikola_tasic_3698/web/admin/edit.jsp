<%@ page import="database.entity.BlogPost" %>
<%@ page import="database.dao.BlogPostDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>Admin - Edit</title>
    <jsp:include page="../include/head.jsp"/>
    <jsp:include page="../include/mde.jsp"/>
</head>
<body>
<jsp:include page="../include/nav.jsp"/>
<%
    long idBlogPost = Long.parseLong((String) request.getAttribute("idBlogPost"));
    BlogPost blogPost = new BlogPostDAO().find(idBlogPost);
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
            <a href="${pageContext.request.contextPath}/admin/edit/${blogPost.idBlogPost}"
               class="breadcrumb">
                ${blogPost.title}
            </a>
        </div>
    </div>
</nav>
<div class="container">
    <form action="${pageContext.request.contextPath}/post/${blogPost.idBlogPost}" method="POST" class="row">
        <div class="row">
            <div class="input-field col s12">
                <input id="idBlogPost" name="idBlogPost" type="text" hidden value="${blogPost.idBlogPost}">
                <label for="idBlogPost" hidden>Title</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="title" name="title" type="text" data-length="128" value="${blogPost.title}">
                <label for="title">Title</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="slug" name="slug" type="text" data-length="128" value="${blogPost.slug}">
                <label for="slug">ULR Slug</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <textarea id="preview" class="materialize-textarea" name="preview">${blogPost.preview}</textarea>
                <label for="preview">Preview Text</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <textarea id="body" name="body" hidden class="materialize-textarea"><%=blogPost.getBody()%></textarea>
                <label for="body" hidden>Textarea</label>
            </div>
        </div>
        <div class="row">
            <div class="row">
                <div class="col s12 right-align">
                    <button class="btn waves-light light-blue lighten-1" type="submit" name="action">
                        Publish<i class="material-icons right">publish</i>
                    </button>
                </div>
            </div>
        </div>
    </form>
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
<jsp:include page="../include/footer.jsp"/>
</body>
</html>
