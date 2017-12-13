/* Global Variables */
let markers = [];


let map;


function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
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

    // remove the marker from the map -- if it exists 
    if(pendingMarker != null) pendingMarker.setMap(null);

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

    let $form = $('<form />', {id : 'createEvent'});

    $('#detail').append($('<div />', {class : 'mx-auto', style : 'width : 400px'}).append($form));

    $form.append($('<div />', { class : 'form-group'}))
    .append($('<label />', { for : 'input_title', text : 'Event Name'}))
    .append($('<input />', {type : 'text', class : 'form-control', name : 'title', placeholder : 'Enter Event Name'}));

    $form.append($('<div />', { class : 'form-group'}))
    .append($('<label />', { for : 'input_maxUsers', text : 'Number of People'}))
    .append($('<input />', {type : 'text', class : 'form-control', name : 'maxUsers', placeholder : 'Enter Number of People'}));

    $form.append($('<div />', { class : 'form-group'}))
    .append($('<label />', { for : 'input_description', text : 'Enter description'}))
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
    let userId = $("meta[name='_userId']").attr("content");
    values['username'] = userId;
    values['attendingUsers'] = [];

    // Store data in the backend model
    let id = formToBackend(values);

    // Retrieve id from backend, and generate a list item w/ event
    appendToList(values.title, id, values.maxUsers, marker);

}

// TODO :: STORE DATA TO THE BACKEND
function formToBackend(values){
  let id;
  $.ajax({
    type:"POST",
    contentType: "application/json",
    url:'/user/addEvent',
    data: JSON.stringify(values),
    async: false,
    success: function(data){
      id = data;
    },
    error: function(error){
      var err = error;
    }
  });

  return id;
}


// TODO :: GET ID FOR CLASS SPEC. ON APPEND TO LIST
function retrieveId(id, event){
    $.ajax({
      type: 'POST',
      contentType: "application/json",
      url: '/user/getEvent',
      data: {id : id, title : event},
      success: function(data){
        return data;
      },
      error(err){
        var error = err;
      }
    });
    return ++id;
}

/*
title -> string -> event name 
id -> number -> id of given event 
persons -> number of people in event -> number of persons
*/
function appendToList(event, id, persons, marker){
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

    // List item recently joined by the user
    $item.click(function(){
      // Get the position from the marker paired with this event
      let latLngEvent = marker.getPosition();

      map.setCenter(latLngEvent);

      // If event window is active -- clear it 
      if(pendingForm) pendingClear();
      pendingForm = true;

      // Open form w/ id of created event 
      openForm(id);
    });
}


/* 
Created Event clicked in banner or on marker in the map
*/
function openForm(id){

  let eventValues = getEventDataBulk(id);

  let $event = $('<div />', {class : 'row'});

  // LHS of div will have a growing list of all active users in the current event 
  let $lhs = $('<div />', {class : 'col-3 fill'});
  
  let $attendees = $('<ul />', {class : 'list-group'});
  
  $.each(eventValues.attendingUsers, function(i, attendee){

    $attendees.append($('<li />', {class : 'list-group-item text-center', text : attendee}));
  
  });

  $lhs.append($attendees);
  $event.append($lhs);

  // RHS of div will have option to join the given event 
  let $rhs = $('<div />', {class : 'col-9 mx-auto fill'});

  $rhs.append($('<h2 />', {class : 'text-center', text : eventValues.maxUsers}));
  $rhs.append($('<h3 />', {class : 'text-center', text : eventValues.title}));
  $rhs.append($('<p />', {class : 'text-center', text : eventValues.description}));

  if(eventValues.maxUsers > eventValues.attendingUsers.length){
    let $form = joinForm(id);
    $rhs.append($form);
  }
  else {
    // Event full, cannot join
  }

  $event.append($rhs);

  // Append entire view to the detail div
  $('#detail').append($event);
}


function joinForm(id){
  let $form = $('<form />', {id : 'joinEvent'});
  $form.append($('<input />', {text : 'Join', type : 'submit', class : 'btn btn-primary btn-block'}));

  $form.submit( function(event){

    /*
    Prevent form submissin :: 
    warning 
        -- If completed it messes up page on refresh
    */
    event.preventDefault();

    values = {};
    values['userId'] = $("meta[name='_userId']").attr("content");
    values['eventId'] = id;

    updateEvent(values);

    // Form has accomplished purpose --> remove
    pendingSetClear();
  });

  return $form;
}


function getEventDataBulk(id){
  //TODO GET EVENT W/ EVENT ID 
  let values;
  $.ajax({
    type:"POST",
    contentType: "application/json",
    url:'/event/getEvent',
    data: id,
    async: false,
    beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(data){
      values = data;
    },
    error: function(error){
      var err = error;
    }
  });
  //FIXME :: DUMMY DATA 
return values;
}

function updateEvent(values){
  //TODO :: POST TO FORM TO UPDATE USERS IN EVENT 
}