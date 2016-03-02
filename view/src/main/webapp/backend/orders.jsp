<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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


        <h1 class="">Order #${requestScope.selectedOrder.id}</h1>
        <div class="order_statuses">
            <c:forEach var="status" items="${requestScope.orderStatuses}">
                <c:if test="${status eq requestScope.selectedOrder.orderStatus}">
                    <c:set var="button" value="btn-primary" />
                </c:if>
                <c:if test="${status ne requestScope.selectedOrder.orderStatus}">
                    <c:set var="button" value="btn-default" />
                </c:if>

                <form class="inline-block" action="?action=changeStatus" method="post">
                    <input class="btn ${button}" type="submit" value="${status}">
                    <input type="hidden" name="id" value="${requestScope.selectedOrder.id}">
                    <input type="hidden" name="status" value="${status}">
                </form>
            </c:forEach>
        </div>
       <div class="order-date">
           Placed: <fmt:formatDate value="${order.orderDate}"
                                   type="BOTH" dateStyle="short" timeStyle="short"/>
       </div>
        <div class="order_user">
            <div>${requestScope.selectedOrder.user.name}  ${requestScope.selectedOrder.user.lastName}</div>
            <div>Email: ${requestScope.selectedOrder.user.email}</div>
            <div>Phone: ${requestScope.selectedOrder.user.phone}</div>

        </div>

        <div class="order_products">
            <table class="">
                <thead>
                <tr>
                    <th>Product name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Cost</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="item" items="${requestScope.selectedOrder.products}">
                    <tr>
                        <td>${item.product.name}</td>
                        <td>${item.product.price} $</td>
                        <td>
                            <span class="">${item.quantity}</span>
                        </td>
                        <td>${item.product.price * item.quantity}</td>
                    </tr>
                </c:forEach>

                </tbody>
                <tfoot>
                <tr>
                    <td></td>
                    <td></td>
                    <td>Subtotal:</td>
                    <td>${requestScope.selectedOrder.total}</td>
                </tr>
                <tr>
                    <td>Shipping cost:</td>
                    <td></td>
                    <td>Total:</td>
                    <td>${requestScope.selectedOrder.shipping.cost}</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>Total:</td>
                    <td>${requestScope.selectedOrder.total + requestScope.selectedOrder.shipping.cost}</td>
                </tr>
                </tfoot>
            </table>
        </div>

        <div class="order_shipping">
            Shipping:
            <div class="">${requestScope.selectedOrder.shipping.name}</div>
            <div class="">${requestScope.selectedOrder.shipping.description}</div>
            <div class="">${requestScope.selectedOrder.shipping.cost}</div>
        </div>

        <div class="order_address">
            <h6>Shipping address</h6>

            <span>Country: </span>
            <span>${requestScope.selectedOrder.address.country}"</span>
            <br>

            <span>Region: </span>
            <span class="">${requestScope.selectedOrder.address.region}</span>
            <br>

            <span>City</span>
                <span class="">${requestScope.selectedOrder.address.city}</span>
            <br>

            <span>Zip</span>
            <span class="">${requestScope.selectedOrder.address.zip}</span>
            <br>

            <span>Street</span>
            <span class="">${requestScope.selectedOrder.address.street}</span>
            <br>

            <span>Building</span>
            <span class="">${requestScope.selectedOrder.address.building}</span>
            <br>

            <span>Flat </span>
            <span class="">${requestScope.selectedOrder.address.flat}</span>
            <br>

        </div>

        <div class="order_payment">
            Payment:
            <div class="">${requestScope.selectedOrder.payment.name}</div>
            <div class="">${requestScope.selectedOrder.payment.description}</div>
        </div>

        <div class="order_comment">
            Comment:
            <p class="">
                 ${requestScope.selectedOrder.comment}
            </p>
        </div>
    </jsp:attribute>

</t:backendLayout>
