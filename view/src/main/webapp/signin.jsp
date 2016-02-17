<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
    <div class="">
        <h2>Sign in</h2>
        <div>Please sign in using the form below</div>
        <form class="" action="/signin" method="post">

            <div class="">Email</div>
            <input class="" type="text" name="email"><br>

            <div class="">Password</div>
            <input class="" type="text" name="password"><br>

            <span class="">Remember me?</span>
            <input class="" type="checkbox" name="remember">

            <input class="" type="submit">

        </form>
    </div>
</body>
</html>

