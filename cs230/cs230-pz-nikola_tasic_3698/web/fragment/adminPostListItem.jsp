<li class="collection-item avatar">
    <i class="material-icons circle">text_format</i>
    <span class="title">${pageContext.request.getParameter("title")}</span>
    <p class="truncate" style="padding-right: 2em;"><%=request.getParameter("preview")%>></p>
    <a href="${pageContext.request.contextPath}/admin/edit/${pageContext.request.getParameter("idBlogPost")}" class="secondary-content"><i class="material-icons orange-text">edit</i></a>
</li>
