<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:frontendLayout>
    <jsp:attribute name="content">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#profile" data-toggle="tab">Profile</a></li>
            <li><a href="#myorders" data-toggle="tab">Orders</a></li>
        </ul>
        <div class="tab-content">
            <div id="profile" class="tab-pane fade in active">
                <h2>Your Profile, ${requestScope.user.name}!</h2>
                <form:form class="" role="form" action="/profile" method="post" modelAttribute="user">
                    <div class="">
                        <h6>Your email:</h6>
                       <div> ${requestScope.user.email}</div>
                    </div>

                    <div class="form-group">
                        <h6>Additional info</h6>
                        <label>Name
                            <form:input class="form-control" type="text" path="name"/>
                        </label>
                        <form:errors path="name" cssClass="error" /><br>

                        <label>Last name
                            <form:input class="form-control" type="text" path="lastName" />
                        </label>
                        <form:errors path="lastName" cssClass="error" /><br>
                        <label>Phone
                            <form:input class="form-control" type="text" path="phone" />
                        </label>
                        <form:errors path="phone" cssClass="error" /><br>

                        <label>Birth date
                            <form:input class="form-control" type="text" path="birthDate" />
                        </label>
                        <form:errors path="birthDate" cssClass="error" /><br>
                    </div>

                    <div class="form-group">
                        <h6>Shipping address</h6>
                        <label>Country
                            <form:input class="form-control" type="text" path="address.country"  />
                        </label>
                        <form:errors path="address.country" cssClass="error" /><br>

                        <label>Region
                            <form:input class="form-control" type="text" path="address.region"  />
                        </label>
                        <form:errors path="address.region" cssClass="error" /><br>

                        <label>City
                            <form:input class="form-control" type="text" path="address.city" />
                        </label>
                        <form:errors path="address.city" cssClass="error" /><br>

                        <label>Zip
                            <form:input class="form-control" type="text" path="address.zip"  />
                        </label>
                        <form:errors path="address.zip" cssClass="error" /><br>

                        <label>Street
                            <form:input class="form-control" type="text" path="address.street"  />
                        </label>
                        <form:errors path="address.street" cssClass="error" /><br>

                        <label>Building
                            <form:input class="form-control" type="text" path="address.building"  />
                        </label>
                        <form:errors path="address.building" cssClass="error" /><br>

                        <label>Flat
                            <form:input class="form-control" type="text" path="address.flat" value="${requestScope.user.address.flat}" />
                        </label>
                        <form:errors path="address.flat" cssClass="error" /><br>

                    </div>
                    <input class="btn btn-primary" value="Save profile" type="submit">

                </form:form>
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