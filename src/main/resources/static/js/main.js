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

	// Event listener every time the user wants to create an event 
  	map.addListener('click', function(e) {
        addEvent(e.latLng, map);
  	});
}

let pendingForm = false;
let pendingMarker;

function addEvent(latLng, map){
	
	let marker = new google.maps.Marker({
        map: map,
        position: latLng
    });

    // If user is still creating an event clear it
    if(pendingForm) pendingClear();
    
    pendingForm = true;
    pendingMarker = marker;
    
    // Create a form in the window for user to type in event detials
	createForm(marker);
}

function pendingClear(){
    pendingForm = false;

    // remove the marker from the map
    pendingMarker.setMap(null);

    // remove the form from the div
    $('#detail').empty();
}


function createForm(marker){

    let $form = $('<form />', {id : 'createEvent'});

    $('#detail').append($('<div />', {class : 'mx-auto', style : 'width : 400px'}).append($form));

    $form.append($('<div />', { class : 'form-group'}))
    .append($('<label />', { for : 'event', text : 'Event Name'}))
    .append($('<input />', {type : 'text', class : 'form-control', name : 'event', id : 'event', placeholder : 'Enter Event Name'}));

    $form.append($('<div />', { class : 'form-group'}))
    .append($('<label />', { for : 'people', text : 'Number of People'}))
    .append($('<input />', {type : 'text', class : 'form-control', name : 'people', id : 'people', placeholder : 'Enter Number of People'}));

    $form.append($('<div />', { class : 'form-group'}))
    .append($('<label />', { for : 'description', text : 'Enter description'}))
    .append($('<textarea />', {class : 'form-control', name : 'description', row : '3'}));
    
    $form.append($('<input />', {text : 'Submit', type : 'submit', class : 'btn btn-primary'}));

    $form.submit( function(event){

        event.preventDefault();

        var $inputs = $('#createEvent :input');

        var values = {};

        $inputs.each(function() {
            values[this.name] = $(this).val();
        });

        let latLng = marker.getPosition();

        values['lat'] = latLng.lat();
        values['lng'] = latLng.lng();

        //TODO :: BRING TO FRONTEND
        //$.post('/event', values);

        // Form has accomplished purpose --> remove
        pendingClear();
    });
}