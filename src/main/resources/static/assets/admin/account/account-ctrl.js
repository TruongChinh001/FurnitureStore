app.controller('account-ctrl', function ($scope, $http) {

    $scope.items = [];
    $scope.listCategories = [];
    $scope.form = {};

    $scope.initialize = function () {
        // load products
        $http.get('/rest/accounts').then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                if (item.photo == null) {
                    item.photo = "default.png";
                }
            })
        });


        $scope.form.createDate = new Date();
    }

    // Khởi đầu
    $scope.initialize();

    // Xóa form
    $scope.reset = function () {
        $scope.form = {
            photo: 'default.png',
        }
    }

    // hiển thị trên form
    $scope.edit = function (item) {
        console.log("click r nè")
        $scope.form = angular.copy(item);
        $('.nav-tabs li:eq(0) button').tab('show')
        console.log($scope.form.name);
    }

    // thêm sp
    $scope.create = function () {
        var item = angular.copy($scope.form);
        $http.post(`/rest/accounts`, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            alert('Thêm mới người dùng thành công!');
        }).catch(error => {
            alert('Thêm mới người dùng thất bại!');
            console.log('Error', error);
        })
    }

    // update
    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`/rest/accounts/${item.username}`, item).then(resp => {
            var index = $scope.items.findIndex(u => u.username == item.username);
            $scope.items[index] = item;
            alert('Cập nhật người dùng thành công!');
            window.location.reload();
        }).catch(error => {
            alert('Cập nhật người dùng thất bại!')
            console.log("Error", error)
        })
    }

    // xóa sp
    $scope.delete = function (item) {
        $http.delete(`/rest/accounts/${item.username}`).then(resp => {
            var index = $scope.items.findIndex(u => u.username == item.username);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert('Xóa người dùng thành công!')
        }).catch(error => {
            alert('Xóa người dùng thất bại!')
            console.log("Error", error)
        })
    }

    // upload hình
    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.photo = resp.data.name;
        }).catch(error => {
            alert("Dung lượng file quá lớn");
            console.log("Error", error);
        })
    }

    $scope.pageSize = 10;
    $scope.start = 0;
    $scope.pageIndex = 0;

    $scope.next = function () {
        if ($scope.start < $scope.items.length - $scope.pageSize) {
            $scope.start += $scope.pageSize;
            $scope.pageIndex++;
        }
    }

    $scope.prev = function () {
        if ($scope.start > 0) {
            $scope.start -= $scope.pageSize;
            $scope.pageIndex--;
        }
    }
    $scope.first = function () {
        $scope.start = 0;
        $scope.pageIndex = 0;
    }

    $scope.last = function () {
        sotrang = Math.ceil($scope.items.length / $scope.pageSize);
        $scope.start = $scope.pageSize * (sotrang - 1);
        $scope.pageIndex = $scope.count() - 1;
    }

    $scope.count = function () {
        return Math.ceil(1.0 * $scope.items.length / $scope.pageSize);
    }

})