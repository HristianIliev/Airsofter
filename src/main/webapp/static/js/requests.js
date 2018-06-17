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

$(".markAsDone").click(function() {
  var indexOfUndescore = $(this)
    .attr("id")
    .indexOf("_");
  var requestIdToSend = $(this)
    .attr("id")
    .substring(indexOfUndescore + 1);

  $.ajax({
    url: "/api/markAsDone/" + requestIdToSend,
    method: "GET",
    success: function(result) {
      if (result.ok) {
        iziToast.success({
          title: "OK",
          message: "Заявката беше успешно маркирана като завършена!",
          position: "topRight"
        });
      }
    }
  });
});
