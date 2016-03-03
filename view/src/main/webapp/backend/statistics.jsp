<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>

    <jsp:attribute name="sidebar">
        <div>
            Daily sales:
            1 000 000 $
        </div>
    </jsp:attribute>
    <jsp:attribute name="content">

        <div class="newOrders">New orders: ${requestScope.newOrders} </div>
        <div class="totalSales">Total sales: ${requestScope.totalSales}</div>
        <div class="monthSales">Month sales: ${requestScope.monthSales}</div>

        <div class="topCustomers">
            Top custmers:
            <c:forEach var="customer"  items="${requestScope.topCustomers}" >
                <div>
                    ${customer.name} ${customer.lastName}
                </div>
            </c:forEach>
        </div>
        <div class="topProducts">
            Top products:
            <c:forEach var="product" items="${requestScope.topProducts}" >
                <div>
                        ${product.name} ${product.price}
                </div>
            </c:forEach>
        </div>
    </jsp:attribute>

</t:backendLayout>
