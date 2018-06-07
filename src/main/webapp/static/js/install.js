$("#submit-1").click(function () {
    $("#first-form").attr("style", "display: none;");
    $("#second-form").attr("style", "");
    $("#step-1").removeClass("active");
    $("#step-1").addClass("done");
    $("#step-2").addClass("active")
});