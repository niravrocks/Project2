app.factory('UserService',function($http){
	var userService=this;
	var BASE_URL="http://localhost:8079/niravbacked"
		
	userService.authenticate=function(user){
		return $http.post(BASE_URL + "/login",user);
	}
	userService.registerUser=function(user){
		return $http.post(BASE_URL + "/register",user) 
	}
	return userService;
})


