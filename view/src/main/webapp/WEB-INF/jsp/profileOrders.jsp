<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:frontendLayout>
    <jsp:attribute name="content">
        <ul class="nav nav-tabs">
            <li class=""><a href="<c:url value="/profile"/>">Profile</a></li>
            <li class=""><a href="<c:url value="/profile/credentials"/>">Credentials</a></li>
            <li class="active"><a href="<c:url value="/profile/orders"/>" >Orders</a></li>
        </ul>
        <div class="tab-content">
            <div id="myorders" class="tab-pane fade in active">
                <h1>My orders</h1>
                <div class="panel-group" id="accordion">
                    <c:forEach var="order" items="${requestScope.orders}">
                        <div class="panel panel-default">

                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#order-${order.id}">Order #${order.id} <small class="">${order.orderStatus}</small></a>
                                </h4>
                            </div>
                            <div id="order-${order.id}" class="panel-collapse collapse">
                                <!-- Содержимое 1 панели -->
                                <div class="panel-body">
                                    <div class="order-date">
                                        Placed: <fmt:formatDate value="${order.orderDate}"
                                                                type="BOTH" dateStyle="short" timeStyle="short"/>
                                    </div>

                                    <div class="order_user">
                                        <div>${order.user.name}  ${order.user.lastName}</div>
                                        <div>Email: ${order.user.email}</div>
                                        <div>Phone: ${order.user.phone}</div>

                                    </div>

                                    <div class="order_products">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th>Product name</th>
                                                <th>Price</th>
                                                <th>Quantity</th>
                                                <th>Cost</th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                            <c:forEach var="item" items="${order.products}">
                                                <tr>
                                                    <td>${item.product.name}</td>
                                                    <td>${item.price} $</td>
                                                    <td>
                                                        <span class="">${item.quantity}</span>
                                                    </td>
                                                    <td>${item.summary}</td>
                                                </tr>
                                            </c:forEach>

                                            </tbody>
                                            <tfoot>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td>Subtotal:</td>
                                                <td>${order.total}</td>
                                            </tr>
                                            <tr>
                                                <td>Shipping cost:</td>
                                                <td></td>
                                                <td>Total:</td>
                                                <td>${order.shipping.cost}</td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td>Total:</td>
                                                <td>${order.total + order.shipping.cost}</td>
                                            </tr>
                                            </tfoot>
                                        </table>
                                    </div>

                                    <p class="order_shipping">
                                        Shipping:
                                    <div class="">${order.shipping.name}</div>
                                    <div class="">${order.shipping.description}</div>
                                    <div class="">${order.shipping.cost}</div>
                                    </p>

                                    <div class="order_address">
                                        <h6>Shipping address</h6>
                                        <dl class="dl-horizontal">
                                            <dt>Country: </dt>
                                            <dd>${order.address.country}"</dd>

                                            <dt>Region: </dt>
                                            <dd class="">${order.address.region}</dd>

                                            <dt>City</dt>
                                            <dd class="">${order.address.city}</dd>

                                            <dt>Zip</dt>
                                            <dd class="">${order.address.zip}</dd>

                                            <dt>Street</dt>
                                            <dd class="">${order.address.street}</dd>

                                            <dt>Building</dt>
                                            <dd class="">${order.address.building}</dd>

                                            <dt>Flat </dt>
                                            <dd class="">${order.address.flat}</dd>

                                        </dl>
                                    </div>

                                    <p class="order_payment">
                                        Payment:
                                    <div class="">${order.payment.name}</div>
                                    <div class="">${order.payment.description}</div>
                                    </p>

                                    <p class="order_comment">
                                        Comment:
                                    <p class="">
                                            ${order.comment}
                                    </p>
                                    </p>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                </div>
            </div>
        </div>
    </jsp:attribute>

</t:frontendLayout>