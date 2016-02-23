<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">
        <div class="">
            <h1 class="">Product ${requestScope.product.name}</h1>



        </div>

    </jsp:attribute>

</t:frontendLayout>
