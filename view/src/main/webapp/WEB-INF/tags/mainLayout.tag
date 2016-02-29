<%@tag description="main layout" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="sidebar" fragment="true" %>
<%@attribute name="content" fragment="true" %>
<%@attribute name="title"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>"type="text/css"/>
        <link rel="stylesheet" href="<c:url value="/css/main.css"/>"type="text/css"/>
        <script type="text/javascript" src="<c:url value="/js/jquery-1.12.1.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/main.js"/>"></script>
        <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <header>
            <div class="container">
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
            </div>
        </header>
        <main>
            <div class="container">

                <div class="row">
                    <!--Адаптивный блок aside-->
                    <aside class="col-lg-3">
                        <jsp:invoke fragment="sidebar"/>
                    </aside>
                    <!--Адаптивный блок article-->
                    <article class="col-lg-9">
                        <jsp:invoke fragment="content"/>
                    </article>
                </div>
            </div>
        </main>
        <footer>
            <div class="container">
                <jsp:invoke fragment="footer"/>
            </div>
        </footer>
    </body>
</html>
