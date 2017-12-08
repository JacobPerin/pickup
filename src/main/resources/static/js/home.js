$( document ).ready(function() {

	var geocoder;
	var map;


	geocoder = new google.maps.Geocoder();
	var mapOptions = {
		zoom: 8,
	}
	map = new google.maps.Map(document.getElementById('map'), mapOptions);


	var address = 'Ames';
	geocoder.geocode( { 'address': address}, function(results, status) {
		if (status == 'OK') {
			map.setCenter(results[0].geometry.location);
			var marker = new google.maps.Marker({
				map: map,
				position: results[0].geometry.location
			});
		} else {
			alert('Geocode was not successful for the following reason: ' + status);
		}
	});
});