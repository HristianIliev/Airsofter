var map;
var lastMarker = null;
var allMarkers = [];

function initMap() {
  $.ajax({
    url: "/api/arenas",
    method: "GET",
    success: function(result) {
      var defaultBoundsForAutocomplete = new google.maps.LatLngBounds(
        new google.maps.LatLng(41.10696293, 21.98533944),
        new google.maps.LatLng(44.41803141, 29.29457424)
      );

      var options = {
        bounds: defaultBoundsForAutocomplete
      };

      var input = document.getElementById("find-arena-input");
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
            animation: google.maps.Animation.DROP,
            icon: "/static/icons/user-map-icon-2.png",
            map: map
          });
        });
      }

      for (var i = 0; i < result.length; i += 1) {
        arenaLocation = new google.maps.LatLng(
          result[i].latitude,
          result[i].longitude
        );

        let arenaMarker = new google.maps.Marker({
          position: arenaLocation,
          map: map,
          animation: google.maps.Animation.DROP,
          arenaCategories: result[i].arenaCategories
        });

        let content =
          '<div id="content">' +
          '<div id="siteNotice">' +
          "</div>" +
          '<h1 id="firstHeading" class="firstHeading">' +
          result[i].name +
          "</h1>" +
          '<div id="bodyContent">' +
          "<p>" +
          result[i].description +
          "</p>" +
          "<a href=/arena/" +
          result[i].id +
          ">" +
          "Виж страницата на арената" +
          "</a>" +
          "</div>" +
          "</div>";

        let infowindow = new google.maps.InfoWindow({
          content: content
        });

        arenaMarker.addListener("click", function() {
          infowindow.open(map, arenaMarker);
        });

        allMarkers.push(arenaMarker);
      }

      console.log(allMarkers);

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
            }
          }
        );

        return false;
      });
    }
  });
}

$("#form_control_1").on("change", function(event) {
  showSuccessfulFiltering();

  if (this.value === "Всички категории") {
    showAllMarkers();
    return false;
  }

  for (var i = 0; i < allMarkers.length; i += 1) {
    let hasBeenSeen = false;
    for (var j = 0; j < allMarkers[i].arenaCategories.length; j += 1) {
      if (allMarkers[i].arenaCategories[j].name === this.value) {
        hasBeenSeen = true;
      }
    }

    if (!hasBeenSeen) {
      clearMarker(allMarkers[i]);
    } else if (hasBeenSeen) {
      showMarker(allMarkers[i]);
    }
  }
});

// Sets the map on all markers in the array.
function setMapOnMarker(marker, map) {
  marker.setMap(map);
}

// Removes the markers from the map, but keeps them in the array.
function clearMarker(marker) {
  setMapOnMarker(marker, null);
}

// Shows a marker on the map
function showMarker(marker) {
  setMapOnMarker(marker, map);
}

// Sets the map on all markers in the array.
function setMapOnAll(map) {
  for (var i = 0; i < allMarkers.length; i += 1) {
    setMapOnMarker(allMarkers[i], map);
  }
}

// Shows all markers on the map
function showAllMarkers() {
  setMapOnAll(map);
}

function showSuccessfulFiltering() {
  iziToast.success({
    title: "OK!",
    message: "Успешно филтрирахте Airsoft арените",
    position: "topRight"
  });
}
