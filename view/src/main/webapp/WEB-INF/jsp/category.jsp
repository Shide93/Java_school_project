<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">
        <c:if test="${requestScope.category == null}">
            <h1>Category doesn't exist</h1>
        </c:if>
        <c:if test="${requestScope.category != null}">
            <div class="category">
                <h1 class="">Category ${requestScope.category.name}</h1>
                <div class="description">
                    <p class="lead">${requestScope.category.description}</p>
                </div>
                <div class="product_list">
                    <c:forEach var="product" items="${requestScope.category.products}">
                       <t:productThumb product="${product}"/>
                    </c:forEach>
                </div>

            </div>
        </c:if>
    </jsp:attribute>

</t:frontendLayout>
