<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:frontendLayout>
    <jsp:attribute name="content">
        <ul class="nav nav-tabs">
            <li class="active"><a href="<c:url value="/profile"/>">Profile</a></li>
            <li class=""><a href="<c:url value="/profile/credentials"/>">Credentials</a></li>
            <li class=""><a href="<c:url value="/profile/orders"/>" >Orders</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade in active">
                <h2>Your Profile, ${requestScope.user.name}!</h2>
                <form:form class="" role="form" action="/profile" method="post" modelAttribute="user">
                    <div class="panel panel-default">
                        <div class="panel-heading">Your email:</div>
                        <div class="panel-body">${requestScope.user.email}</div>
                    </div>

                    <div class=" panel panel-default">
                        <div class="panel-heading">Additional info</div>
                        <div class="form-group panel-body">
                            <label>Name
                                <form:input class="form-control" type="text" path="name"/>
                            </label>
                            <form:errors path="name" cssClass="error" /><br>

                            <label>Last name
                                <form:input class="form-control" type="text" path="lastName" />
                            </label>
                            <form:errors path="lastName" cssClass="error" /><br>
                            <label>Phone
                                <form:input class="form-control input-medium bfh-phone"
                                            data-format="+d (ddd) ddd dddd" type="text" path="phone" />
                            </label>
                            <form:errors path="phone" cssClass="error" /><br>

                            <label>Birth date
                                <form:input id="date-picker" class="form-control" type="text" path="birthDate" />
                            </label>

                            <form:errors path="birthDate" cssClass="error" /><br>
                        </div>
                    </div>


                    <div class=" panel panel-default">
                        <div class="panel-heading">Shipping address</div>
                        <div class="form-group panel-body">
                            <label>Country
                                <form:input class="form-control" type="text" path="address.country"  />
                            </label>
                            <form:errors path="address.country" cssClass="error" /><br>

                            <label>Region
                                <form:input class="form-control" type="text" path="address.region"  />
                            </label>
                            <form:errors path="address.region" cssClass="error" /><br>

                            <label>City
                                <form:input class="form-control" type="text" path="address.city" />
                            </label>
                            <form:errors path="address.city" cssClass="error" /><br>

                            <label>Zip
                                <form:input class="form-control width-correction" type="number" path="address.zip"  />
                            </label>
                            <form:errors path="address.zip" cssClass="error" /><br>

                            <label>Street
                                <form:input class="form-control" type="text" path="address.street"  />
                            </label>
                            <form:errors path="address.street" cssClass="error" /><br>

                            <label>Building
                                <form:input class="form-control width-correction" type="number" path="address.building"  />
                            </label>
                            <form:errors path="address.building" cssClass="error" /><br>

                            <label>Flat
                                <form:input class="form-control width-correction" type="number" path="address.flat"
                                            value="${requestScope.user.address.flat}" />
                            </label>
                            <form:errors path="address.flat" cssClass="error" /><br>

                        </div>
                    </div>
                    <input class="btn btn-primary" value="Save profile" type="submit">
                </form:form>
            </div>
        </div>
    </jsp:attribute>

</t:frontendLayout>