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
    </head>
    <body>
        <header>
            <jsp:invoke fragment="header"/>
        </header>

        <div ckass="sidebar">
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
