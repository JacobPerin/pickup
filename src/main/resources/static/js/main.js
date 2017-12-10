let markers = [];


function initMap() {
	let map = new google.maps.Map(document.getElementById('map'), {
	   zoom: 15,
       styles: [
        {elementType: 'geometry', stylers: [{color: '#242f3e'}]},
        {elementType: 'labels.text.stroke', stylers: [{color: '#242f3e'}]},
        {elementType: 'labels.text.fill', stylers: [{color: '#746855'}]},
        {
          featureType: 'administrative.locality',
          elementType: 'labels.text.fill',
          stylers: [{color: '#d59563'}]
        },
        {
          featureType: 'poi',
          elementType: 'labels.text.fill',
          stylers: [{color: '#d59563'}]
        },
        {
          featureType: 'poi.park',
          elementType: 'geometry',
          stylers: [{color: '#263c3f'}]
        },
        {
          featureType: 'poi.park',
          elementType: 'labels.text.fill',
          stylers: [{color: '#6b9a76'}]
        },
        {
          featureType: 'road',
          elementType: 'geometry',
          stylers: [{color: '#38414e'}]
        },
        {
          featureType: 'road',
          elementType: 'geometry.stroke',
          stylers: [{color: '#212a37'}]
        },
        {
          featureType: 'road',
          elementType: 'labels.text.fill',
          stylers: [{color: '#9ca5b3'}]
        },
        {
          featureType: 'road.highway',
          elementType: 'geometry',
          stylers: [{color: '#746855'}]
        },
        {
          featureType: 'road.highway',
          elementType: 'geometry.stroke',
          stylers: [{color: '#1f2835'}]
        },
        {
          featureType: 'road.highway',
          elementType: 'labels.text.fill',
          stylers: [{color: '#f3d19c'}]
        },
        {
          featureType: 'transit',
          elementType: 'geometry',
          stylers: [{color: '#2f3948'}]
        },
        {
          featureType: 'transit.station',
          elementType: 'labels.text.fill',
          stylers: [{color: '#d59563'}]
        },
        {
          featureType: 'water',
          elementType: 'geometry',
          stylers: [{color: '#17263c'}]
        },
        {
          featureType: 'water',
          elementType: 'labels.text.fill',
          stylers: [{color: '#515c6d'}]
        },
        {
          featureType: 'water',
          elementType: 'labels.text.stroke',
          stylers: [{color: '#17263c'}]
        }]
	});


    // TODO :: Allow user to set to current location (starting) or enter a city
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

// Global variables f/ determining state of form operations
let pendingForm = false;
let pendingMarker;

// Event creation :: trigered by on map click
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


// Helper method f/ incomplete form request
function pendingClear(){

    dropDetailView();

    // remove the marker from the map
    pendingMarker.setMap(null);

    pendingForm = false;
    pendingMarker = null;
}

// Helper method f/ form successful completion
function pendingSetClear(){

    dropDetailView();

    // Store created marker to marker events list
    markers.push(pendingMarker);

    pendingForm = false;
    pendingMarker = null;
}

function dropDetailView(){
    $('#detail').empty();
}

function createForm(marker){

    let $form = $('<form />', {id : 'createEvent', action: 'event/add', method: 'post'});

    $('#detail').append($('<div />', {class : 'mx-auto', style : 'width : 400px'}).append($form));

    $form.append($('<div />', { class : 'form-group'}))
    .append($('<label />', { for : 'event', text : 'Event Name'}))
    .append($('<input />', {type : 'text', class : 'form-control', name : 'event', id : 'event', placeholder : 'Enter Event Name'}));

    $form.append($('<div />', { class : 'form-group'}))
    .append($('<label />', { for : 'people', text : 'Number of People'}))
    .append($('<input />', {type : 'text', class : 'form-control', name : 'people', id : 'people', placeholder : 'Enter Number of People'}));

    $form.append($('<div />', { class : 'form-group'}))
    .append($('<label />', { for : 'description', text : 'Enter description'}))
    .append($('<textarea />', {class : 'form-control', name : 'description', row : '1'}));

    $form.append($('<br />'));
    
    $form.append($('<input />', {text : 'Submit', type : 'submit', class : 'btn btn-primary btn-block'}));

    $form.submit( function(event){

        /*
        Prevent form submissin :: 
        warning 
            -- If completed it messes up page on refresh
        */
        event.preventDefault();

        // Perform necessary operation(s) w/ form data
        formOpterations(marker);

        // Form has accomplished purpose --> remove
        pendingSetClear();
    });
}

function formOpterations(marker){
    let $inputs = $('#createEvent :input');

    let values = {};

    $inputs.each(function() {
        values[this.name] = $(this).val();
    });

    let latLng = marker.getPosition();

    values['lat'] = latLng.lat();
    values['lng'] = latLng.lng();

    // Store data in the backend model
    formToBackend(values);

    /*
    TODO :: 
        --Retrieve username from hidden span / meta tag
        --Make call to backend to get id for event
        --Pass to class to initialize event w/ event id
    */
    let id = retrieveId('', values.event);

    // Retrieve id from backend, and generate a list item w/ event
    appendToList(values.event, id, values.people);

}

// TODO :: STORE DATA TO THE BACKEND
function formToBackend(values){
    $.post('/user/addEvent', values);
}


// DUMMY VALUE, REPLACE WITH ACTUAL DATA
let id = 0;

// TODO :: GET ID FOR CLASS SPEC. ON APPEND TO LIST
function retrieveId(username, event){
    //$.get('/event', {username : username, event : event});
    return ++id;
}

/*
title -> string -> event name 
id -> number -> 
persons -> number of people in event -> number of persons
*/
function appendToList(event, id, persons){
    //$('#active-events')
    let $item = $('<li />', {id : id, text : event, class : 'list-group-item d-flex justify-content-between align-items-center'});
    
    $item .append($('<span />', {class : 'badge badge-primary badge-pill', text : persons}));

    $('#active-events').append($item);

    $item.hover(
        function() {
            $(this).addClass('active');
        }, function() {
            $(this).removeClass('active');
        }
    );
}