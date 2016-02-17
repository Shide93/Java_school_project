<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:frontendLayout>
    <jsp:attribute name="content">

         <div class="">
             <h2>Sign up</h2>
             <form class="" action="/signup" method="post">
                 <div class="">Name</div>
                 <input class="" type="text" name="name"><br>

                 <div class="">Last name</div>
                 <input class="" type="text" name="last_name"><br>

                 <div class="">Email</div>
                 <input class="" type="text" name="email"><br>

                 <div class="">Password</div>
                 <input class="" type="password" name="password"><br>

                 <div class="">Re-type password</div>
                 <input class="" type="password" name="password2"><br>

                 <input class="" type="submit">

             </form>
         </div>
    </jsp:attribute>

</t:frontendLayout>

