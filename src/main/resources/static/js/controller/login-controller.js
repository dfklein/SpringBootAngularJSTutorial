/**
 * 
 */
appCliente.controller('loginController', function($scope, $http) {
	
	$scope.autenticar = function() {
		$http.post("/SpringBootAngularJSTutorial/autenticar", $scope.usuario).then(
				function success(response) {
					
				},
				
				function fail(response) {
					
				}
				
		);
			
	}
	
});