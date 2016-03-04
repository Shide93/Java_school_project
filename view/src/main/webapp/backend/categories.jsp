<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>
    <jsp:attribute name="sidebar">
       <div class="">
            <div class="list-group">
                <c:if test="${applicationScope.categoryList != null}">
                   <c:forEach var="category" items="${applicationScope.categoryList}">
                       <a class="list-group-item" href="<c:url value="/backend/categories?categoryId=${category.id}"/>">${category.name}</a>
                   </c:forEach>
                </c:if>
               <a class="add_category list-group-item" href="<c:url value="/backend/categories?categoryId=0"/>">Add category</a>
            </div>
       </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <c:if test="${requestScope.selectedCategory != null}">
            <div class="">
                <h1 class="">Category ${requestScope.selectedCategory.name}</h1>
                <form class="" role="form" action="<c:url value="/backend/categories?action=save"/>" method="post">
                <div class="form-group">
                    <label>Category name:
                        <input class="form-control" type="text" name="name" value="${requestScope.selectedCategory.name}">
                    </label>
                </div>
                <div class="form-group">
                    <label>Category description
                        <textarea class="form-control" name="description">${requestScope.selectedCategory.description}</textarea>
                    </label>
                </div>
                <div>
                    <input type="hidden" name="id" value="${requestScope.selectedCategory.id}">
                    <input class="save btn btn-default"  type="submit" value="Save category">
                </div>
                </form>
                <form action="<c:url value="/backend/categories?action=remove"/>" method="post">
                    <input type="hidden" name="id" value="${requestScope.selectedCategory.id}">
                    <input class="delete btn btn-default" type="submit" value="Remove category">
                </form>
                <div class="">
                    <c:forEach var="product" items="${requestScope.selectedCategory.products}">
                        <div>
                            <a href="<c:url value="/backend/products?productId=${product.id}"/>">
                                ${product.name}
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>
        <c:if test="${requestScope.selectedCategory == null}">
            <div class="">
                <h1 class="">New category</h1>
                <form class="" role="form" action="<c:url value="/backend/categories?action=add"/>" method="post">
                    <div class="">
                        <label>Category name:
                            <input class="form-control" type="text" name="name" value="">
                        </label>
                    </div>
                    <div class="form-group">
                        <label>Category description
                        <textarea class="form-control" name="description"></textarea>
                        </label>
                    </div>
                    <div>
                        <input class="add btn btn-default" type="submit" value="Add new category">
                    </div>
                </form>
            </div>
        </c:if>
    </jsp:attribute>

</t:backendLayout>
