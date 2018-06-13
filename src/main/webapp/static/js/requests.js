$(".btn-reject").click(function() {
  var id = $(this)
    .parent()
    .attr("id");
  $.ajax({
    url: "/api/reject/" + id,
    method: "GET",
    succes: function(result) {
      if (result.ok === true) {
        iziToast.success({
          title: "OK",
          message: "Заявката беше успешно отхвърлена!",
          position: "topRight"
        });
      }
    }
  });
});

$(".btn-accept").click(function() {
  var id = $(this)
    .parent()
    .attr("id");
  $.ajax({
    url: "/api/accept/" + id,
    method: "GET",
    succes: function(result) {
      if (result.ok === true) {
        iziToast.success({
          title: "OK",
          message: "Заявката беше успешно потвърдена!",
          position: "topRight"
        });
      }
    }
  });
});
