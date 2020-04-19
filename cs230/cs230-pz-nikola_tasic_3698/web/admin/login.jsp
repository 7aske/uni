<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>Blog - Admin Login</title>
    <jsp:include page="../include/head.jsp"/>
    <style>
        /*#ff9800*/
        /*#29b6f6*/
        input:focus.validate {
            border-bottom-color: #ff9800 !important;
            box-shadow: 0 1px 0 0 #ff9800 !important;
        }

        input:focus.validate + label {
            color: #ff9800 !important;
        }

        .input-field .prefix.active {
            color: #ff9800 !important;
        }

        body {
            min-height: 100vh !important;
            display: flex;
            flex-direction: column;
        }
        .container {
            flex-grow: 4;
        }
    </style>
</head>
<body>
<jsp:include page="../include/nav.jsp"/>
<div class="container">
    <div class="row">
        <form class="col s12" method="post" action="${pageContext.request.contextPath}/admin/login">
            <div class="row">
                <br/>
                <br/>
                <div class="col s12 m12 l3"></div>
                <div class="col s12 m12 l6">
                    <div class="input-field col s12 m12 l12">
                        <i class="material-icons prefix">account_circle</i>
                        <input id="username" name="username" type="text" class="validate">
                        <label for="username">Username</label>
                    </div>
                    <div class="input-field col s12 m12 l12">
                        <i class="material-icons prefix">lock</i>
                        <input id="password" name="password" type="text" class="validate">
                        <label for="password">Password</label>
                    </div>
                    <div class="row">
                        <div class="col s12">
                            <button class="btn waves-light light-blue lighten-1" type="submit" name="action">
                                Submit<i class="material-icons right">send</i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <br><br>
</div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>
