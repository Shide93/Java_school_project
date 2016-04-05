<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>

    <jsp:attribute name="sidebar">
        <div>
            <div class="list-group">
                <c:if test="${requestScope.orders != null}">
                    <c:forEach var="order" items="${requestScope.orders}">
                        <a class="list-group-item" href="<c:url value="/backend/orders?orderId=${order.id}"/>">Order: ${order.id}</a>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="content">


        <h1 class="">Order #${requestScope.selectedOrder.id}</h1>
        <div class="order_statuses panel">
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
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </c:forEach>
        </div>
        <div class="panel panel-default">

           <div class="order-date panel-body">
               Placed: <fmt:formatDate value="${requestScope.selectedOrder.orderDate}"
                                       type="BOTH" dateStyle="short" timeStyle="short"/>
           </div>
        </div>

        <ul class="order_user">
            <li class="list-group-item">${requestScope.selectedOrder.user.name}  ${requestScope.selectedOrder.user.lastName}</li>
            <li  class="list-group-item">Email: ${requestScope.selectedOrder.user.email}</li>
            <li  class="list-group-item">Phone: ${requestScope.selectedOrder.user.phone}</li>
        </ul>


        <div class="order_products ">
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
                <c:forEach var="item" items="${requestScope.selectedOrder.products}">
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

        <div class="list-group">
            <div class="order_shipping list-group-item  row">
                <div class="col-lg-1"><strong>Shipping:</strong></div>
                <div class="col-lg-1"><strong>${requestScope.selectedOrder.shipping.name}</strong></div>
                <div class="col-lg-3">${requestScope.selectedOrder.shipping.description}</div>
                <div class=col-lg-1"><strong>${requestScope.selectedOrder.shipping.cost}$</strong></div>
            </div>
             <div class="order_payment list-group-item row">
                 <div class="col-lg-1"><strong>Payment:</strong></div>
                 <div class="col-lg-1"><strong>${requestScope.selectedOrder.payment.name}</strong></div>
                 <div class="col-lg-3">${requestScope.selectedOrder.payment.description}</div>
             </div>
        </div>

        <div class="order_address">
            <h4>Shipping address</h4>

            <table class="table">
                <tr>
                    <td>Country: </td>
                    <td>${requestScope.selectedOrder.address.country}"</td>
                </tr>
                <tr>
                    <td>Region: </td>
                    <td class="">${requestScope.selectedOrder.address.region}</td>
                </tr>
                <tr>
                    <td>City</td>
                    <td class="">${requestScope.selectedOrder.address.city}</td>
                </tr>
                <tr>
                    <td>Zip</td>
                    <td class="">${requestScope.selectedOrder.address.zip}</td>
                </tr>
                <tr>
                    <td>Street</td>
                    <td class="">${requestScope.selectedOrder.address.street}</td>
                </tr>
                <tr>
                    <td>Building</td>
                    <td class="">${requestScope.selectedOrder.address.building}</td>
                </tr>
                <tr>
                    <td>Flat </td>
                    <td class="">${requestScope.selectedOrder.address.flat}</td>
                </tr>
            </table>

        </div>



        <div class="order_comment row">
            <div class="col-lg-1"><strong>Comment:</strong></div>
            <div class="col-lg-1"></div>
            <div class="col-lg-9 well">
                <p class=" lead">
                        ${requestScope.selectedOrder.comment}
                </p>
            </div>
        </div>
    </jsp:attribute>

</t:backendLayout>
