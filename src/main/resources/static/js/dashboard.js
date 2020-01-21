$(".add").click(function (e) { 
    e.preventDefault();
    var plus = $(".plus");
    var plusDisplay = plus.css("display");

    if(plusDisplay != "none") {
        plus.css("display", "none");
        $(".minus").css("display", "inline-block");
    } else {
        plus.css("display", "inline-block");
        $(".minus").css("display", "none");
    }

});

function enableEdit(id) {
    $("."+id).attr("readonly", false);
    $(".edit"+id).css("display", "none");
    $(".action-update").css("display", "block");
}