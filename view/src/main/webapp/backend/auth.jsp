<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>

    <jsp:attribute name="sidebar">
        <div>

        </div>
    </jsp:attribute>
    <jsp:attribute name="content">

               <div class="">
                   <h2>Admin panel</h2>
                   <div>Please sing in</div>
                   <div>${requestScope.errMsg}</div>
                   <form class="" role="form" action="<c:url value="/backend/auth"/>" method="post">
                       <div class="form-group">
                           <label>Email
                               <input class="form-control" type="text" name="email">
                           </label><br>

                           <label>Password
                               <input class="form-control" type="password" name="password">
                           </label><br>
                           <div class="checkbox">
                               <label>
                                   <input type="checkbox" name="remember" checked> Remember me
                               </label>
                           </div>
                           <input class="btn btn-default" type="submit">

                       </div>
                   </form>
               </div>

    </jsp:attribute>

</t:backendLayout>
