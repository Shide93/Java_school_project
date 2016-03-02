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
                <form class="" action="<c:url value="/profile"/>" method="post">
                    <div class="">
                        <h6>Change email:</h6>
                        <label>Email
                            <input class="" type="" name="email" value="${sessionScope.user.email}">
                        </label><br>
                    </div>
                    <div>
                        <h6>Change password:</h6>

                        <label>Password
                            <input class="" type="password" name="password" value="${sessionScope.user.password}">
                            <span class = "">${requestScope.passNotEq}</span>
                        </label><br>

                        <div class="">Re-type password</div>
                        <label>
                            <input class="" type="password" name="password2">
                        </label><br>
                    </div>
                    <div class="">
                        <h6>Additional info</h6>
                        <div class="">Name</div>
                        <label>
                            <input class="" type="text" name="name" value="${sessionScope.user.name}">
                        </label><br>


                        <label>Last name
                            <input class="" type="text" name="last_name" value="${sessionScope.user.lastName}">
                        </label><br>
                        <label>Phone
                            <input class="" type="text" name="phone" value="${sessionScope.user.phone}">
                        </label><br>

                        <label>Birth date
                            <input class="" type="text" name="birth_date"
                                   value="<fmt:formatDate value="${sessionScope.user.birthDate}"
                                                type="DATE" pattern="dd-MM-yyyy"/>">
                            <span class = "">${requestScope.wrongBirth}</span>
                        </label><br>
                    </div>
                    <div class="">
                        <h6>Shipping address</h6>
                        <label>Country
                            <input class="" type="text" name="country" value="${sessionScope.user.address.country}">
                        </label><br>

                        <label>Region
                            <input class="" type="text" name="region" value="${sessionScope.user.address.region}">
                        </label><br>

                        <label>City
                            <input class="" type="text" name="city" value="${sessionScope.user.address.city}">
                        </label><br>

                        <label>Zip
                            <input class="" type="text" name="zip" value="${sessionScope.user.address.zip}">
                            <span class = "">${requestScope.wrongZip}</span>
                        </label><br>

                        <label>Street
                            <input class="" type="text" name="street" value="${sessionScope.user.address.street}">
                        </label><br>

                        <label>Building
                            <input class="" type="text" name="building" value="${sessionScope.user.address.building}">
                        </label><br>

                        <label>Flat
                            <input class="" type="text" name="flat" value="${sessionScope.user.address.flat}">
                        </label><br>

                    </div>
                    <input class="" value="Save profile" type="submit">

                </form>
            </div>

            <div id="myorders" class="tab-pane fade">
                <h1>My orders</h1>
                <c:forEach var="order" items="${requestScope.orders}">
                    <div class="">
                        <h3 class="">Order #${order.id}</h3>
                        <div class="order_statuses">
                          <h4>${order.orderStatus}</h4>
                        </div>
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

                        <div class="order_shipping">
                            Shipping:
                            <div class="">${order.shipping.name}</div>
                            <div class="">${order.shipping.description}</div>
                            <div class="">${order.shipping.cost}</div>
                        </div>

                        <div class="order_address">
                            <h6>Shipping address</h6>

                            <span>Country: </span>
                            <span>${order.address.country}"</span>
                            <br>

                            <span>Region: </span>
                            <span class="">${order.address.region}</span>
                            <br>

                            <span>City</span>
                            <span class="">${order.address.city}</span>
                            <br>

                            <span>Zip</span>
                            <span class="">${order.address.zip}</span>
                            <br>

                            <span>Street</span>
                            <span class="">${order.address.street}</span>
                            <br>

                            <span>Building</span>
                            <span class="">${order.address.building}</span>
                            <br>

                            <span>Flat </span>
                            <span class="">${order.address.flat}</span>
                            <br>

                        </div>

                        <div class="order_payment">
                            Payment:
                            <div class="">${order.payment.name}</div>
                            <div class="">${order.payment.description}</div>
                        </div>

                        <div class="order_comment">
                            Comment:
                            <p class="">
                                    ${order.comment}
                            </p>
                        </div>

                    </div>
                </c:forEach>

            </div>
        </div>
    </jsp:attribute>

</t:frontendLayout>