<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">
         <c:if test="${requestScope.product == null}">
             <h1>Product doesn't exist</h1>
         </c:if>
        <c:if test="${requestScope.product != null}">
            <div class="">
                <div class="row">
                    <div class="image col-lg-4">
                        <img src="<c:url value="/resources/img/default_product.png"/>">
                    </div>
                    <div class="col-lg-8">
                        <h3 class="">Product ${requestScope.product.name}</h3>
                        <form class="add_to_cart" role="form" action="<c:url value="/cart?action=add"/>" method="post">
                            <input type="hidden" name="product_id" value="${requestScope.product.id}">
                            <div class="price">
                                Price: ${requestScope.product.price}
                            </div>
                            <div class="stock">
                                In stock: ${requestScope.product.stock}
                            </div>
                            <label> Quantity:
                                <input type="text" class="form-control" name="quantity" value="1">
                            </label>
                            <br>
                            <input class="btn btn-default" type="submit" value="Add to cart">
                        </form>
                    </div>
                </div>
                <div class="description">
                    <p class="lead">${requestScope.product.description}</p>
                </div>
                <div class="features">
                   <table class="table">
                        <tr>
                            <th>Feature</th>
                            <th>Value</th>
                        </tr>
                        <c:forEach var="feature_val" items="${requestScope.product.features}">
                            <tr>
                                <td>${feature_val.feature.name}</td>
                                <td>${feature_val.value}</td>
                            </tr>
                        </c:forEach>
                   </table>
                </div>

            </div>
        </c:if>
    </jsp:attribute>

</t:frontendLayout>
