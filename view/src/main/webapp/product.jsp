<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">
        <div class="">
            <h1 class="">Product ${requestScope.product.name}</h1>
            <div class="image">
                here will be product image
            </div>
            <div class="">
                <form class="add_to_cart" action="<c:url value="/cart?action=add"/>" method="post">
                    <input type="hidden" name="product_id" value="${requestScope.product.id}">
                    <div class="price">
                        Price: ${requestScope.product.price}
                    </div>
                    <div class="stock">
                        In stock: ${requestScope.product.stock}
                    </div>
                    <label> Quantity:
                        <input type="text" name="quantity" value="1">
                    </label>

                    <input class="" type="submit" value="Add to cart">
                </form>
            </div>
            <div class="description">
                ${requestScope.product.description}
            </div>
            <div class="features">
               <ul>
                    <c:forEach var="feature_val" items="${requestScope.product.features}">
                        <li>
                            ${feature_val.feature.name} - ${feature_val.value}
                        </li>
                    </c:forEach>
               </ul>
            </div>

        </div>

    </jsp:attribute>

</t:frontendLayout>
