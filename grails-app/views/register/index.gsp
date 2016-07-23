<%--
  Created by IntelliJ IDEA.
  User: KRISHNA
  Date: 22-07-2016
  Time: 17:30
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project Management Application</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <form name="loginForm" id="loginForm" class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" placeholder="username" name="username" id="username" class="form-control">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" name="password" id="password" class="form-control">
                </div>
                <button type="submit" class="btn btn-success">Register here</button>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</nav>

</body>
</html>