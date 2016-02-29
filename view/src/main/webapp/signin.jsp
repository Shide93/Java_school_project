<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">

        <div class="">
            <h2>Sign in</h2>
            <div>Please sign in using the form below or <a href="<c:url value="/signup"/>">singn up.</a> </div>
            <form class="" role="form" action="<c:url value="/signin"/>" method="post">
                <div class="form-group">
                    <label>Email
                        <input class="form-control" type="text" name="email">
                    </label><br>

                    <label>Password
                        <input class="form-control" type="password" name="password">
                    </label><br>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="remember" checked> Remember me
                        </label>
                    </div>
                    <input class="btn btn-default" type="submit">

                </div>
            </form>
        </div>

    </jsp:attribute>

</t:frontendLayout>


