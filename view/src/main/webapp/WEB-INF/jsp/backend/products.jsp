<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:backendLayout>

    <jsp:attribute name="sidebar">
       <div class="">
           <div class="list-group">
               <c:if test="${requestScope.products != null}">
                   <c:forEach var="product" items="${requestScope.products}">
                       <a class="list-group-item" href="<c:url value="/backend/products?productId=${product.id}"/>">${product.name}</a>
                   </c:forEach>
               </c:if>
               <form:form class=""
                     role="form"
                     action="/backend/products?action=add"
                     method="post">
                   <input class="add_product list-group-item btn btn-default" type="submit" value="Add product">
               </form:form>
           </div>
       </div>
    </jsp:attribute>
    <jsp:attribute name="content">
        <form:errors cssClass="error" />
         <c:if test="${param.removeFailed ne null}">
             <div class="alert alert-danger">${param.removeFailed}</div>
         </c:if>
        <c:if test="${requestScope.selectedProduct != null}">
            <div class="">
                <h1 class="">Product ${requestScope.selectedProduct.name}</h1>
                <form:form class="" role="form"
                           action="/backend/products?action=save"
                           modelAttribute="selectedProduct"
                           method="post">
                    <div class="name form-group">
                        <label>Product name:
                            <form:input class="form-control" type="text" path="name"
                                        value="${requestScope.selectedProduct.name}"/>
                        </label>
                        <form:errors path="name" cssClass="error" />
                    </div>
                    <div class="description form-group">
                        <label>Product description
                            <form:textarea class="form-control" path="description"/>
                        </label>
                        <form:errors path="description" cssClass="error" />
                    </div>
                    <div class="price form-group">
                        <label>Price:
                            <form:input class="form-control" type="number" path="price"
                                        value="${requestScope.selectedProduct.price}"/>
                        </label>
                        <form:errors path="price" cssClass="error" />
                    </div>
                    <div class="stock form-group">
                        <label>Stock:
                            <form:input class="form-control" type="number" path="stock"
                                        value="${requestScope.selectedProduct.stock}"/>
                        </label>
                        <form:errors path="stock" cssClass="error" />
                    </div>
                    <div class="category form-group">
                        <label>Category:
                            <form:select itemValue="category" class="form-control" path="category.id">
                                <form:options  items="${requestScope.categoryList}"  itemValue="id" itemLabel="name" />
                            </form:select>
                        </label>
                        <form:errors path="category.id" cssClass="error" />
                    </div>
                    <div class="features">
                        Features
                        <div class="row prod-feature"></div>
                        <c:forEach var="prodFeature" items="${requestScope.selectedProduct.features}"
                                   varStatus="status">
                            <div class="row prod-feature">
                                <div class="col-lg-4">
                                    <input type="hidden" class="form-control"
                                           value="${requestScope.selectedProduct.id}"
                                           name="features[${status.index}].productId">
                                    <input type="hidden" class="form-control"
                                           value="${prodFeature.featureId}"
                                           name="features[${status.index}].featureId">
                                    <div>${prodFeature.feature.name}:</div>
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
                            <c:if test="${param.addFeatureFailed ne null}">
                                <div class="alert alert-danger">${param.addFeatureFailed}</div>
                            </c:if>
                            <label>
                                <select form="add_feature_form" class="form-control"
                                        name="featureId">
                                    <c:forEach var="feature" items="${requestScope.features}">
                                        <option class="" value="${feature.id}">
                                            ${feature.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </label>
                            <input type="text" class="form-control" form="add_feature_form"
                                   value="" name="value">
                            <input class=" btn btn-default" type="submit"
                                   form="add_feature_form" value="Add feature">
                        </div>
                    </div>

                    <div>
                        <input class=""type="hidden" name="id" value="${requestScope.selectedProduct.id}">
                        <input class="save btn btn-primary" type="submit" value="Save product">
                    </div>
                </form:form>
                <form action="<c:url value="/backend/products?action=remove"/>" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="id" value="${requestScope.selectedProduct.id}">
                    <input class="delete btn btn-primary" type="submit" value="Remove product">
                </form>
            </div>
            <form id="add_feature_form" class="display-none" role="form"
                  action="<c:url value="/backend/products?action=addFeature"/>"
                  method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="productId" value="${requestScope.selectedProduct.id}"/>
            </form>
        </c:if>
        <c:if test="${requestScope.selectedProduct == null}">
            <h1>Product doesn't exists</h1>
        </c:if>
    </jsp:attribute>

</t:backendLayout>
