/**
 * Controller
 * 
 * A idéia dos parâmetros é que a primeira String é uma chave que chama o seu
 * controlador, que está definido pela função do segundo parâmetro.
 */
appCliente.controller('clienteController', function($scope, $http) { // $scope é a
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
	
	$scope.salvarCliente = function() {
		if ($scope.frmCliente.$invalid) { // todos os elementos que você declara com "name" no html é adicionado ao escopo com uma variável de mesmo nome.
			$http({
				method:'POST',
				url:'http://localhost:8080/SpringBootAngularJSTutorial/clientes/',
				data:$scope.cliente
			}).then(function (response){
	//			$scope.clientes.push(response.data);
				$scope.carregarClientes();
				$scope.cancelarAlteracao();
				$scope.frmCliente.$setPristine(true);
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
				
			});
		
		
		} else {
			window.alert('Erro de validação');
		}
	}
	
	$scope.excluirCliente = function(cliente) {
		$http({
			method:'DELETE',
			url:'http://localhost:8080/SpringBootAngularJSTutorial/clientes/' + cliente.id,
			data:$scope.cliente
		}).then(function (response){
			var pos = $scope.clientes.indexOf(cliente);
			$scope.clientes.splice(pos, 1);
			$scope.cancelarAlteracao();
			
		}, function (response) {
			console.log(response.data);
			console.log(response.status);
			
		});
	}
	
	$scope.alterarCliente = function(cliente) {
		$scope.cliente = angular.copy(cliente);
		
//		$http({
//			method:'DELETE',
//			url:'http://localhost:8080/SpringBootAngularJSTutorial/clientes/' + cliente.id,
//			data:$scope.cliente
//		}).then(function (response){
//			var pos = $scope.clientes.indexOf(cliente);
//			$scope.clientes.splice(pos, 1);
//			
//		}, function (response) {
//			console.log(response.data);
//			console.log(response.status);
//			
//		});
	}
	
	$scope.cancelarAlteracao = function() {
		$scope.cliente = {};
	}
	
	

});