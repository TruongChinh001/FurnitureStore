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
        //load  roles account
        $http.get("/rest/roles/roleaccount").then(resp => {
			$scope.authorities=resp.data;
             console.log($scope.authorities)
		}).catch(error=>{
			console.log("Lay  role account that bai")
			console.log(error)
		})
		 //load   account roles
		 $http.get("/rest/roles/accountrole").then(resp => {
			$scope.accountroles=resp.data;
            console.log($scope.accountroles)
		}).catch(error=>{
			console.log("Lay account role that bai")
			console.log(error)
		})
		/*
        // load staff and admin
        $http.get("/rest/accounts/admin?admin=true").then(resp => {
            $scope.admins = resp.data;
        })*/
        /*
        // load authorities of staff and admin
        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            // $location.path("/unauthorized");
            window.location.replace("/security/unauthorized");
        })
        */
    }
	$scope.loadRoleaccount=function(){
		 //load  roles account
        $http.get("/rest/roles/roleaccount").then(resp => {
			$scope.authorities=resp.data;
             console.log($scope.authorities)
		}).catch(error=>{
			console.log("Lay  role account that bai")
			console.log(error)
		})
	}
    $scope.authority_of = function(acc, role){
		if($scope.authorities){
			
			//console.log($scope.authorities.find(ur => ur.accounts[0].id == acc.id && ur.id == role.id))
			return $scope.authorities.find(ur => ur.account_id == acc.id && ur.role_id == role.id)
        }
    }
	
	
	
    $scope.authority_changed = function(acc, role){
		var authority = $scope.authority_of(acc, role);
        if(authority){// đã cấp quyền => thu hồi
        	 $scope.revoke_authority(authority);
             console.log(authority+"tao ne 3")
        } else{ // chưa được cấp quyền => thêm mới
            
            authority={account_id:acc.id, role_id:role.id};
				$scope.grant_authority(authority);
		}
    }

    // thêm mới authority
    $scope.grant_authority = function(authority){
        $http.put(`/rest/accounts/${authority.account_id}/role/${authority.role_id}`, authority).then(resp => {
           $scope.loadRoleaccount();
			alert("Cấp quyền sử dụng thành công");
           
        }).catch(error => {
            alert('Cấp quyền sử dụng thất bại!');
            console.log("Error", error);
        })
    }

    // xóa authority
    $scope.revoke_authority = function(authority){
        $http.put(`/rest/accounts/delete/${authority.account_id}/role/${authority.role_id}`, authority ).then(resp => {
           $scope.loadRoleaccount();
			alert("Thu hồi quyền sử dụng thành công");
           
        }).catch(error => {
            alert('Thu hồi sử dụng thất bại!');
            console.log("Error", error);
        })
    }

    $scope.initialize();
    

})