<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">
        <h1>Search results</h1>
        <div class="product_list">
            <c:forEach var="product" items="${requestScope.products}">
                <t:productThumb product="${product}"/>
            </c:forEach>
        </div>
    </jsp:attribute>

</t:frontendLayout>