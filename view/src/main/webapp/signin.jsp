<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">

        <div class="">
            <h2>Sign in</h2>
            <c:if test="${param.signup ne 'success'}">
                <div>Please sign in using the form below or <a href="<c:url value="/signup"/>">singn up.</a> </div>
            </c:if>
            <c:if test="${param.signup eq 'success'}">
                <div class="alert alert-success"><spring:message code="register.success"/></div>
            </c:if>
            <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                <div class="alert alert-danger">
                    <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                </div>
            </c:if>
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
                            <input type="checkbox" name="remember-me" checked> Remember me
                        </label>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input class="btn btn-default" type="submit">
                </div>
            </form>
        </div>
    </jsp:attribute>

</t:frontendLayout>


