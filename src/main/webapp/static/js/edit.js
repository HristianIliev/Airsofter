$('a[data-toggle="tab"]').on("show.bs.tab", function(e) {
  $(".nav-link.active").removeClass("active");
  $(".nav-item.active").removeClass("active");

  $(e.target).addClass("active");
  $(e.target)
    .parent()
    .addClass("active"); // newly activated tab
});
