layout 'layouts/main.tpl',
        pageTitle: 'Main',
        mainBody: contents {
            div(class: 'container-fluid'){
              comment 'split page into two halves :: (1) active events list (2) google map + detial view'
              div(class: 'row height-full') {
                
                comment 'left side of page'  
                div(class: 'col-3 height-full scroll-y', style:'background-color: #CCD6DD;') {
                  input(type:'hidden', id:"userId", name:"username", value: userId)
                  div(class: 'container-fluid'){
                  	    ul(id : 'active-events', class : 'list-group')
                          // events.each { event ->
                          //   li {
                          //     span(class : 'badge badge-primary badge-pill', text : event.description)
                          //   }

                          // }
                        
                  }
                }
                
                comment 'right side of page'
                div(class : 'col-9 height-full') {
                  
                  div(class : 'row') {
                    comment 'google map'
                    div('', id : 'map', class: 'col height-sixty-five')
                    comment 'make column break to next line'
                    div('', class : 'w-100')
                    comment 'detial view f/ events'
                    div('' , id : 'detail', class: 'col height-thirty-five scroll-y', style:'background-color: #E1E8ED;')
                  }
                }
              }
          }
        }