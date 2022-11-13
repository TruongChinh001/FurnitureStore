app.controller('authority-ctrl', function($scope, $http, $location){
    
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];
	$scope.accounts = [];
	$scope.accountroles=[];
	$scope.roleaccount=[];
    $scope.initialize = function(){
        //load all roles
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        })
		
		// load staff and admin
        $http.get("/rest/accounts").then(resp => {
            $scope.admins = resp.data;
            
        })

        // load authorities of staff and admin
        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            // $location.path("/unauthorized");
            window.location.replace("/security/unauthorized");
        })
    }
    
    $scope.loadAuthorities = function(){
         // load authorities of staff and admin
         $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            // $location.path("/unauthorized");
            window.location.replace("/security/unauthorized");
        })
    }

    $scope.authority_of = function(acc, role){
        if($scope.authorities){
            return $scope.authorities.find(ur => ur.account.username == acc.username
                && ur.role.id == role.id);
        }
    }
	
	$scope.authority_changed = function(acc, role){
        var authority = $scope.authority_of(acc, role);
        if(authority){// đã cấp quyền => thu hồi
            $scope.revoke_authority(authority);
        } else{ // chưa được cấp quyền => thêm mới
            authority = {
                account: acc,
                role: role
            }
            $scope.grant_authority(authority);
        }
    }

    // thêm mới authority
    $scope.grant_authority = function(authority){
        $http.post(`/rest/authorities`, authority).then(resp => {
            $scope.authorities.push(resp.data);
            alert('Cấp quyền sử dụng thành công!');
        }).catch(error => {
            alert('Cấp quyền sử dụng thất bại!');
            console.log("Error", error);
        })
    }

    // xóa authority
    $scope.revoke_authority = function(authority){
        $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
            $scope.loadAuthorities();
            alert('Thu hồi quyền sử dụng thành công!');
        }).catch(error => {
            alert('Thu hồi quyền sử dụng thất bại!');
            console.log("Error", error);
        })
    }

    $scope.initialize();
    

})