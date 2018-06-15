$('a[data-toggle="tab"]').on("show.bs.tab", function(e) {
  $(".nav-link.active").removeClass("active");
  $(".nav-item.active").removeClass("active");

  $(e.target).addClass("active");
  $(e.target)
    .parent()
    .addClass("active"); // newly activated tab
});

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

  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      initialLocation = new google.maps.LatLng(
        position.coords.latitude,
        position.coords.longitude
      );

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

  $.ajax({
    url: "/api/arena",
    method: "GET",
    success: function(result) {
      arenaLocation = new google.maps.LatLng(result.latitude, result.longitude);
      map.setCenter(arenaLocation);
      lastMarker = new google.maps.Marker({
        position: arenaLocation,
        map: map
      });
    }
  });
}

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
