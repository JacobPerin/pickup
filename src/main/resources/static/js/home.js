function initMap() {
	let map = new google.maps.Map(document.getElementById('map'), {
		zoom: 8,
	});

	let address = 'Ames';
	let geocoder = new google.maps.Geocoder();
	geocoder.geocode( { 'address': address}, function(results, status) {
		if (status == 'OK') {
			map.setCenter(results[0].geometry.location);
		} else {
			alert('Geocode was not successful for the following reason: ' + status);
		}
	});

  	map.addListener('click', function(e) {
        addEvent(e.latLng, map);
  	});
}



function addEvent(latLng, map){
	
	let marker = new google.maps.Marker({
        map: map,
        position: e.latLng
    });

}