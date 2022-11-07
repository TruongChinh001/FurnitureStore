app.controller('profile-ctrl', function($scope, $http){
    $scope.session = {};
    $scope.items = [];

    $http.get('/rest/remote').then(resp => {
        $scope.session = resp.data;
    })

    $scope.initialize = function () {
        // load products
        $http.get('/rest/accounts').then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                if (item.photo == null) {
                    item.photo = "default.png";
                }
            })
        })
    }

    // Khởi đầu
    $scope.initialize();


    $scope.update = function(){
        var item = angular.copy($scope.session)
        $http.put(`/rest/accounts/${item.username}`, item).then(resp => {
            var index = $scope.items.findIndex(u => u.username == item.username);
            $scope.items[index] = item;
            alert('Cập nhật người dùng thành công!')
            window.location.reload();
        }).catch(error => {
            alert('Cập nhật người dùng thất bại!')
            console.log("Error", error);
        })
    }

    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.session.photo = resp.data.name;
        }).catch(error => {
            alert("Lỗi upload hình ảnh");
            console.log("Error", error);
        })
    }

})