layout 'layouts/login.tpl',
        pageTitle: 'Login',
        mainBody: contents {
               comment 'modify action'
		      form( id:'loginForm', action : '/user', ,method : 'post') {
		      	input(name: 'id'type 'hidden')
		        div(class : 'form-group') {
		          label('Username', for : 'username')
		          input(type : 'text', class : 'form-control', name : 'username', id : 'username', placeholder : 'Username')
		        }

		        div(class : 'form-group') {
		          label(for : 'password', 'Password')
		          input(type : 'password', class : 'form-control',name : 'password', id : 'password', placeholder : 'Password')
		        }

		        button('Submit', type : 'submit', class : 'btn btn-primary')
		      }
        }