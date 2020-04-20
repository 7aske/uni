<li class="collection-item avatar">
    <i class="material-icons circle <%=Boolean.parseBoolean(request.getParameter("published")) ? "teal lighten-2" : "grey"%>">text_format</i>
    <a href="${pageContext.request.contextPath}/post/${pageContext.request.getParameter("slug")}"><span class="title">${pageContext.request.getParameter("title")}</span><span class="grey-text">&nbsp;${pageContext.request.getParameter("slug")}</span></a>
    <p class="truncate" style="padding-right: 2em;"><%=request.getParameter("preview")%></p>
    <a href="${pageContext.request.contextPath}/admin/post/edit/${pageContext.request.getParameter("idBlogPost")}" class="secondary-content"><i class="material-icons orange-text">edit</i></a>
</li>
