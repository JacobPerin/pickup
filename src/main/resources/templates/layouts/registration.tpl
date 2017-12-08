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

      mainBody()
    }


  }
}