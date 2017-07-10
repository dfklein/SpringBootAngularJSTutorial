/**
 * 
 */
appCliente.controller('mainController', function($scope, $location, $route, $routeParams) {
	$scope.$location = $location; // desta forma você insere uma referência para o location no scope. Você pode usar o mesmo nome para não confundir.
	$scope.$route = $route;
	$scope.$routeParams = $routeParams;
});