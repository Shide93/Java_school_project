<%@tag description="frontend layout" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="content" fragment="true" %>
<t:mainLayout>
    <jsp:attribute name="header">
        <div class="">abc</div>
        <%-- if registered--%>
        <div class="">
            <a href="">Привет %user</a>
            /
            <a href="">Выход</a>
        </div>
        <%-- else --%>
        <div class="">
            <a href="signin.jsp">Вход</a>
            /
            <a href="signup.jsp">Регистрация</a>
        </div>
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