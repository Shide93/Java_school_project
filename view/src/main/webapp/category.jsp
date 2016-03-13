<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">
        <c:if test="${requestScope.category == null}">
            <h1>Category doesn't exist</h1>
        </c:if>
        <c:if test="${requestScope.category != null}">
            <div class="category">
                <h1 class="">Category ${requestScope.category.name}</h1>
                <div class="description">
                    <p class="lead">${requestScope.category.description}</p>
                </div>
                <div class="product_list">
                    <c:forEach var="product" items="${requestScope.category.products}">
                        <div class="product">
                            <form class="add_to_cart" action="<c:url value="/cart?action=add"/>" method="post">
                                <div class="row">
                                    <div class="image col-lg-2">
                                        <img class="img-responsive img-thumbnail" src="<c:url value="/resources/img/default_product.png"/>">
                                    </div>
                                    <div class="col-lg-8">
                                        <input type="hidden" name="product_id" value="${product.id}">
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
                                        <input class="btn btn-primary" type="submit" value="Add to cart">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </c:if>
    </jsp:attribute>

</t:frontendLayout>
