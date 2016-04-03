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
                <p class="newOrders">New orders: <b><c:out value="${requestScope.newOrders}" default="0"/></b></p>
                <p class="totalSales">Total sales: <b><c:out value="${requestScope.totalSales}" default="0"/></b></p>
                <p class="monthSales">Month sales: <b><c:out value="${requestScope.monthSales}" default="0"/></b></p>
            </div>
            <div class="topCustomers col-lg-4">
                Top cusotmers:
                <c:if test="${requestScope.topCustomers != null}">
                    <c:forEach var="customer" items="${requestScope.topCustomers}"  >
                        <div>
                            ${customer.name} ${customer.lastName}
                        </div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="topProducts col-lg-4">
                Top products:
                <c:if test="${requestScope.topProducts != null}">
                    <c:forEach var="product" items="${requestScope.topProducts}" >
                        <div>
                            <a href="<c:url value="/backend/products?productId=${product.id}"/>">${product.name}</a>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8">
                <span>Access token for REST report service:
                </span>
                <a href="#" class="show_token">Show</a>
                <span class="token_value display-none">${accessToken}</span>
            </div>
        </div>
        <script>
            $(document).ready( function() {
                $(".show_token").on('click', function (e) {
                    e.preventDefault();
                    $(".token_value").show();
                    $(".show_token").hide();
                });
            });
        </script>
    </jsp:attribute>

</t:backendLayout>
