yieldUnescaped '<!DOCTYPE html>'
html {
  head {

    comment 'http://groovy-lang.org/templating.html :: used to render .html'

    title(pageTitle)

    comment 'twitter bootstrap for styling the page'
    link(rel:'stylesheet', href:'https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css', integrity:'sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb', crossorigin:'anonymous')
  }
  body {

   	div(class : 'mx-auto'){

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

      form(id:'RegisterPage', action: '/user/register',style: 'width:50%; margin: 0 auto; height: 50%;'){
      	button('Register', type:'submit', class:'btn btn-primary')
      }
  	}

  	mainBody()

  }
}