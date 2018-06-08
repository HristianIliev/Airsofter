$("#submit-1").click(function() {
  if ($("#arenaName").val().length === 0) {
    $("#arenaName")
      .parent()
      .parent()
      .addClass("has-error");
  } else if ($("#arenaDescription").val().length < 150) {
    $("#arenaDescription")
      .parent()
      .addClass("has-error");
  } else {
    $("#first-form").attr("style", "display: none;");
    $("#second-form").attr("style", "");
    $("#step-1").removeClass("active");
    $("#step-1").addClass("done");
    $("#step-2").addClass("active");
  }
});

$("#submit-2").click(function() {
  $("#second-form").attr("style", "display: none;");
  $("#third-form").attr("style", "");
  $("#step-2").addClass("done");
  $("#step-2").removeClass("active");
  $("#step-3").addClass("active");
});

$("#back-1").click(function() {
  $("#second-form").attr("style", "display: none;");
  $("#first-form").attr("style", "");
  $("#step-2").removeClass("active");
  $("#step-1").addClass("active");
  $("#step-1").removeClass("done");
});

$("#back-2").click(function() {
  $("#third-form").attr("style", "display: none;");
  $("#second-form").attr("style", "");
  $("#step-3").removeClass("active");
  $("#step-2").removeClass("done");
  $("#step-2").addClass("active");
});

function setUpWorkTimeEvents() {
  // Setting up the work time
  for (var i = 0; i < 14; i += 1) {
    let dialog = new mdDateTimePicker.default({
      type: "time",
      mode: true,
      cancel: "Отказ"
    });

    let time = document.getElementById("trigger-time-" + i);
    dialog.trigger = time;
    time.addEventListener("click", function() {
      dialog.toggle();
    });
    time.addEventListener("onOk", function() {
      let start = dialog.time.toString().indexOf(":") - 2;
      this.value = dialog.time.toString().substring(start, start + 5);
    });
  }

  // Setting up off days
  for (var i = 0; i < 7; i += 1) {
    let checkbox = $("#checkbox-" + i);
    checkbox.on("change", function() {
      if ($(this).is(":checked")) {
        hideInputs($(this));

        showOffDayLabel($(this));
      } else if (!$(this).is(":checked")) {
        hideOffDayLabel(checkbox);

        showInputs($(this));
      }
    });
  }
}

setUpWorkTimeEvents();

function hideInputs(checkbox) {
  checkbox
    .parent()
    .parent()
    .prev()
    .attr("style", "display: none;");
  checkbox
    .parent()
    .parent()
    .prev()
    .prev()
    .attr("style", "display: none;");
}

function showOffDayLabel(checkbox) {
  checkbox
    .parent()
    .parent()
    .before(
      $("<td/>")
        .attr("colspan", 2)
        .addClass("text-center")
        .addClass("font-green")
        .text("Почивен ден")
    );
}

function showInputs(checkbox) {
  checkbox
    .parent()
    .parent()
    .prev()
    .attr("style", "");
  checkbox
    .parent()
    .parent()
    .prev()
    .prev()
    .attr("style", "");
}

function hideOffDayLabel(checkbox) {
  checkbox
    .parent()
    .parent()
    .prev()
    .remove();
}
