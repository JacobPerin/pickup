layout 'layouts/registration.tpl',
        pageTitle: 'Registration',
        mainBody: contents {
                     comment 'modify action'
				      form( action : '/user', method : 'post') {
				        div(class : 'form-group') {
				          label(for : 'firstName', 'First Name')
				          input(type : 'text', class : 'form-control', name : 'firstName', id : 'firstName', placeholder : 'Enter First Name')
				        }

				        div(class : 'form-group') {
				          label(for : 'lastName', 'Last Name')
				          input(type : 'text', class : 'form-control', name : 'lastName', id : 'lastName', placeholder : 'Enter Last Name')
				        }

				        div(class : 'form-group') {
				          label(for : 'email', 'Email')
				          input(type : 'text', class : 'form-control', name : 'email', id : 'email', placeholder : 'Enter Email')
				        }

				        div(class : 'form-group') {
				          label(for : 'username', 'Username')
				          input(type : 'text', class : 'form-control', name : 'username', id : 'username', placeholder : 'Enter Username')
				        }

				        div(class : 'form-group') {
				          label(for : 'password', 'Password')
				          input(type : 'password', class : 'form-control', name : 'password', id : 'password', placeholder : 'Enter Password')
				        }

				        button('Submit', type : 'submit', class : 'btn btn-primary')
				      }
        }