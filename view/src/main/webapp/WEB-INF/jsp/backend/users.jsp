<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>

    <jsp:attribute name="sidebar">
        <div>

        </div>
    </jsp:attribute>
    <jsp:attribute name="content">

        <c:forEach var="user" items="${requestScope.users}">
            <div class="user row">
                <div class="col-lg-1">${user.id}</div>
                <div class="col-lg-4">${user.email}</div>
                <div class="col-lg-5">${user.name} ${user.lastName}</div>
                <div class="col-lg-2">
                    <label>Admin
                        <input class="is_admin" user-id="${user.id}" type="checkbox"
                               <c:if test="${user.role eq 'ROLE_ADMIN'}">checked</c:if> >
                    </label>
                </div>
            </div>
        </c:forEach>

        <script>
            $(document).ready( function(){
                $(".is_admin").on('click', function(e) {
                    console.log($(this).prop('checked'));
                    var id =  $(this).attr("user-id");
                    var isAdmin = $(this).prop('checked');
                    $.post("?action=setRights", "id=" + id + "&isAdmin=" + isAdmin,
                            function(JData) {
                    });
                });
        });
        </script>
    </jsp:attribute>

</t:backendLayout>
