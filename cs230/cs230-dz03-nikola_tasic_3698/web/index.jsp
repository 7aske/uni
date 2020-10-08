<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="include/materialize.jsp"/>
    <title>cs230::index.jsp</title>
</head>
<body>
<div class="container">
    <form action="form.jsp" method="post">
        <div class="row">
            <div class="input-field col s6">
                <input placeholder="Ime" id="name" name="name" type="text" class="validate">
                <label for="name">Ime</label>
            </div>
        </div>
        <div class="row">
            <h6>Izaberite fakultet</h6>
            <div class="col s12">
                <p>
                    <label>
                        <input name="fit" type="checkbox" class="filled-in"/>
                        <span>FIT</span>
                    </label>
                </p>
                <p>
                    <label>
                        <input name="fam" type="checkbox" class="filled-in"/>
                        <span>FAM</span>
                    </label>
                </p>
                <p>
                    <label>
                        <input name="fdu" type="checkbox" class="filled-in"/>
                        <span>FDU</span>
                    </label>
                </p>
                <div class="input-field">
                    <input class="btn" type="submit" value="POSALJI">
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
