$(document).ready( function() {
    $(".add_to_cart").on('submit', function (e) {
        e.preventDefault();
        $.post("/cart?action=add", $(this).serialize(),
            function (JData) {
                console.log(JData);
                if (JData.error === "duplicate") {
                    alert("You already have this product in your cart");
                    return;
                }
                $(".cart_count").text(JData.count);
                $(".cart_summary").text(JData.summary);
            }, "json");
    });

    $(".cart_product_quantity").on('change', function(e) {
        var row = $(this).parents("[product-id]");
        var id = row.attr("product-id");
        var quantity = $(this).val();
        $.post("?action=edit", "product_id=" + id + "&quantity=" + quantity,
            function(JData) {
                console.log(JData);
                $(".cart_count").text(JData.count);
                $(".cart_summary").text(JData.summary);
                var cost = parseInt($(row).find(".product_price").html()) * parseInt($(row).find(".cart_product_quantity").val());
                $(row).find(".product_cost").html(cost);
            }, "json");
    });

    $(".cart_product_remove").on('click', function(e) {
        var row = $(this).parents("[product-id]");
        var id = row.attr("product-id");
        $.post("?action=remove", "product_id=" + id,
            function(JData) {
                row.hide();
                $(".cart_count").text(JData.count);
                $(".cart_summary").text(JData.summary);
            }, "json");
    });

    //CSRF AJAX protection
    $(document).ajaxSend(function(e, xhr, options) {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        xhr.setRequestHeader(header, token);
    });





});