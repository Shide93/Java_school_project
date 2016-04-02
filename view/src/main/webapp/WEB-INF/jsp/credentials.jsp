<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:frontendLayout>
    <jsp:attribute name="content">
         <ul class="nav nav-tabs">
             <li class=""><a href="<c:url value="/profile"/>">Profile</a></li>
             <li class="active"><a href="<c:url value="/profile/credentials"/>">Credentials</a></li>
             <li class=""><a href="<c:url value="/profile/orders"/>" >Orders</a></li>
         </ul>
            <div class="tab-pane fade in active">
                <h2>Your Profile, ${requestScope.user.name}!</h2>
                <p>${requestScope.notValid}</p>
                <form:form class="" role="form" action="/profile/credentials" method="post" modelAttribute="credentials">
                    <div class="form-group">
                        <h6>Change email:</h6>
                        <label>Email
                            <form:input class="form-control" type="text" path="email" />
                        </label>
                        <form:errors path="email" cssClass="error" /><br>
                    </div>
                    <div class="form-group">
                        <h6>Change password:</h6>

                        <label>New password
                            <form:password class="form-control" path="password" />
                        </label>
                        <form:errors path="password" cssClass="error" /><br>

                        <label>Re-type password
                            <form:password class="form-control" path="confirmPassword" />
                        </label>
                        <form:errors path="confirmPassword" cssClass="error" /><br>
                    </div>
                    <input class="btn btn-primary" value="Save credentials" type="submit">

                </form:form>
            </div>
    </jsp:attribute>

</t:frontendLayout>