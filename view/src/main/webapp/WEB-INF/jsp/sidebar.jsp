<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <div class="list-group">
        <a class="list-group-item" href="<c:url value="/search"/>">Search products</a>
        <c:forEach var="category" items="${applicationScope.categoryList}">
            <a class="list-group-item
            <c:if test="${applicationScope.category.id eq category.id}">active</c:if>
            " href="<c:url value="/category/${category.id}"/>">${category.name}</a>
        </c:forEach>
    </div>

</div>
