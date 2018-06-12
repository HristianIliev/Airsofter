// GOOGLE MAPS SETUP
var map;
var lastMarker = null;

function initMap() {
  var defaultBoundsForAutocomplete = new google.maps.LatLngBounds(
    new google.maps.LatLng(41.10696293, 21.98533944),
    new google.maps.LatLng(44.41803141, 29.29457424)
  );

  var options = {
    bounds: defaultBoundsForAutocomplete
  };

  var input = document.getElementById("arena-address");
  var autocomplete = new google.maps.places.Autocomplete(input, options);

  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: -34.397, lng: 150.644 },
    zoom: 14
  });
  // map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      initialLocation = new google.maps.LatLng(
        position.coords.latitude,
        position.coords.longitude
      );
      map.setCenter(initialLocation);
      var marker = new google.maps.Marker({
        position: initialLocation,
        icon: "/static/icons/user-map-icon-2.png",
        map: map
      });
    });
  }

  $(".searchService").on("submit", function() {
    var geocoder = new google.maps.Geocoder();
    console.log($(".searchedCity").val());
    geocoder.geocode(
      {
        address: $(".searchedCity").val()
      },
      function(results, status) {
        console.log(status);
        if (status == google.maps.GeocoderStatus.OK) {
          pos = {
            lat: results[0].geometry.location.lat(),
            lng: results[0].geometry.location.lng()
          };
          map.setCenter(pos);
          if (lastMarker !== null) {
            lastMarker.setMap(null);
            lastMarker = null;
          }

          lastMarker = new google.maps.Marker({
            position: pos,
            map: map,
            draggable: true
          });
        }
      }
    );

    return false;
  });
}

$("#submit-1").click(function() {
  if ($("#arenaName").val().length === 0) {
    $("#arenaName")
      .parent()
      .parent()
      .addClass("has-error");
    return false;
  } else if ($("#arenaDescription").val().length < 150) {
    $("#arenaDescription")
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

$("#submit-2").click(function() {
  if (lastMarker === null) {
    $("#arena-address")
      .parent()
      .parent()
      .addClass("has-error");

    return false;
  }

  $("#second-form").attr("style", "display: none;");
  $("#third-form").attr("style", "");
  $("#step-2").addClass("done");
  $("#step-2").removeClass("active");
  $("#step-3").addClass("active");
});

$("#submit-3").click(function() {
  if ($("#telephone").val().length === 0) {
    $("#telephone")
      .parent()
      .parent()
      .addClass("has-error");

    return false;
  }

  var name = $("#arenaName").val();
  var description = $("#arenaDescription").val();
  var latitude = lastMarker.position.lat();
  var longitude = lastMarker.position.lng();
  var telephone = $("#telephone").val();

  var mondayOff = $("#checkbox-0").prop("checked");
  var tuesdayOff = $("#checkbox-1").prop("checked");
  var wednesdayOff = $("#checkbox-2").prop("checked");
  var thursdayOff = $("#checkbox-3").prop("checked");
  var fridayOff = $("#checkbox-4").prop("checked");
  var saturdayOff = $("#checkbox-5").prop("checked");
  var sundayOff = $("#checkbox-6").prop("checked");

  var mondayStart = $("#trigger-time-0").val();
  var mondayEnd = $("#trigger-time-1").val();
  var tuesdayStart = $("#trigger-time-2").val();
  var tuesdayEnd = $("#trigger-time-3").val();
  var wednesdayStart = $("#trigger-time-4").val();
  var wednesdayEnd = $("#trigger-time-5").val();
  var thursdayStart = $("#trigger-time-6").val();
  var thursdayEnd = $("#trigger-time-7").val();
  var fridayStart = $("#trigger-time-8").val();
  var fridayEnd = $("#trigger-time-9").val();
  var saturdayStart = $("#trigger-time-10").val();
  var saturdayEnd = $("#trigger-time-11").val();
  var sundayStart = $("#trigger-time-12").val();
  var sundayEnd = $("#trigger-time-13").val();

  var dataJSON = {
    name: name,
    description: description,
    latitude: latitude,
    longitude: longitude,
    arenaCategories: [],
    telephone: telephone,
    timetable: {
      mondayOff: mondayOff,
      tuesdayOff: tuesdayOff,
      wednesdayOff: wednesdayOff,
      thursdayOff: thursdayOff,
      fridayOff: fridayOff,
      saturdayOff: saturdayOff,
      sundayOff: sundayOff,
      mondayStart: mondayStart,
      mondayEnd: mondayEnd,
      tuesdayStart: tuesdayStart,
      tuesdayEnd: tuesdayEnd,
      wednesdayStart: wednesdayStart,
      wednesdayEnd: wednesdayEnd,
      thursdayStart: thursdayStart,
      thursdayEnd: thursdayEnd,
      fridayStart: fridayStart,
      fridayEnd: fridayEnd,
      saturdayStart: saturdayStart,
      saturdayEnd: saturdayEnd,
      sundayStart: sundayStart,
      sundayEnd: sundayEnd
    }
  };

  for (var i = 0; i < 9; i += 1) {
    var checkboxId = "checkbox" + i;
    var labelId = "label" + i;
    var entryName = $("#" + labelId).text();
    if ($("#" + checkboxId).is(":checked")) {
      dataJSON["arenaCategories"].push({
        name: entryName
      });
    }
  }

  for (var i = 0; i < 9; i += 1) {
    var checkboxId = "checkbox" + i + "_1";
    var labelId = "label" + i + "_1";
    var entryName = $("#" + labelId).text();
    if ($("#" + checkboxId).is(":checked")) {
      dataJSON["arenaCategories"].push({
        name: entryName
      });
    }
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
  vex.dialog.open({
    message:
      "За да завършите успешно регистрацията си трябва първо да потвърдите телефонния си номер. Ние ви изпратихме SMS, въведете кода за достъп:",
    input: [
      '<div class="input-group"><span class="input-group-addon"><input type="text" name="code" class="form-control" placeholder="Код"></span"</div>'
    ].join(""),
    callback: function(data) {
      if (!data) {
        return console.log("Cancelled");
      }
      console.log(data.code);

      // $.ajax({
      //   url: "/api/checkVerificationCode?code=" + data.code,
      //   method: "GET",
      //   success: function(result) {
      //     if (result) {
            startLoading();

            $.ajax({
              url: "/api/createArena",
              method: "post",
              data: JSON.stringify(dataJSON),
              contentType: "application/json",
              success: function(result) {
                stopLoading();
                if (result.id !== 0 && result !== "null" && result !== null) {
                  window.location.href = "/dashboard";
                }
              }
            });
      //     } else {
      //       alert("Вашият код беше грешен :(");
      //     }
      //   }
      // });
    }
  });
}

function startLoading() {
  $("#submit-3").html(
    '<i class="far fa-check-circle"></i> Приключване <i class="fas fa-circle-notch fa-spin"></i>'
  );
}

function stopLoading() {
  $("#submit-3").html('<i class="far fa-check-circle"></i> Приключване');
}

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
