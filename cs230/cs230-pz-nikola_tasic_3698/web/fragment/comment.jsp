<%@ page import="java.time.LocalDate" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('lang')}"/>
<fmt:setBundle basename="i18n/strings"/>

<%
    Long idComment = Long.parseLong(request.getParameter("idComment"));
    Long idPost = Long.parseLong(request.getParameter("idPost"));
    String commenterEmail = request.getParameter("commenterEmail");
    String commenterName = request.getParameter("commenterName");
    String body = request.getParameter("body");
    LocalDate dateCommented = LocalDate.parse(request.getParameter("dateCommented"));
%>

<div class="row" id="comment-<%=idComment%>">
    <h5>
        <%=commenterName%>
        <span style="font-size: .55em;"><fmt:message key="post.comments.on_date"/><%=dateCommented%></span>
    </h5>
    <p class="truncate comment-body">
        <%=body%>
    </p>
    <button class="show-more" id="show-<%=idComment%>" onclick="(function() {
        const b = document.querySelector('#comment-<%=idComment%> .comment-body');
        b.classList.toggle('truncate');
    })()"><fmt:message key="post.comments.show_more"/></button>
    <button class="show-less" id="hide-<%=idComment%>" onclick="(function() {
        const b = document.querySelector('#comment-<%=idComment%> .comment-body');
        b.classList.toggle('truncate');
    })()"><fmt:message key="post.comments.show_less"/></button>
</div>
