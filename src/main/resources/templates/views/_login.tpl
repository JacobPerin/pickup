layout 'layouts/login.tpl',
        pageTitle: 'Login',
        mainBody: contents {
        	comment 'playing w/ a groovy fragment'
       		div(class : 'form-group bg-danger rounded text-center'){
       			p(error, class : 'text-white')
       		}
       		
    	}
