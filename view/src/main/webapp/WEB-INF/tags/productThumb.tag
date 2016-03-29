<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="product" required="true"
              type="com.tsystems.javaschool.webshop.dao.entities.Product"
              description="Current product to display" %>

<div class="product">
    <form class="add_to_cart" action="<c:url value="/cart?action=add"/>" method="post">
        <div class="row">
            <div class="image col-lg-2">
                <img class="img-responsive img-thumbnail" src="<c:url value="/resources/img/default_product.png"/>">
            </div>
            <div class="col-lg-8">
                <input type="hidden" name="productId" value="${product.id}">
                <div class="name">
                    <a href="/product/${product.id}">${product.name}</a>
                </div>
                <div class="price">
                    Price: <span class="">${product.price}</span> $
                </div>
                <div class="stock">
                    In stock: ${product.stock}
                </div>
                <input type="hidden" name="quantity" value="1">
            </div>
            <div class="col-lg-2">
                <c:if test="${product.stock > 0}">
                    <input class="btn btn-primary" type="submit" value="Add to cart">
                </c:if>
                <c:if test="${product.stock <= 0}">
                    <input class="btn btn-primary disabled" disabled type="button"  value="Out of stock">
                </c:if>
            </div>
        </div>
    </form>
</div>