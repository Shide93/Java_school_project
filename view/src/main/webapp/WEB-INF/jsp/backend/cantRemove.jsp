<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>

    <jsp:attribute name="sidebar">
        <div>

        </div>
    </jsp:attribute>
    <jsp:attribute name="content">

        <h1 class="">${requestScope.cantRemove}</h1>

    </jsp:attribute>

</t:backendLayout>
