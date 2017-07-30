/**
 * Este é um interceptor que ele vai fazer uma verificação (a da existência do token, no caso) para toda requisição.
 * Você precisa adicionar este interceptor nas configurações de rotas. (Nesta aplicação ela está no app.js)
 */
appCliente.factory('tokenInterceptor', function($q, $location) { // o $q é um objeto que pode aceitar ou rejeitar uma ação de request
	
return {
		
		'request': function(config){
			
			config.headers.Authorization = 'Bearer ' + localStorage.getItem("userToken");  
		
			return config;
		},
		
//		'response' : function(rejection) {
//			 ???
//		}
		
		'responseError': function (rejection){
	    	alert('Token inválido');
	    	if(response.status==401){ // 401 é o código do erro de autenticação.
	    		$location.path("/login");
	    	}
	    	
	    	if(rejection.status==500){ 
	    		$location.path("/login");
	    	}
	    	return $q.reject(rejection);
	    }
	
	
	};
});