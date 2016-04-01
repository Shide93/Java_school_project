<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:frontendLayout>
    <jsp:attribute name="content">

         <div class="">
             <h2>Sign up</h2>
             <p>${requestScope.notValid}</p>
             <form:form class="" role="form" action="/signup" method="post" modelAttribute="user">
                 <div class="form-group">
                     <label>Name
                         <form:input class="form-control" type="text" path="name" />
                     </label>
                     <form:errors path="name" cssClass="error" /><br>


                     <label>Last name
                         <form:input class="form-control" type="text" path="lastName"/>
                     </label>
                     <form:errors path="lastName" cssClass="error" /><br>
                 </div>
                 <div class="form-group">
                     <label> Email
                         <form:input class="form-control" type="email" path="email"/>
                     </label>
                     <form:errors path="email" cssClass="error" /><br>


                     <label>Password
                         <form:password class="form-control" path="password"/>
                     </label>
                     <form:errors path="password" cssClass="error" /><br>

                     <label>Re-type password
                         <form:password class="form-control" path="confirmPassword"/>
                     </label>
                     <form:errors path="confirmPassword" cssClass="error" />

                 </div>
                 <input class="btn btn-primary" type="submit">

             </form:form>
         </div>
    </jsp:attribute>

</t:frontendLayout>

