<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">
        <div class="checkout">
            <form class="" action="<c:url value="/checkout"/>" method="post">
                <div class = "contact-info">
                    <div class="general">
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
                    </div>
                    <div class="address">
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
                </div>
                <div class = "shipping">
                    <c:forEach var="shippingType" items="${requestScope.shippingTypes}">
                        <div>
                            <label>
                                <input type="radio" name="shipping_id" shipping-cost="${shippingType.cost}" value="${shippingType.id}">
                                    ${shippingType.name}
                            </label>
                            <span class="cost"><span class="shipping_cost">${shippingType.cost}</span>$</span>
                            <div class="description">
                                    ${shippingType.description}
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class = "payment">
                    <c:forEach var="paymentType" items="${requestScope.paymentTypes}">
                        <label>
                            <input type="radio" name="payment_id" value="${paymentType.id}">
                                ${paymentType.name}
                        </label>
                        <div class="description">
                                ${paymentType.description}
                        </div>
                    </c:forEach>
                </div>
                <div class = "confirmation">

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
                    <div class="">
                        <span>Comment:</span>
                        <textarea class="comment" name="comment" placeholder="Add your comment to order here"></textarea>
                        <br>
                        <input type="submit" value="Buy">
                    </div>
                </div>
            </form>
        </div>
        <script>
            $(document).ready( function(){
                $("input[name=shipping_id]").on('change', function(e) {
                    var shippingCost = parseInt($("input[name=shipping_id]:checked")
                            .attr("shipping-cost"));
                    var total = parseInt(${sessionScope.cart.summary});
                    $('.shipping_total').text(shippingCost);
                    $('.order_total').text(total + shippingCost);
                });
            });
        </script>
    </jsp:attribute>

</t:frontendLayout>

