<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:backendLayout>

    <jsp:attribute name="sidebar">
        <div>
            Daily sales:
            1 000 000 $
        </div>
    </jsp:attribute>
    <jsp:attribute name="content">
        <h2>Product features</h2>

            <table>
                <thead>
                <tr>
                    <th>Feature name</th>
                    <th>Type</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="feature" items="${requestScope.features}">
                    <tr feature-id="${feature.id}">
                        <td>
                            <label>
                                <input type="text" name="feature_name" value="${feature.name}">
                            </label>
                        </td>
                        <td>
                            <label>
                                <select class="" name="feature_type">
                                    <c:forEach var="type" items="${requestScope.feature_types}">
                                        <option <c:if test="${feature.type == type}">selected</c:if>
                                                class="" value="${type}">
                                            ${type}
                                        </option>
                                    </c:forEach>
                                </select>
                            </label>
                        </td>
                        <td>
                            <input type="hidden" value="${feature.id}">
                            <input type="button" class="save_feature" value="Save">
                        </td>
                        <td>
                            <input type="button" class="delete_feature" value="Remove">
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
                <tfoot>
                <tr>
                    <td>
                        <label>
                            <input type="text" name="add_name" value="" placeholder="feature name">
                        </label>
                    </td>
                    <td>
                        <select class="" name="add_type" title="assaaa">
                            <c:forEach var="type" items="${requestScope.feature_types}">
                                <option class="" value="${type}">
                                        ${type}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td colspan="2">
                        <input type="button" class="add_feature" value="Add new feature">
                    </td>
                </tr>
                </tfoot>
            </table>

        <script>
            $(document).ready( function(){
                $(".add_feature").on('click', function(e) {
                    console.log("clicked");
                    var name = $("input[name=add_name]");
                    var type = $("select[name=add_type]");
                    $.post("?action=add", "name=" + name.val() + "&type=" + type.val(),
                            function(JData) {
                        console.log(JData);
                        //TODO: add new line in table
                    }, "json");
                });

                $(".save_feature").on('click', function(e) {
                    console.log("clicked");
                    var row = $(this).parents("[feature-id]");
                    var id = row.attr("feature-id");
                    var name = row.find("input[name=feature_name]");
                    var type = row.find("select[name=feature_type]");
                    $.post("?action=save", "id=" + id + "&name=" + name.val() + "&type=" + type.val(),
                            function(JData) {
                                console.log(JData);
                                //TODO: add new line in table, clear fields
                            }, "json");
                });

                $(".delete_feature").on('click', function(e) {
                    console.log("clicked");
                    var row = $(this).parents("[feature-id]");
                    var id = row.attr("feature-id");
                    $.post("?action=remove", "id=" + id,
                            function(JData) {
                                console.log(JData);
                                //TODO: add new line in table
                            }, "json");
                });

            });
        </script>
    </jsp:attribute>

</t:backendLayout>
