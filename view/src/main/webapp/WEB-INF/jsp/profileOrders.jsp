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
                                    <div class="panel panel-default">

                                        <div class="order-date panel-body">
                                            Placed: <fmt:formatDate value="${order.orderDate}"
                                                                    type="BOTH" dateStyle="short" timeStyle="short"/>
                                        </div>
                                    </div>

                                    <ul class="order_user">
                                        <li class="list-group-item">${order.user.name}  ${order.user.lastName}</li>
                                        <li  class="list-group-item">Email: ${order.user.email}</li>
                                        <li  class="list-group-item">Phone: ${order.user.phone}</li>
                                    </ul>

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

                                    <div class="list-group">
                                        <div class="order_shipping list-group-item  row">
                                            <div class="col-lg-1"><strong>Shipping:</strong></div>
                                            <div class="col-lg-1"><strong>${order.shipping.name}</strong></div>
                                            <div class="col-lg-3">${order.shipping.description}</div>
                                            <div class=col-lg-1"><strong>${order.shipping.cost}$</strong></div>
                                        </div>
                                        <div class="order_payment list-group-item row">
                                            <div class="col-lg-1"><strong>Payment:</strong></div>
                                            <div class="col-lg-1"><strong>${order.payment.name}</strong></div>
                                            <div class="col-lg-3">${order.payment.description}</div>
                                        </div>
                                    </div>

                                    <div class="order_address">
                                        <h4>Shipping address</h4>

                                        <table class="table">
                                            <tr>
                                                <td>Country: </td>
                                                <td>${order.address.country}"</td>
                                            </tr>
                                            <tr>
                                                <td>Region: </td>
                                                <td class="">${order.address.region}</td>
                                            </tr>
                                            <tr>
                                                <td>City</td>
                                                <td class="">${order.address.city}</td>
                                            </tr>
                                            <tr>
                                                <td>Zip</td>
                                                <td class="">${order.address.zip}</td>
                                            </tr>
                                            <tr>
                                                <td>Street</td>
                                                <td class="">${order.address.street}</td>
                                            </tr>
                                            <tr>
                                                <td>Building</td>
                                                <td class="">${order.address.building}</td>
                                            </tr>
                                            <tr>
                                                <td>Flat </td>
                                                <td class="">${order.address.flat}</td>
                                            </tr>
                                        </table>

                                    </div>

                                    <div class="order_comment row">
                                        <div class="col-lg-1"><strong>Comment:</strong></div>
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-9 well">
                                            <p class=" lead">
                                                    ${order.comment}
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </jsp:attribute>

</t:frontendLayout>