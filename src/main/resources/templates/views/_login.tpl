layout 'layouts/login.tpl',
        pageTitle: 'Login',
        mainBody: contents {

       	div(class : 'mx-auto'){
               comment 'modify action'
		    form( id:'loginForm', method: 'post', action : '/user/login', style: 'width:50%; margin: 0 auto; height: 50%;') {
		      	// input(name: 'id'type 'hidden')
		        div(class : 'form-group') {
		          label('Username', for : 'username')
		          input(type : 'text', class : 'form-control', name : 'username', id : 'username', placeholder : 'Username', required:true)
		        }

		        div(class : 'form-group') {
		          label(for : 'password', 'Password')
		          input(type : 'password', class : 'form-control',name : 'password', id : 'password', placeholder : 'Password', required:true)
		        }

		        button('Submit', type : 'submit', class : 'btn btn-primary')
		        
		      }
		      form(id:'RegisterPage', action: '/user/register'){
		      	button('Register', type:'submit', class:'btn btn-primary')
		      }
		  }
        }