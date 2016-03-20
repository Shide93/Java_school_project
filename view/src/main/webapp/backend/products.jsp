<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>

    <jsp:attribute name="sidebar">
       <div class="">
           <div class="list-group">
               <c:if test="${requestScope.products != null}">
                   <c:forEach var="product" items="${requestScope.products}">
                       <a class="list-group-item" href="<c:url value="/backend/products?productId=${product.id}"/>">${product.name}</a>
                   </c:forEach>
               </c:if>
               <a class="add_product list-group-item" href="<c:url value="/backend/products?productId=0"/>">Add product</a>
           </div>
       </div>
    </jsp:attribute>
    <jsp:attribute name="content">
        <c:if test="${requestScope.selectedProduct != null}">
            <div class="">
                <h1 class="">Product ${requestScope.selectedProduct.name}</h1>
                <form class="" role="form" action="<c:url value="/backend/products?action=save"/>" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="name form-group">
                        <label>Product name:
                            <input class="form-control" type="text" name="name" value="${requestScope.selectedProduct.name}">
                        </label>
                    </div>
                    <div class="description form-group">
                        <label>Product description
                            <textarea class="form-control" name="description">${requestScope.selectedProduct.description}</textarea>
                        </label>
                    </div>
                    <div class="price form-group">
                        <label>Price:
                            <input class="form-control" type="text" name="price" value="${requestScope.selectedProduct.price}">
                        </label>
                    </div>
                    <div class="stock form-group">
                        <label>Stock:
                            <input class="form-control" type="text" name="stock" value="${requestScope.selectedProduct.stock}">
                        </label>
                    </div>
                    <div class="category form-group">
                        <label>Category:
                            <select class="form-control" name="category.id">
                                <c:forEach var="category" items="${requestScope.categoryList}">
                                    <option <c:if test="${requestScope.selectedProduct.category.id == category.id}">selected</c:if>
                                            class="" value="${category.id}">
                                            ${category.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                    <div class="features">
                        Features
                        <div class="row prod-feature"></div>
                        <c:forEach var="prodFeature" items="${requestScope.selectedProduct.features}"  varStatus="status">
                            <div class="row prod-feature">
                                <div class="col-lg-4">
                                    <input type="hidden" class="form-control"
                                           value="${requestScope.selectedProduct.id}" name="features[${status.index}].productId">
                                    <label>
                                        <select class="form-control" name="features[${status.index}].featureId">
                                            <c:forEach var="feature" items="${requestScope.features}">
                                                <option <c:if test="${prodFeature.featureId == feature.id}">selected</c:if>
                                                        class="" value="${feature.id}">
                                                        ${feature.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                </div>
                                <div class="col-lg-4">
                                    <label>
                                        <input type="text" class="form-control"
                                               value="${prodFeature.value}" name="features[${status.index}].value">
                                    </label>

                                </div>
                            </div>
                        </c:forEach>
                        <div class="">
                            <input class="add_product_feature btn btn-default" type="button" value="Add feature">
                        </div>
                    </div>

                    <div>
                        <input class=""type="hidden" name="id" value="${requestScope.selectedProduct.id}">
                        <input class="save btn btn-primary" type="submit" value="Save product">
                    </div>
                </form>
                <form action="<c:url value="/backend/products?action=remove"/>" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="id" value="${requestScope.selectedProduct.id}">
                    <input class="delete btn btn-primary" type="submit" value="Remove product">
                </form>
            </div>

        </c:if>
        <c:if test="${requestScope.selectedProduct == null}">

            <div class="">
                <h1 class="">New product</h1>
                <form class="" role="form" action="<c:url value="/backend/products?action=add"/>" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="name">
                        <label>Product name:
                            <input class="form-control" type="text" name="name" value="">
                        </label>
                    </div>
                    <div class="description">
                        <label>Product description
                            <textarea class="form-control" name="description"></textarea>
                        </label>
                    </div>
                    <div class="price">
                        <label>Price:
                            <input class="form-control" type="text" name="price" value="">
                        </label>
                    </div>
                    <div class="stock">
                        <label>Stock:
                            <input class="form-control" type="text" name="stock" value="">
                        </label>
                    </div>
                    <div class="category">
                        <label>Category:
                            <select class="form-control" class="" name="category">
                                <c:forEach var="category" items="${applicationScope.categoryList}">
                                    <option class="" value="${category.id}">
                                            ${category.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                    <div class="features">
                        Features
                            <div class="row prod-feature"></div>
                        <div class="row">
                            <input class="add_product_feature btn btn-default" type="button" value="Add feature">
                        </div>
                    </div>
                    <div>
                        <input class="add btn btn-primary" type="submit" value="Add new product">
                    </div>

                </form>
            </div>

        </c:if>

        <script>
            $(document).ready( function() {
                $(".add_product_feature").on('click', function(e) {
                    $('.prod-feature:last').after(
                        '<div class="row prod-feature">'
                            + '<div class="col-lg-4">'
                                    + '<label>'
                                        + '<select class="form-control" name="prod_features[id]">'
                                           + '<c:forEach var="feature" items="${requestScope.features}">'
                                               + '<option class="" value="${feature.id}">'
                                                    + '${feature.name}'
                                                + '</option>'
                                           + '</c:forEach>'
                                       + '</select>'
                                    + '</label>'
                            + '</div>'
                            + '<div class="col-lg-4">'
                                + '<label>'
                                    + '<input type="text" class="form-control" value="" name="prod_features[value]">'
                                + '</label>'
                            + '</div>'
                       + '</div>');
                });
            });
        </script>
    </jsp:attribute>

</t:backendLayout>
