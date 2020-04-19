<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String idBlogPost = request.getParameter("idBlogPost");
    String title = request.getParameter("title");
    String slug = request.getParameter("slug");
    String preview = request.getParameter("preview");
    String body = request.getParameter("body");
    boolean published = Boolean.parseBoolean(request.getParameter("published"));
%>
<form action="${pageContext.request.contextPath}/admin/post/edit/<%=idBlogPost%>" method="POST" class="row">
    <br/>
    <div class="row">
        <div class="col s6 left-align">
            <button class="btn waves-light light-blue lighten-1" type="submit" name="action">
                Update<i class="material-icons right">publish</i>
            </button>
        </div>

    </div>
    <div class="row">
        <div class="col">
            <label class="">
                <input type="checkbox" class="filled-in" name="published" <%=published ? "checked" : ""%>/>
                <span>Published</span>
            </label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <input id="idBlogPost" name="idBlogPost" type="text" hidden value="<%=idBlogPost%>">
            <label for="idBlogPost" hidden>Title</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <input id="title" name="title" type="text" data-length="128" value="<%=title%>">
            <label for="title">Title</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <input id="slug" name="slug" type="text" data-length="128" value="<%=slug%>">
            <label for="slug">ULR Slug</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <textarea id="preview" class="materialize-textarea" name="preview"><%=preview%></textarea>
            <label for="preview">Preview Text</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <textarea id="body" name="body" hidden class="materialize-textarea"><%=body%></textarea>
            <label for="body" hidden>Textarea</label>
        </div>
    </div>
</form>
<c:if test="${!pageContext.request.getParameter('idBlogPost').equals('')}">
    <div class="col s6 right-align">
        <form method="post" action="${pageContext.request.contextPath}/admin/post/delete/<%=idBlogPost%>">
            <button class="btn waves-light red lighten-1" type="submit" name="action">
                Remove<i class="material-icons right">delete</i>
            </button>
        </form>
    </div>
</c:if>
