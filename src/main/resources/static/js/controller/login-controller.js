/**
 * 
 */
appCliente.controller('loginController', function($scope, $http) {
	
	$scope.usuario = {};
	
	$scope.token = '';
	
	$scope.autenticar = function() {
		console.log($scope.usuario.nome);
		console.log($scope.usuario.senha);
		console.log($scope.usuario.algo);
		$http.post("/SpringBootAngularJSTutorial/autenticar", $scope.usuario).then(
				function success(response) {
					$scope.token = response.data.token;
					localStorage.setItem("userToken", response.data.token);
					
					// você precisa armazenar este token em um lugar acessível para outros controllers. Pode ser no storage do navegador ou via cookies.
					
				},
				
				function fail(response) {
					
				}
				
		);
			
	}
	
});