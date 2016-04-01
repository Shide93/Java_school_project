<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:frontendLayout>
    <jsp:attribute name="content">

            <div id="profile" class="tab-pane fade in active">
                <h2>Your Profile, ${requestScope.user.name}!</h2>
                <p>${requestScope.notValid}</p>
                <form:form class="" role="form" action="/profile" method="post" modelAttribute="credentials">
                    <div class="form-group">
                        <h6>Change email:</h6>
                        <label>Email
                            <form:input class="form-control" type="text" path="email" />
                        </label>
                        <form:errors path="email" cssClass="error" /><br>
                    </div>
                    <div class="form-group">
                        <h6>Change password:</h6>

                        <label>Password
                            <form:password class="form-control" path="password" />
                        </label>
                        <form:errors path="password" cssClass="error" /><br>

                        <label>Re-type password
                            <form:password class="form-control" path="confirmPassword" />
                        </label>
                        <form:errors path="confirmPassword" cssClass="error" /><br>
                    </div>
                </form:form>
            </div>
    </jsp:attribute>

</t:frontendLayout>