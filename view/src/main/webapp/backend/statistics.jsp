<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>

    <jsp:attribute name="sidebar">
        <div>

        </div>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div class="row">
            <div class="col-lg-4">
                <p class="newOrders">New orders: <b>${requestScope.newOrders}</b></p>
                <p class="totalSales">Total sales: <b>${requestScope.totalSales}</b></p>
                <p class="monthSales">Month sales: <b>${requestScope.monthSales}</b></p>
            </div>
            <div class="topCustomers col-lg-4">
                Top cusotmers:
                <c:forEach var="customer" items="${requestScope.topCustomers}" >
                    <div>
                        ${customer.name} ${customer.lastName}
                    </div>
                </c:forEach>
            </div>
            <div class="topProducts col-lg-4">
                Top products:
                <c:forEach var="product" items="${requestScope.topProducts}" >
                    <div>
                        <a href="<c:url value="/backend/products?productId=${product.id}"/>">${product.name}</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </jsp:attribute>

</t:backendLayout>
