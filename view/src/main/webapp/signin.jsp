<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">

        <div class="">
            <h2>Sign in</h2>
            <div>Please sign in using the form below or <a href="<c:url value="/signup"/>">singn up.</a> </div>
            <form class="" action="<c:url value="/signin"/>" method="post">

                <label>Email
                    <input class="" type="text" name="email">
                </label><br>

                <label>Password
                    <input class="" type="password" name="password">
                </label><br>

                <label>Remember me
                    <input class="" type="checkbox" name="remember" checked>
                </label>

                <input class="" type="submit">

            </form>
        </div>

    </jsp:attribute>

</t:frontendLayout>


