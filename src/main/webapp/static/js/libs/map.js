var map;

function initMap() {
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
        }
      }
    );

    return false;
  });
}
