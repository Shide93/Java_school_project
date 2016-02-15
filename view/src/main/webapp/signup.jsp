<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">

         <div class="">
             <h2>Sign up</h2>
             <form class="" action="/signup" method="post">
                 email<br>
                 <input class="" type="text" name="email"><br>

                 password<br>
                 <input class="" type="text" name="password"><br>

                 <input class="" type="submit">

             </form>
         </div>
    </jsp:attribute>

</t:frontendLayout>

