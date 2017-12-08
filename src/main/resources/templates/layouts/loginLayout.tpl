yieldUnescaped '<!DOCTYPE html>'
html {
  head {

    comment 'http://groovy-lang.org/templating.html :: used to render .html'

    title(pageTitle)

    comment 'twitter bootstrap for styling the page'
    link(rel: 'stylesheet', href: '/css/bootstrap.min.css')

    comment 'custom .css for setting row height values'
    link(rel: 'stylesheet', href: '/css/home.css')

  }
  body {

    comment 'split page into two forms: login and registration'
    div(class: 'row height-full') {
      
      comment 'registration top part'  
      div(class: 'col-3 bg-primary height-full') {
        label(for: 'text', 'Not a User?')
        input(name: 'Register', type:'submit',value:'Register')
      }
      
      comment 'right side of page'
      div(class : 'col-9 height-full') {
        
        div(class : 'row') {

          comment 'google map'
          div(id : 'map', class: 'col bg-secondary height-sixty-five'){}

          comment 'make column break to next line'
          div(class : 'w-100'){}

          comment 'detial view f/ events'
          div(class: 'col detail bg-info height-thirty-five'){}
        }
      }
    }
    mainBody()
  }
}