<%@tag description="frontend layout" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="content" fragment="true" %>

<t:mainLayout>
    <jsp:attribute name="header">
        <div class="">abc</div>
    <!--login -->
        <c:if test="${sessionScope.user != null}">
            <div class="">
                <a href="<c:url value="/profile"/>">Привет ${sessionScope.user.name}</a>
                /
                <a href="<c:url value="/logout"/>">Выход</a>
            </div>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <div class="">
                <a href="<c:url value="/signin.jsp"/>">Вход</a>
                /
                <a href="<c:url value="/signup.jsp"/>">Регистрация</a>
            </div>
        </c:if>
    <!--cart -->
        <div class="cart">
            <div>Your cart:</div>
            <div class=""><span>${sessionScope.cart.count} items</span> - <span>${sessionScope.cart.summary} $</span> </div>
        </div>

    </jsp:attribute>
    <jsp:attribute name="sidebar">
       <jsp:include page="sidebar.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="content">
       <jsp:invoke fragment="content"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <div>this is footer</div>
    </jsp:attribute>
</t:mainLayout>