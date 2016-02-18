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
        <form class="" action="<c:url value="/signin"/>" method="post">

            <label>Email
                <input class="" type="email" name="email">
            </label><br>

            <label>Password
                <input class="" type="text" name="password">
            </label><br>

            <label>Remember me
                <input class="" type="checkbox" name="remember" checked>
            </label>

            <input class="" type="submit">

        </form>
    </div>
</body>
</html>

