<%@tag description="frontend layout" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="content" fragment="true" %>

<t:mainLayout>
    <jsp:attribute name="header">
        <div class="navbar navbar-default" role="navigation">

            <a class="navbar-brand" href="<c:url value="/"/>">WebShop</a>
            <div class="row">
                <!--cart -->
                <div class="cart col-lg-2 col-lg-offset-4">
                    <div>

                        <a class="btn btn-default navbar-btn" href="<c:url value="/cart"/>"><span class="glyphicon glyphicon-shopping-cart"></span> Your cart:</a>
                    </div>
                    <div class="">
                        <span class="cart_count">
                            <c:out value="${sessionScope.cart.count}" default="0"/>
                        </span>
                        items /
                        <span class="cart_summary">
                            <c:out value="${sessionScope.cart.summary}" default="0"/>
                        </span> $
                    </div>
                </div>
                <!--login -->
                <div class="col-lg-4 pull-right">
                    <div class="pull-right">
                        <c:if test="${sessionScope.user != null}">
                            <a class="btn btn-default navbar-btn" href="<c:url value="/profile"/>">Hello, ${sessionScope.user.name}!</a>
                            <a class="btn btn-default navbar-btn" href="<c:url value="/logout"/>">Logout</a>
                        </c:if>
                        <c:if test="${sessionScope.user == null}">
                            <a class="btn btn-default navbar-btn" href="<c:url value="/signin"/>">Sign in</a>
                            <a class="btn btn-default navbar-btn" href="<c:url value="/signup"/>">Sign up</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="sidebar">
       <jsp:include page="sidebar.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="content">
       <jsp:invoke fragment="content"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <div class="pull-right">@T-systems JavaSchool #16</div>
    </jsp:attribute>
</t:mainLayout>