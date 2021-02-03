<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="com.example.cms.database.entity.Tag" %>
<%@ page import="com.example.cms.database.dao.TagDAO" %>
<%@ page import="com.example.cms.database.dao.PostDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${pageContext.session.getAttribute('lang')}"/>
<fmt:setBundle basename="i18n/strings"/>

<%
    String idPost = request.getParameter("idPost");
    Long idUser = (Long) session.getAttribute("idUser");
    String title = request.getParameter("title");
    String slug = request.getParameter("slug");
    String excerpt = request.getParameter("excerpt");
    String body = request.getParameter("body");
    boolean published = Boolean.parseBoolean(request.getParameter("published"));
%>
<form action="${pageContext.request.contextPath}/admin/post/edit/<%=idPost%>" method="POST" class="row">
    <br/>
    <div class="row">
        <div class="col s6 left-align">
            <button class="btn waves-light light-blue lighten-1" type="submit" name="action">
                <fmt:message key="admin.post.edit.form.update"/><i class="material-icons right">publish</i>
            </button>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <label class="">
                <input type="checkbox" class="filled-in" name="published" <%=published ? "checked" : ""%>/>
                <span><fmt:message key="admin.post.edit.form.published"/></span>
            </label>
        </div>
    </div>
    <input id="id_post" name="id_post" type="text" hidden value="<%=idPost%>">
    <input id="id_user" name="id_user" type="text" hidden value="<%=idUser%>">
    <div class="row">
        <div class="input-field col s12">
            <input id="title" name="title" type="text" data-length="128" value="<%=title%>" required>
            <label for="title"><fmt:message key="admin.post.edit.form.title.label"/></label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <input id="slug" name="slug" type="text" data-length="32" value="<%=slug%>" required>
            <label for="slug"><fmt:message key="admin.post.edit.form.slug.label"/></label>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <label for="tags"><fmt:message key="admin.post.edit.form.tags.label"/></label>
            <input id="tags" name="tags" type="hidden">
            <div class="chips chips-autocomplete">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <textarea id="excerpt" class="materialize-textarea" name="excerpt" required><%=excerpt%></textarea>
            <label for="excerpt"><fmt:message key="admin.post.edit.form.excerpt.label"/></label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <textarea id="body" name="body" hidden class="materialize-textarea"><%=body%></textarea>
            <label for="body" hidden><fmt:message key="admin.post.edit.form.body.label"/></label>
        </div>
    </div>
</form>
<%
    TagDAO tagDAO = new TagDAO();
    PostDAO blogPostDAO = new PostDAO();
    List<Tag> tagList = tagDAO.findAll();
    request.setAttribute("tagList", tagList);
    Set<Tag> tags;
    try {
        tags = blogPostDAO.find(Long.parseLong(idPost)).getTags();
    } catch (NumberFormatException ex) {
        tags = new HashSet<Tag>();
    }
    request.setAttribute("tags", tags);
%>
<script>
    let tags =
        <json:array items="${tagList}" var="tag">
        <json:object>
        <json:property name="idTag" value="${tag.idTag}"/>
        <json:property name="name" value="${tag.name}"/>
        </json:object>
        </json:array>;
    let currTags =
        <json:array items="${tags}" var="tag">
        <json:object>
        <json:property name="idTag" value="${tag.idTag}"/>
        <json:property name="name" value="${tag.name}"/>
        </json:object>
        </json:array>;
    document.addEventListener('DOMContentLoaded', function () {
        const tagsInput = document.querySelector('input[name="tags"]');

        // Update tags input element in page load
        tagsInput.value = currTags.map(t => ({tag: t.name})).map(t => t.tag).join(",");

        const instance = M.Chips.init(document.querySelector('.chips-autocomplete'), {
            data: currTags.map(t => ({tag: decodeURIComponent(t.name)})),
            autocompleteOptions: {
                data: tags.reduce((obj, item) => {
                    obj[decodeURIComponent(item.name)] = null;
                    return obj;
                }, {}),
            },
            onChipAdd: function () {
                tagsInput.value = this.chipsData.map(t => t.tag).join(",")

            },
            onChipDelete: function () {
                tagsInput.value = this.chipsData.map(t => t.tag).join(",")
            },
        });
    });
</script>
<c:if test="${!pageContext.request.getParameter('idPost').equals('')}">
    <div class="col s6 right-align">
        <form method="post" action="${pageContext.request.contextPath}/admin/post/delete/<%=idPost%>">
            <button class="btn waves-light red lighten-1" type="submit" name="action">
                <fmt:message key="admin.post.edit.form.remove"/><i class="material-icons right">delete</i>
            </button>
        </form>
    </div>
</c:if>
