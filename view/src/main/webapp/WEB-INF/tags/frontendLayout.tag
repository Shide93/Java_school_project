<%@tag description="frontend layout" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                        <sec:authorize access="isAuthenticated()">
                            <a class="btn btn-default navbar-btn"
                               href="<c:url value="/profile"/>">Hello,
                                <sec:authentication property="principal.username" />!
                            </a>

                            <form class="pull-right" action="<c:url value="/logout"/>" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input class="btn btn-default navbar-btn" value="logout" type="submit">
                            </form>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                            <a class="btn btn-default navbar-btn" href="<c:url value="/signin"/>">Sign in</a>
                            <a class="btn btn-default navbar-btn" href="<c:url value="/signup"/>">Sign up</a>
                        </sec:authorize>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="sidebar">
<%--suppress XmlPathReference --%>
<%--it warns but works properly--%>
        <jsp:include page="/WEB-INF/jsp/sidebar.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="content">
       <jsp:invoke fragment="content"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <div class="pull-right">@T-systems JavaSchool #16</div>
    </jsp:attribute>
</t:mainLayout>