<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>

    <jsp:attribute name="sidebar">
        <div>

        </div>
    </jsp:attribute>
    <jsp:attribute name="content">
        <h2>Product features</h2>

            <table class="table">
                <thead>
                <tr>
                    <th>Feature name</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="feature" items="${requestScope.features}">
                    <tr feature-id="${feature.id}">
                        <td>
                            <label>
                                <input class="form-control" type="text" name="feature_name" value="${feature.name}">
                            </label>
                        </td>

                        <td>
                            <input type="hidden" value="${feature.id}">
                            <input type="button" class="save_feature btn btn-default" value="Save">
                        </td>
                        <td>
                            <input type="button" class="delete_feature btn btn-default" value="Remove">
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
                <tfoot>
                <tr>
                    <td>
                        <label>
                            <input type="text" class="form-control" name="add_name" value="" placeholder="feature name">
                        </label>
                    </td>
                    <td colspan="2">
                        <input type="button" class="add_feature btn btn-default" value="Add new feature">
                    </td>
                </tr>
                </tfoot>
            </table>

        <script>
            $(document).ready( function(){
                $(".add_feature").on('click', function(e) {
                    console.log("clicked");
                    var name = $("input[name=add_name]");
                    $.post("?action=add", "name=" + name.val(),
                            function(JData) {
                        console.log(JData);
                        $("tbody").append(
                                '<tr feature-id="'+JData.id+'">'
                                + '<td>'
                                + '<label>'
                                + '<input class="form-control" type="text" name="feature_name" value="'+JData.name+'">'
                                + '</label>'
                                + '</td>'
                                + '<td>'
                                + '<input type="hidden" value="'+JData.id+'">'
                                + '<input type="button" class="save_feature btn btn-default" value="Save">'
                                + '</td>'
                                + '<td>'
                                + '<input type="button" class="delete_feature btn btn-default" value="Remove">'
                                + '</td>'
                                + '</tr>');
                        name.val("");
                    }, "json");
                });

                $("table").on('click', ".save_feature", function(e) {
                    console.log("clicked");
                    var row = $(this).parents("[feature-id]");
                    var id = row.attr("feature-id");
                    var name = row.find("input[name=feature_name]");
                    $.post("?action=save", "id=" + id + "&name=" + name.val(),
                            function(JData) {
                                console.log(JData);

                            }, "json");
                });

                $("table").on('click', ".delete_feature", function(e) {
                    console.log("clicked");
                    var row = $(this).parents("[feature-id]");
                    var id = row.attr("feature-id");
                    $.post("?action=remove", "id=" + id,
                            function(JData) {
                                if (JData.cantRemove) {
                                    alert(JData.cantRemove);
                                }
                                row.remove();
                            }, "json");
                });

            });
        </script>
    </jsp:attribute>

</t:backendLayout>
