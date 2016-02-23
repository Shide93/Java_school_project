<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <ul>
        <c:forEach var="category" items="${applicationScope.categoryList}">

        <li>
            <a href="<c:url value="/category/${category.id}"/>">${category.name}</a>
        </li>

        </c:forEach>
    </ul>

</div>
