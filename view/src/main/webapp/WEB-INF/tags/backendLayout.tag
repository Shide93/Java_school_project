<%@tag description="frontend layout" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="content" fragment="true" %>
<%@attribute name="sidebar" fragment="true" %>

<t:mainLayout>
    <jsp:attribute name="header">
        <jsp:include page="backendHeader.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="sidebar">
          <jsp:invoke fragment="sidebar"/>
    </jsp:attribute>
    <jsp:attribute name="content">
       <jsp:invoke fragment="content"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <div>this is footer</div>
    </jsp:attribute>
</t:mainLayout>