/**
 * Declaração da aplicação para o Angular
 * 
 * A documentação do AngularJS está no http://code.angularjs.org
 */
var appCliente = angular.module('appCliente', ['ngRoute']); // os colchetes são um array
													// onde se declaram
													// dependências (nesse caso,
													// da aplicação).

//Esta é uma configuração de rotas, onde você mapeia as requisições do Angular que não chamam o servidor.
appCliente.config(function($routeProvider){
	// o primeiro parâmetro é a URL que você está mapeando e a segunda é um JSON com configurações.
	$routeProvider
	.when('/clientes', {
		// os parâmetros abaixo são respectivamente a URL chamada e o controller utilizado. Desta forma você não utilizaria o "ng-controller" no html. Ver a página cliente.html
		templateUrl:'view/cliente.html',
		controller:'clienteController'
	})
	// o otherwise seria um tipo de "else" para a declaração de rotas, onde você passa a url que será direcionado caso haja uma requisição para uma rota inexistente.
	.otherwise({
		redirectTo:'/'
	});

});
