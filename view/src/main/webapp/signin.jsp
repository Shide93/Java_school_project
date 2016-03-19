<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">

        <div class="">
            <h2>Sign in</h2>
            <div>Please sign in using the form below or <a href="<c:url value="/signup"/>">singn up.</a> </div>
            <p>${requestScope.notValid}</p>
            <c:url value="/login" var="loginUrl" />
            <form class="" role="form" action="${loginUrl}" method="post">
                <div class="form-group">
                    <label>Email
                        <input class="form-control" type="text" name="username">
                    </label><br>

                    <label>Password
                        <input class="form-control" type="password" name="password">
                    </label><br>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="_spring_security_remember_me" checked> Remember me
                        </label>
                    </div>
                    <input class="btn btn-default" type="submit">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>
            </form>
        </div>

<form action="<c:url value="/logout"/>" method="post"><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> <input class="btn btn-default" type="submit"></form>

    </jsp:attribute>

</t:frontendLayout>


