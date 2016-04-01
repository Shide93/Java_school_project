<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-default">
    <div class="navbar-header">
        <a href="<c:url value="/backend"/>" class="navbar-brand">WebShop backend</a>
    </div>
    <ul class="nav navbar-nav">

        <li class="">
            <a href="<c:url value="/backend/orders"/>">Orders</a>
        </li>
        <li class="">
            <a href="<c:url value="/backend/categories"/>">Categories</a>
        </li>
        <li class="">
            <a href="<c:url value="/backend/products"/>">Products</a>
        </li>
        <li class="">
            <a href="<c:url value="/backend/features"/>">Features</a>
        </li>
        <li class="">
            <a href="<c:url value="/backend/statistics"/>">Statistics</a>
        </li>
        <li class="">
            <a href="<c:url value="/backend/users"/>">User rights</a>
        </li>
        <li class="">
            <a href="<c:url value="/"/>">To storefront</a>
        </li>

    </ul>

</div>
