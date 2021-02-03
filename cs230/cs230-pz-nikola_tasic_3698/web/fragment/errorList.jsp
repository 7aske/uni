<div class="container">
    <%
        if (request.getAttribute("errors") != null) {
            out.print("<ul class=\"collection\">");
            // can be request.getParameter as well
            String[] errors = ((String[]) request.getAttribute("errors"));
            for (String error : errors) {
                out.print(String.format("<li class=\"collection-item red accent-2 white-text\">%s</li>", error));
            }
            request.setAttribute("errors", null);
            out.print("</ul>");
        }
    %>
</div>