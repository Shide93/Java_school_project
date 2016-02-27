<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">

        <div class="">
            <h2>Your Profile, ${sessionScope.user.name}!</h2>
            <form class="" action="<c:url value="/profile"/>" method="post">
                <div class="">
                    <h6>Change email:</h6>
                    <label>Email
                        <input class="" type="" name="email" value="${sessionScope.user.email}">
                    </label><br>
                </div>
                <div>
                    <h6>Change password:</h6>

                    <label>Password
                        <input class="" type="password" name="password" value="${sessionScope.user.password}">
                        <span class = "">${requestScope.passNotEq}</span>
                    </label><br>

                    <div class="">Re-type password</div>
                    <label>
                        <input class="" type="password" name="password2">
                    </label><br>
                </div>
                <div class="">
                    <h6>Additional info</h6>
                    <div class="">Name</div>
                    <label>
                        <input class="" type="text" name="name" value="${sessionScope.user.name}">
                    </label><br>


                    <label>Last name
                        <input class="" type="text" name="last_name" value="${sessionScope.user.lastName}">
                    </label><br>
                    <label>Phone
                        <input class="" type="text" name="phone" value="${sessionScope.user.phone}">
                    </label><br>

                    <label>Birth date
                        <input class="" type="text" name="birth_date" value="${sessionScope.user.birthDate}">
                        <span class = "">${requestScope.wrongBirth}</span>
                    </label><br>
                </div>
                <div class="">
                    <h6>Shipping address</h6>
<%--//TODO: address??--%>
                    <label>Country
                        <input class="" type="text" name="country" value="${sessionScope.user.address.country}">
                    </label><br>

                    <label>Region
                        <input class="" type="text" name="region" value="${sessionScope.user.address.region}">
                    </label><br>

                    <label>City
                        <input class="" type="text" name="city" value="${sessionScope.user.address.city}">
                    </label><br>

                    <label>Zip
                        <input class="" type="text" name="zip" value="${sessionScope.user.address.zip}">
                        <span class = "">${requestScope.wrongZip}</span>
                    </label><br>

                    <label>Address
                        <input class="" type="text" name="street" value="${sessionScope.user.address.street}">
                    </label><br>

                </div>
                <input class="" value="Save profile" type="submit">

            </form>
        </div>
    </jsp:attribute>

</t:frontendLayout>