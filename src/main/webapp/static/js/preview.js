// GOOGLE MAPS SETUP
var map;

function initMap() {
  $.ajax({
    url: "/api/arena",
    method: "GET",
    success: function(result) {
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

      var pos = {
        lat: result.latitude,
        lng: result.longitude
      };

      var arena = new google.maps.Marker({
        position: pos,
        map: map,
        draggable: true
      });

      map.setCenter({ lat: pos.lat, lng: pos.lng });

      //   GETS THE ADDRESS HUMAN READABLE
      var geocoder = new google.maps.Geocoder();
      geocoder.geocode({ location: pos }, function(results, status) {
        if (status === "OK") {
          if (results[0]) {
            $(".arena-address").text(results[0].formatted_address);
          } else {
            window.alert("No results found");
          }
        } else {
          window.alert("Geocoder failed due to: " + status);
        }
      });
    }
  });
}
