<%@tag description="frontend layout" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="content" fragment="true" %>
<t:mainLayout>
    <jsp:attribute name="header">
        <div class="">abc</div>
        <c:if test="${sessionScope.userID != null}">
            <div class="">
                <a href="/profile">Привет ${sessionScope.userID}</a>
                /
                <a href="/logout">Выход</a>
            </div>
        </c:if>
        <c:if test="${sessionScope.userID == null}">
            <div class="">
                <a href="signin.jsp">Вход</a>
                /
                <a href="signup.jsp">Регистрация</a>
            </div>
        </c:if>
    </jsp:attribute>
    <jsp:attribute name="sidebar">
        <div>this is sidebar</div>
    </jsp:attribute>
    <jsp:attribute name="content">
       <jsp:invoke fragment="content"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <div>this is footer</div>
    </jsp:attribute>
</t:mainLayout>