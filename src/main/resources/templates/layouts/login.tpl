yieldUnescaped '<!DOCTYPE html>'
html {
  head {

    comment 'http://groovy-lang.org/templating.html :: used to render .html'

    title(pageTitle)

    comment 'twitter bootstrap for styling the page'
    link(rel: 'stylesheet', href: '/css/bootstrap.min.css')
  }
  body {

    div(class : 'mx-auto', style : 'width : 400px;'){

      h1('Registration Page')

    
      comment 'modify action'
      form( action : '/', ,method : 'post') {
        div(class : 'form-group') {
          label('Username', for : 'username')
          input(type : 'text', class : 'form-control', name : 'username', id : 'username', placeholder : 'Username')
        }

        div(class : 'form-group') {
          label(for : 'password', 'Password')
          input(type : 'password', class : 'form-control', name : 'password', id : 'password', placeholder : 'Password')
        }

        button('Submit', type : 'submit', class : 'btn btn-primary')
      }
    }

    mainBody()
  }
}