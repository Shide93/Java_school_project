<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:frontendLayout>
    <jsp:attribute name="content">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#profile" data-toggle="tab">Profile</a></li>
            <li><a href="#myorders" data-toggle="tab">Orders</a></li>
        </ul>
        <div class="tab-content">
            <div id="profile" class="tab-pane fade in active">
                <h2>Your Profile, ${sessionScope.user.name}!</h2>
                <p>${requestScope.notValid}</p>
                <form class="" role="form" action="<c:url value="/profile"/>" method="post">
                    <div class="form-group">
                        <h6>Change email:</h6>
                        <label>Email
                            <input class="form-control" type="text" name="email" value="${sessionScope.user.email}">
                        </label><br>
                    </div>
                    <div class="form-group">
                        <h6>Change password:</h6>

                        <label>Password
                            <input class="form-control" type="password" name="password" value="${sessionScope.user.password}">
                        </label>

                        <label>Re-type password
                            <input class="form-control" type="password" name="password2">
                        </label><br>
                    </div>
                    <div class="form-group">
                        <h6>Additional info</h6>
                        <label>Name
                            <input class="form-control" type="text" name="name" value="${sessionScope.user.name}">
                        </label><br>


                        <label>Last name
                            <input class="form-control" type="text" name="last_name" value="${sessionScope.user.lastName}">
                        </label><br>
                        <label>Phone
                            <input class="form-control" type="text" name="phone" value="${sessionScope.user.phone}">
                        </label><br>

                        <label>Birth date
                            <input class="form-control" type="text" name="birth_date"
                                   value="<fmt:formatDate value="${sessionScope.user.birthDate}"
                                                type="DATE" pattern="dd-MM-yyyy"/>">

                        </label>
                    </div>
                    <div class="form-group">
                        <h6>Shipping address</h6>
                        <label>Country
                            <input class="form-control" type="text" name="country" value="${sessionScope.user.address.country}">
                        </label><br>

                        <label>Region
                            <input class="form-control" type="text" name="region" value="${sessionScope.user.address.region}">
                        </label><br>

                        <label>City
                            <input class="form-control" type="text" name="city" value="${sessionScope.user.address.city}">
                        </label><br>

                        <label>Zip
                            <input class="form-control" type="text" name="zip" value="${sessionScope.user.address.zip}">

                        </label>

                        <label>Street
                            <input class="form-control" type="text" name="street" value="${sessionScope.user.address.street}">
                        </label><br>

                        <label>Building
                            <input class="form-control" type="text" name="building" value="${sessionScope.user.address.building}">
                        </label><br>

                        <label>Flat
                            <input class="form-control" type="text" name="flat" value="${sessionScope.user.address.flat}">
                        </label><br>

                    </div>
                    <input class="btn btn-primary" value="Save profile" type="submit">

                </form>
            </div>

            <div id="myorders" class="tab-pane fade">
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