<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>

    <jsp:attribute name="sidebar">
        <div>
            <ul class="">
                <c:forEach var="order" items="${requestScope.orders}">
                    <li class="">
                        <a href="<c:url value="/backend/orders?orderId=${order.id}"/>">Order: ${order.id}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </jsp:attribute>
    <jsp:attribute name="content">

        <h1 class="">Order #${requestScope.selectedProduct.id}</h1>

    </jsp:attribute>

</t:backendLayout>
