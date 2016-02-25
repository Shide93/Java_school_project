<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">

        <h1 class="">This is cart!</h1>
        <c:if test="${sessionScope.cart == null || sessionScope.cart.items == null}">
            <div>
                Your cart is empty!
            </div>
        </c:if>
        <c:if test="${sessionScope.cart != null}">
            <table>
                <thead>
                    <tr>
                        <th>Product name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Cost</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="item" items="${sessionScope.cart.items}">
                        <tr>
                            <td>${item.product.name}</td>
                            <td>${item.product.price} $</td>
                            <td>${item.quantity}</td>
                            <td>${item.product.price * item.quantity}</td>
                        </tr>
                    </c:forEach>

                </tbody>
                <tfoot>
                    <tr>
                        <td></td>
                        <td></td>
                        <td>Total cost:</td>
                        <td>${sessionScope.cart.summary}</td>
                    </tr>
                </tfoot>
            </table>
        </c:if>
    </jsp:attribute>

</t:frontendLayout>
