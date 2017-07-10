/**
 * 
 */
appCliente.controller('clienteDetalhesController', function($scope, $routeParams, $http) {
	
	$scope.cliente = {};
	// Atenção mais uma vez para as diferenças entre usar o Tomcat embutido e o deploy na aplicação. Tem que adicionar o nome da aplicação quando o tomcat roda fora.
	$http.get('/SpringBootAngularJSTutorial/clientes/' + $routeParams.clienteId // "clienteId" é o nome da variável que está declarada nas rodas no app.js
		).then(function(response){
			$scope.cliente = response.data;
			
		}, 
		function(response) {
			console.log(response);
			
		});
});