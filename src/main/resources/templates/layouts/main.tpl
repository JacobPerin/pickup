yieldUnescaped '<!DOCTYPE html>'
html {
  head {

    comment 'http://groovy-lang.org/templating.html :: used to render .html'

    title(pageTitle)

    comment 'twitter bootstrap for styling the page'
    link(rel: 'stylesheet', href: '/css/bootstrap.min.css')

    comment 'custom .css for setting row height values'
    link(rel: 'stylesheet', href: '/css/main.css')

    comment 'jquery for .js'
    script('', type:'text/javascript' , src : 'https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js')

  }
  body {
    div(class: 'container-fluid'){
      comment 'split page into two halves :: (1) active events list (2) google map + detial view'
      div(class: 'row height-full') {
        
        comment 'left side of page'  
        div(class: 'col-3 bg-primary height-full scroll-y') {
          comment 'TODO :: Add a growing list of events'
        }
        
        comment 'right side of page'
        div(class : 'col-9 height-full') {
          
          div(class : 'row') {

            comment 'google map'
            div('', id : 'map', class: 'col height-sixty-five')

            comment 'make column break to next line'
            div('', class : 'w-100')

            comment 'detial view f/ events'
            div('' , id : 'detail', class: 'col bg-info height-thirty-five scroll-y')
          }
        }
      }
    }


    comment 'Import the .js file to handle events + load google maps'
    script( '', type:'text/javascript', src:'/js/main.js' )

    script ('async', 'defer' , src : 'https://maps.googleapis.com/maps/api/js?key=AIzaSyD7z1r4T2Z6mNda14Z2at9sX9Zj6CKbZa0&callback=initMap')
    mainBody()
  }
}