<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>

    <jsp:attribute name="sidebar">
       <div class="">
           <ul class="">
               <c:forEach var="product" items="${requestScope.products}">
                   <li class="">
                       <a href="<c:url value="/backend/products?productId=${product.id}"/>">${product.name}</a>
                   </li>
               </c:forEach>
               <li class="add_category">
                   <a href="<c:url value="/backend/products?productId=0"/>">Add category</a>
               </li>
           </ul>
       </div>
    </jsp:attribute>
    <jsp:attribute name="content">
        <c:if test="${requestScope.selectedProduct != null}">
            <div class="">
                <h1 class="">Product ${requestScope.selectedProduct.name}</h1>
                <form class="" action="<c:url value="/backend/products?action=save"/>" method="post">
                    <div class="name">
                        <label>Product name:
                            <input type="text" name="name" value="${requestScope.selectedProduct.name}">
                        </label>
                    </div>
                    <div class="description">
                        <label>Product description
                            <textarea name="description">${requestScope.selectedProduct.description}</textarea>
                        </label>
                    </div>
                    <div class="price">
                        <label>Price:
                            <input type="text" name="price" value="${requestScope.selectedProduct.price}">
                        </label>
                    </div>
                    <div class="stock">
                        <label>Stock:
                            <input type="text" name="stock" value="${requestScope.selectedProduct.stock}">
                        </label>
                    </div>
                    <div class="category">
                        <label>Category:
                            <select class="" name="category">
                                <c:forEach var="category" items="${applicationScope.categoryList}">
                                    <option <c:if test="${requestScope.selectedProduct.category.id == category.id}">selected</c:if>
                                            class="" value="${category.id}">
                                            ${category.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                    <div class="features">
                        Features here
                    </div>
                    <div class="image">
                        Image Upload?
                    </div>
                    <div>
                        <input type="hidden" name="id" value="${requestScope.selectedProduct.id}">
                        <input class="save" type="submit" value="Save category">
                    </div>
                </form>
                <form action="<c:url value="/backend/products?action=remove"/>" method="post">
                    <input type="hidden" name="id" value="${requestScope.selectedProduct.id}">
                    <input class="delete" type="submit" value="Remove category">
                </form>
            </div>

        </c:if>
        <c:if test="${requestScope.selectedProduct == null}">

            <div class="">
                <h1 class="">New product</h1>
                <form class="" action="<c:url value="/backend/products?action=add"/>" method="post">
                    <div class="name">
                        <label>Product name:
                            <input type="text" name="name" value="">
                        </label>
                    </div>
                    <div class="description">
                        <label>Product description
                            <textarea name="description"></textarea>
                        </label>
                    </div>
                    <div class="price">
                        <label>Price:
                            <input type="text" name="price" value="">
                        </label>
                    </div>
                    <div class="stock">
                        <label>Stock:
                            <input type="text" name="stock" value="">
                        </label>
                    </div>
                    <div class="category">
                        <label>Category:
                            <select class="" name="category">
                                <c:forEach var="category" items="${applicationScope.categoryList}">
                                    <option class="" value="${category.id}">
                                            ${category.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                    <div class="features">
                        Features here
                    </div>
                    <div class="image">
                        Img upload?
                    </div>
                    <div>
                        <input class="add" type="submit" value="Add new product">
                    </div>
                </form>
            </div>

        </c:if>

    </jsp:attribute>

</t:backendLayout>
