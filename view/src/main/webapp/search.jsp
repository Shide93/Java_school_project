<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">
        <h1>Search products</h1>
        <div class="row">
                <form action="<c:url value="/search"/>" method="post">
                    <c:forEach var="feature" items="${requestScope.features}">
                        <h5>${feature.name}</h5>
                        <c:forEach var="fValue" items="${requestScope.featureValues}">
                            <c:if test="${fValue.featureId eq feature.id}">
                                <label>
                                    <input type="checkbox" name="features" value="${feature.id}/${fValue.value}">
                                    ${fValue.value}
                                </label>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    <input type="submit" class="" value="Search">
                </form>
        </div>
    </jsp:attribute>

</t:frontendLayout>
