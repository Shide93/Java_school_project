<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:frontendLayout>
    <jsp:attribute name="content">
        <div class="checkout">
            <form:form class="" role="form" action="/checkout" method="post" modelAttribute="order">

                <div class = "contact-info">
                    <div class="general form-group panel panel-default">
                        <div class="panel-heading">Contact info:</div>
                        <div class="panel-body">
                            <label>Name
                                <form:input class="form-control" type="text" path="user.name"/>
                            </label>
                            <form:errors path="user.name" cssClass="error" /><br>
                            <label>Last name
                                <form:input class="form-control" type="text" path="user.lastName"/>
                            </label>
                            <form:errors path="user.lastName" cssClass="error" /><br>
                            <label>Phone
                                <form:input class="form-control input-medium bfh-phone"
                                            data-format="+d (ddd) ddd dddd" type="text" path="user.phone" />
                            </label>
                            <form:errors path="user.phone" cssClass="error" /><br>
                        </div>
                    </div>
                    <div class="address form-group panel panel-default">
                        <div class="panel-heading">Shipping address</div>
                        <div class="panel-body">
                            <label>Country
                                <form:input class="form-control" type="text" path="address.country" />
                            </label>
                            <form:errors path="address.country" cssClass="error" /><br>

                            <label>Region
                                <form:input class="form-control" type="text" path="address.region" />
                            </label>
                            <form:errors path="address.region" cssClass="error" /><br>

                            <label>City
                                <form:input class="form-control" type="text" path="address.city" />
                            </label>
                            <form:errors path="address.city" cssClass="error" /><br>

                            <label>Zip
                                <form:input class="form-control" type="text" path="address.zip" />
                            </label>
                            <form:errors path="address.zip" cssClass="error" /><br>

                            <label>Street
                                <form:input class="form-control" type="text" path="address.street" />
                            </label>
                            <form:errors path="address.street" cssClass="error" /><br>

                            <label>Building
                                <form:input class="form-control" type="text" path="address.building" />
                            </label>
                            <form:errors path="address.building" cssClass="error" /><br>

                            <label>Flat
                                <form:input class="form-control" type="text" path="address.flat" />
                            </label>
                            <form:errors path="address.flat" cssClass="error" /><br>
                        </div>
                    </div>
                </div>

                <div class = "shipping form-group panel panel-default">
                    <div class="panel-heading">Shipping type:</div>
                    <div class="panel-body">
                        <c:forEach var="shippingType" items="${requestScope.shippingTypes}">
                            <div class="radio">
                                <label>
                                    <form:radiobutton path="shipping.id" shipping-cost="${shippingType.cost}" value="${shippingType.id}"/>
                                        ${shippingType.name}
                                </label>
                                <span class="cost"><span class="shipping_cost">${shippingType.cost}</span>$</span>
                                <div class="description">
                                        ${shippingType.description}
                                </div>
                            </div>
                        </c:forEach>
                        <form:errors path="shipping" cssClass="error" /><br>
                    </div>
                </div>
                <div class = "payment form-group panel panel-default">
                    <div class="panel-heading">Payment type:</div>
                    <div class="panel-body">
                        <c:forEach var="paymentType" items="${requestScope.paymentTypes}">
                            <div class="radio">
                                <label>
                                    <form:radiobutton path="payment.id" value="${paymentType.id}"/>
                                        ${paymentType.name}
                                </label>
                                <div class="description">
                                        ${paymentType.description}
                                </div>
                            </div>
                        </c:forEach>
                        <form:errors path="payment" cssClass="error" /><br>
                    </div>
                </div>
                <div class = "confirmation form-group">

                    <table class="table table-striped">
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
                            <td>Shipping:</td>
                            <td colspan="2"><span class="shipping_total">0</span></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td>Total cost:</td>
                            <td colspan="2"><span class="order_total">${sessionScope.cart.summary}</span></td>
                        </tr>
                        </tfoot>
                    </table>
                    <div class="form-group">
                        <span>Comment:</span>
                        <form:textarea class="comment form-control" path="comment" placeholder="Add your comment to order here"/>
                        <br>
                        <input class="btn btn-primary" type="submit" value="Buy">
                    </div>
                </div>
            </form:form>
        </div>
        <script>
            $(document).ready( function(){
                $("input[name='shipping.id']").on('change', function(e) {
                    var shippingCost = parseInt($("input[name='shipping.id']:checked")
                            .attr("shipping-cost"));
                    var total = parseInt(${sessionScope.cart.summary});
                    $('.shipping_total').text(shippingCost);
                    $('.order_total').text(total + shippingCost);
                });
            });
        </script>
    </jsp:attribute>

</t:frontendLayout>

