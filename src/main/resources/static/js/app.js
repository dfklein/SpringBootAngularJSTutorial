/**
 * Declaração da aplicação para o Angular
 * 
 * A documentação do AngularJS está no http://code.angularjs.org
 */
var appCliente = angular.module('appCliente', []); // os colchetes são um array
													// onde se declaram
													// dependências (nesse caso,
													// da aplicação).

/**
 * Controller
 * 
 * A idéia dos parâmetros é que a primeira String é uma chave que chama o seu
 * controlador, que está definido pela função do segundo parâmetro.
 */
appCliente.controller('indexController', function($scope, $http) { // $scope é a
															// injeção de um
															// objeto nativo do
															// Angular. O objeto
															// $scope em
															// particular
															// refere-se ao
															// escopo contendo
															// tudo o que está
															// envolvido na
															// declaração de seu
															// controller (suas
															// variáveis, por
															// exemplo)
	$scope.clientes=[];
	$scope.cliente={}
	
	$scope.carregarClientes = function() {
		$http({
			method:'GET',
			url:'http://localhost:8080/SpringBootAngularJSTutorial/clientes/'
				//Você passa dois parâmetros: uma função de sucesso do callback e outr de exceção.
		}).then(function successCallBack(response){
			console.log(response.data);
			console.log(response.status);
			$scope.clientes = response.data;
			
		}, function errorCallBack(response) {
			console.log(response.data);
			console.log(response.status);
			
		});
	}
	
	$scope.salvarClientes = function() {
		$http({
			method:'POST',
			url:'http://localhost:8080/SpringBootAngularJSTutorial/clientes/',
			data:$scope.cliente
		}).then(function (response){
			$scope.clientes.push(response.data);
//			console.log(response.status);
//			$scope.clientes = response.data;
		}, function (response) {
			console.log(response.data);
			console.log(response.status);
			
		});
	}
	

});