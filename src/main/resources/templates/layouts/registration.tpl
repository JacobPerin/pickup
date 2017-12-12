yieldUnescaped '<!DOCTYPE html>'
html {
  head {

    comment 'http://groovy-lang.org/templating.html :: used to render .html'

    title(pageTitle)

    comment 'twitter bootstrap for styling the page'
    link(rel:'stylesheet', href:'https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css', integrity:'sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb', crossorigin:'anonymous')
  }
  body {

    div(class : 'mx-auto', style : 'width : 400px;'){
      
      h1('Registration Page')

      form( id:'registrationForm', action : '/user/save', method : 'post') {
        div(class : 'form-group') {
          label(for : 'firstName', 'First Name')
          input(type : 'text', class : 'form-control', name : 'firstName', id : 'firstName', placeholder : 'Enter First Name', required: true)
        }

        div(class : 'form-group') {
          label(for : 'lastName', 'Last Name')
          input(type : 'text', class : 'form-control', name : 'lastName', id : 'lastName', placeholder : 'Enter Last Name', required: true)
        }

        div(class : 'form-group') {
          label(for : 'email', 'Email')
          input(type : 'text', class : 'form-control', name : 'email', id : 'email', placeholder : 'Enter Email', required: true)
        }

        div(class : 'form-group') {
          label(for : 'username', 'Username')
          input(type : 'text', class : 'form-control', name : 'username', id : 'username', placeholder : 'Enter Username', required: true)
        }

        div(class : 'form-group') {
          label(for : 'password', 'Password')
          input(type : 'password', class : 'form-control', name : 'password', id : 'password', placeholder : 'Enter Password', required: true)
        }

        button('Submit', type : 'submit', class : 'btn btn-primary')

      }
    }

    mainBody()
    
  }
}