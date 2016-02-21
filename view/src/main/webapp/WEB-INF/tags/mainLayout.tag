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
        <link rel="stylesheet" href="../../css/main.css"/>
        <script type="text/javascript" src="../../js/main.js"></script>
    </head>
    <body>
        <header>
            ${requestScope}
            <br>
            ${sessionScope}
            <br>
            ${cookie}
            <br>
            ${pageContext}
            <br>
            ${initParam}
            <br>
            ${param}
            <jsp:invoke fragment="header"/>
        </header>

        <div class="sidebar">
            <jsp:invoke fragment="sidebar"/>
        </div>

        <div class="content">
            <jsp:invoke fragment="content"/>
        </div>

        <footer>
            <jsp:invoke fragment="footer"/>
        </footer>
    </body>
</html>
