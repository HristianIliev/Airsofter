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
                        .attr("type", "email")
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
                            .attr("name", "radio" + i)
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
                            .attr("name", "radio" + i)
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
                            .attr("name", "radio" + i)
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

$("#submit-2").click(function() {
  var members = $("#members").val();

  for (var i = 1; i < members; i += 1) {
    if ($("#" + i).val().length === 0) {
      $("#" + i)
        .parent()
        .parent()
        .addClass("has-error");

      return false;
    }

    var radioName = "radio" + i;
    if ($("input[name=" + radioName + "]:checked").length === 0) {
      iziToast.error({
        title: "Error",
        message: "Има неизбрано ниво на умения при някой от участниците",
        position: "topRight"
      });

      return false;
    }
  }

  $("#second-form").attr("style", "display: none;");
  $("#third-form").attr("style", "");
  $("#step-2").addClass("done");
  $("#step-2").removeClass("active");
  $("#step-3").addClass("active");
});

$("#back-2").click(function() {
  $("#third-form").attr("style", "display: none;");
  $("#second-form").attr("style", "");
  $("#step-3").removeClass("active");
  $("#step-2").removeClass("done");
  $("#step-2").addClass("active");
});

$("#submit-3").click(function() {
  if ($("#telephone").val().length === 0) {
    $("#telephone")
      .parent()
      .parent()
      .addClass("has-error");

    return false;
  }

  var name = $("#teamName").val();
  var description = $("#teamDescription").val();
  var telephone = $("#telephone").val();
  var members = $("#members").val();

  var dataJSON = {
    name: name,
    description: description,
    preferences: [],
    teamMembers: [],
    telephone: telephone
  };

  for (var i = 0; i < 9; i += 1) {
    var checkboxId = "checkbox" + i;
    var labelId = "label" + i;
    var entryName = $("#" + labelId).text();
    if ($("#" + checkboxId).is(":checked")) {
      dataJSON["preferences"].push({
        name: entryName
      });
    }
  }

  for (var i = 0; i < 9; i += 1) {
    var checkboxId = "checkbox" + i + "_1";
    var labelId = "label" + i + "_1";
    var entryName = $("#" + labelId).text();
    if ($("#" + checkboxId).is(":checked")) {
      dataJSON["preferences"].push({
        name: entryName
      });
    }
  }

  for (var i = 1; i < members; i += 1) {
    var email = $("#" + i).val();
    var radioName = "radio" + i;
    var level = $("input[name=" + radioName + "]:checked")
      .next()
      .text();
    dataJSON["teamMembers"].push({
      email: email,
      level: level
    });
  }

  // sendVerificationSMS(telephone);

  showCodeVerificationModal(dataJSON);
});

function sendVerificationSMS(telephone) {
  $.ajax({
    url: "/api/sendVerificationSMS?telephone=" + telephone,
    method: "GET",
    success: function(result) {}
  });
}

function showCodeVerificationModal(dataJSON) {
  // vex.dialog.open({
  //   message:
  //     "За да завършите успешно регистрацията на отбор трябва първо да потвърдите телефонния си номер. Ние ви изпратихме SMS, въведете кода за достъп:",
  //   input: [
  //     '<div class="input-group"><span class="input-group-addon"><input type="text" name="code" class="form-control" placeholder="Код"></span"</div>'
  //   ].join(""),
  //   callback: function(data) {
  //     if (!data) {
  //       return console.log("Cancelled");
  //     }
  //     console.log(data.code);

  //     $.ajax({
  //       url: "/api/checkVerificationCode?code=" + data.code,
  //       method: "GET",
  //       success: function(result) {
  //         if (result) {
  startLoading();

  $.ajax({
    url: "/api/createTeam",
    method: "POST",
    data: JSON.stringify(dataJSON),
    contentType: "application/json",
    success: function(result) {
      stopLoading();
      if (result.id !== 0 && result !== "null" && result !== null) {
        window.location.href = "/team/" + result.id;
      } else {
        iziToast.error({
          title: "Error",
          message: "Някой от емайлите не е валиден",
          positon: "topRight"
        });
      }
    }
  });
  //         } else {
  //           alert("Вашият код беше грешен :(");
  //         }
  //       }
  //     });
  //   }
  // });
}

function startLoading() {
  $("#submit-3").html(
    '<i class="far fa-check-circle"></i> Приключване <i class="fas fa-circle-notch fa-spin"></i>'
  );
}

function stopLoading() {
  $("#submit-3").html('<i class="far fa-check-circle"></i> Приключване');
}
