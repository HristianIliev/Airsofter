$(".tile").click(function() {
  $(".tile.active").removeClass("active");
  $(this).addClass("active");
});

$("#submit-1").click(function() {
  var dataAccountType = $(".tile.active").attr("data-account-type");
  if (dataAccountType === 0 || dataAccountType === "0") {
    window.location.href = "/app";
    return false;
  } else if (dataAccountType === 1 || dataAccountType === "1") {
    window.location.href = "/install-team";
    return false;
  }
});
