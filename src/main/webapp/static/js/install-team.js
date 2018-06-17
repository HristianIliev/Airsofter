$("#submit-1").click(function() {
  if ($("#teamName").val().length === 0) {
    $("#teamName")
      .parent()
      .parent()
      .addClass("has-error");
    return false;
  } else if ($("#teamDescription").val().length < 150) {
    $("#teamDescription")
      .parent()
      .addClass("has-error");

    return false;
  }

  $("#first-form").attr("style", "display: none;");
  $("#second-form").attr("style", "");
  $("#step-1").removeClass("active");
  $("#step-1").addClass("done");
  $("#step-2").addClass("active");
});

$("#back-1").click(function() {
  $("#second-form").attr("style", "display: none;");
  $("#first-form").attr("style", "");
  $("#step-2").removeClass("active");
  $("#step-1").addClass("active");
  $("#step-1").removeClass("done");
});

$("#generate-forms").click(function() {
  var members = $("#members").val();

  for (var i = 1; i < members; i += 1) {
    var number = i + 1;
    $(".addTo").append(
      $("<div/>")
        .addClass("row")
        .append(
          $("<div/>")
            .addClass("col-md-5")
            .append(
              $("<div/>")
                .addClass("form-group")
                .addClass("form-md-line-input")
                .addClass("form-md-floating-label")
                .append(
                  $("<div/>")
                    .addClass("input-icon")
                    .append(
                      $("<input/>")
                        .attr("id", i)
                        .attr("type", "text")
                        .addClass("form-control")
                    )
                    .append(
                      $("<label/>")
                        .attr("for", i)
                        .text("Емайл на участник номер " + number)
                    )
                    .append(
                      $("<span/>")
                        .addClass("help-block")
                        .text("Въведи емайл на участника в тази платформа")
                    )
                    .append(
                      $("<i/>")
                        .addClass("fas")
                        .addClass("fa-at")
                    )
                )
            )
        )
        .append(
          $("<div/>")
            .addClass("col-md-7")
            .append(
              $("<div/>")
                .addClass("form-group")
                .addClass("form-md-radios")
                .append($("<label/>").text("Ниво на умения"))
                .append(
                  $("<div/>")
                    .addClass("md-radio-inline")
                    .append(
                      $("<div/>")
                        .addClass("md-radio")
                        .append(
                          $("<input/>")
                            .attr("type", "radio")
                            .attr("id", "radio_" + i)
                            .attr("name", "radio_" + i)
                            .addClass("md-radiobtn")
                        )
                        .append(
                          $("<label/>")
                            .attr("for", "radio_" + i)
                            .html(
                              '<span class="inc"></span><span class="check"></span><span class="box"></span> Опитен '
                            )
                        )
                    )
                    .append(
                      $("<div/>")
                        .addClass("md-radio")
                        .addClass("has-error")
                        .append(
                          $("<input/>")
                            .attr("type", "radio")
                            .attr("id", "radio-2_" + i)
                            .attr("name", "radio-2_" + i)
                            .addClass("md-radiobtn")
                        )
                        .append(
                          $("<label/>")
                            .attr("for", "radio-2_" + i)
                            .html(
                              '<span class="inc"></span><span class="check"></span><span class="box"></span> Среден '
                            )
                        )
                    )
                    .append(
                      $("<div/>")
                        .addClass("md-radio")
                        .addClass("has-warning")
                        .append(
                          $("<input/>")
                            .attr("type", "radio")
                            .attr("id", "radio-3_" + i)
                            .attr("name", "radio-3_" + i)
                            .addClass("md-radiobtn")
                        )
                        .append(
                          $("<label/>")
                            .attr("for", "radio-3_" + i)
                            .html(
                              '<span class="inc"></span><span class="check"></span><span class="box"></span> Нов '
                            )
                        )
                    )
                )
            )
        )
    );
  }
});
