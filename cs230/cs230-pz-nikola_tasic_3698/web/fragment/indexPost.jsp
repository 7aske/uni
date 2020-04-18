<div class="col s12 m12 l12 xl4">
    <div class="card light-blue darken-1">
        <div class="card-content white-text">
            <h5 class="card-title">
                <%=request.getParameter("title")%>
            </h5>
            <p class="light">
                <%=request.getParameter("preview")%>
            </p>
        </div>
        <div class="card-action">
            <a href="${pageContext.request.contextPath}/post/${pageContext.request.getParameter("slug")}">Read more</a>
        </div>
    </div>
</div>
