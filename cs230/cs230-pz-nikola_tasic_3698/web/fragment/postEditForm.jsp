<%
    String idBlogPost = request.getParameter("idBlogPost");
    String title = request.getParameter("title");
    String slug = request.getParameter("slug");
    String preview = request.getParameter("preview");
    String body = request.getParameter("body");
%>
<form action="${pageContext.request.contextPath}/admin/edit/<%=idBlogPost%>" method="POST" class="row">
    <br/>
    <div class="row">
        <div class="row">
            <div class="col s12 left-align">
                <button class="btn waves-light light-blue lighten-1" type="submit" name="action">
                    Publish<i class="material-icons right">publish</i>
                </button>
            </div>
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