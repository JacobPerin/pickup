yieldUnescaped '<!DOCTYPE html>'
html {
  head {

    comment 'http://groovy-lang.org/templating.html :: used to render .html'

    title(pageTitle)

    comment 'twitter bootstrap for styling the page'
    link(rel: 'stylesheet', href: '/css/bootstrap.min.css')
  }
  body {

    comment 'split page into two halves :: (1) active events list (2) google map + detial view'
    div(class: 'row-fluid') {
      
      comment 'left side of page'  
      div(class: 'col-4 bg-primary') {
        comment 'TODO :: Add a growing list of events'
      }
      
      comment 'right side of page'
      div(class : 'col-8') {
        
        div(class : 'row-fluid') {

          comment 'google map'
          div(class: 'map bg-secondary')

          comment 'make column break to next line'
          div(class : 'w-100')

          comment 'detial view f/ events'
          div(class: 'detail bg-info')
        }
      }
    }

    comment 'Import the .js file to handle events + load google maps'
    script( '', type:'text/javascript', src:'/js/home.js' )

    mainBody()
  }
}