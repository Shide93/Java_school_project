<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>
    <jsp:attribute name="sidebar">
       <div class="">
            <div class="list-group">
                <c:if test="${requestScope.categoryList != null}">
                   <c:forEach var="category" items="${requestScope.categoryList}">
                       <a class="list-group-item" href="<c:url value="/backend/categories?categoryId=${category.id}"/>">${category.name}</a>
                   </c:forEach>
                </c:if>
               <a class="add_category list-group-item" href="<c:url value="/backend/categories?categoryId=0"/>">Add category</a>
            </div>
       </div>
    </jsp:attribute>

    <jsp:attribute name="content">
         <c:if test="${param.removeFailed ne null}">
             <div class="alert alert-danger">${param.removeFailed}</div>
         </c:if>
        <c:if test="${requestScope.selectedCategory.id != 0}">
            <div class="">
                <h1 class="">Category ${requestScope.selectedCategory.name}</h1>
                <form:form class="" role="form" action="/backend/categories?action=save" method="post" modelAttribute="selectedCategory">
                    <div class="form-group">
                        <label>Category name:
                            <form:input class="form-control" type="text" path="name"/>
                        </label>
                        <form:errors path="name" cssClass="error" />
                    </div>
                    <div class="form-group">
                        <label>Category description
                            <form:textarea class="form-control" path="description"/>
                        </label>
                        <form:errors path="description" cssClass="error" />
                    </div>
                    <div>
                        <form:input type="hidden" path="id" value="${requestScope.selectedCategory.id}"/>
                        <input class="save btn btn-default"  type="submit" value="Save category">
                    </div>

                </form:form>

                <form:form action="/backend/categories?action=remove" method="post" modelAttribute="selectedCategory">
                    <form:input type="hidden" path="id" value="${requestScope.selectedCategory.id}"/>
                    <input class="delete btn btn-default" type="submit" value="Remove category">
                </form:form>

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

        <c:if test="${requestScope.selectedCategory.id == 0}">
            <div class="">
                <h1 class="">New category</h1>
                <form:form class="" role="form" action="/backend/categories?action=add" method="post" modelAttribute="selectedCategory">
                    <div class="">
                        <label>Category name:
                            <form:input class="form-control" type="text" path="name"/>
                        </label>
                        <form:errors path="name" cssClass="error" />
                    </div>
                    <div class="form-group">
                        <label>Category description
                        <form:textarea class="form-control" path="description"/>
                        </label>
                        <form:errors path="description" cssClass="error" />
                    </div>
                    <div>
                        <input class="add btn btn-default" type="submit" value="Add new category">
                    </div>
                </form:form>
            </div>
        </c:if>
    </jsp:attribute>

</t:backendLayout>
