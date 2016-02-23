<%@tag description="main layout" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="sidebar" fragment="true" %>
<%@attribute name="content" fragment="true" %>
<%@attribute name="title"%>
<html>
    <head>
        <title>${title}</title>
        <link rel="stylesheet" href="<c:url value="/css/main.css"/>"type="text/css"/>
        <script type="text/javascript" src="<c:url value="/js/main.js"/>"></script>
    </head>
    <body>
        <header class="header">
           Request: ${requestScope}
            <br>
           Session: ${sessionScope}
            <br>
           Cookies:  ${cookie}
            <br>
           Page: ${pageContext}
            <br>
           InitParam: ${initParam}
            <br>
           Param: ${param}
            <jsp:invoke fragment="header"/>
        </header>

        <div class="sidebar">
            <jsp:invoke fragment="sidebar"/>
        </div>

        <div class="content">
            <jsp:invoke fragment="content"/>
        </div>

        <footer class="footer">
            <jsp:invoke fragment="footer"/>
        </footer>
    </body>
</html>
