<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">

         <div class="">
             <h2>Sign up</h2>
             <form class="" action="<c:url value="/signup"/>" method="post">

                 <label>Name
                     <input class="" type="text" name="name">
                 </label><br>


                 <label>Last name
                     <input class="" type="text" name="last_name">
                 </label><br>


                 <label> Email
                     <input class="" type="email" name="email">
                 </label><br>


                 <label>Password
                     <input class="" type="password" name="password">
                 </label><br>

                 <label>Re-type password
                     <input class="" type="password" name="password2">
                 </label><br>

                 <input class="" type="submit">

             </form>
         </div>
    </jsp:attribute>

</t:frontendLayout>

