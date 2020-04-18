<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container">
        <a id="logo-container" href="${pageContext.request.contextPath}"
           class="brand-logo">BLOG</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="${pageContext.request.contextPath}">Home</a></li>
        </ul>
        <ul id="nav-mobile" class="sidenav">
            <li><a href="${pageContext.request.contextPath}">Home</a></li>
        </ul>
        <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
    </div>
</nav>
<script>
    M.Sidenav.init(document.querySelector(".sidenav"), {});
</script>