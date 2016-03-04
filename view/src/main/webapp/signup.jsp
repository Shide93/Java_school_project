<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">

         <div class="">
             <h2>Sign up</h2>
             <form class="" role="form" action="<c:url value="/signup"/>" method="post">
                 <div class="form-group">
                     <label>Name
                         <input class="form-control" type="text" name="name">
                     </label><br>


                     <label>Last name
                         <input class="form-control" type="text" name="last_name">
                     </label><br>
                 </div>
                 <div class="form-group">
                     <label> Email
                         <input class="form-control" type="" name="email">
                     </label><br>


                     <label>Password
                         <input class="form-control" type="password" name="password">
                     </label><br>

                     <label>Re-type password
                         <input class="form-control" type="password" name="password2">
                     </label><br>
                 </div>
                 <input class="btn btn-primary" type="submit">

             </form>
         </div>
    </jsp:attribute>

</t:frontendLayout>

