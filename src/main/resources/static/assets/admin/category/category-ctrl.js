app.controller('category-ctrl', function ($scope, $http) {

    $scope.items = [];
    $scope.listCategories = [];
    $scope.listCategoriesGroup = [];
    $scope.form = {};

    $scope.initialize = function () {
        // load categories
        $http.get('/rest/categories-group').then(resp => {
            $scope.listCategoriesGroup = resp.data;
        })

        $http.get('/rest/categories').then(resp => {
            $scope.items = resp.data;
        })
    }

    // Khởi đầu
    $scope.initialize();

    // Xóa form
    $scope.reset = function () {
        $scope.form = {
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
        $http.post(`/rest/categories`, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            alert('Thêm mới danh mục thành công!');
        }).catch(error => {
            alert('Thêm mới danh mục thất bại!');
            console.log('Error', error);
            console.log($scope.item);
        })
    }

    // update
    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`/rest/categories/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(c => c.id == item.id);
            $scope.items[index] = item;
            alert('Cập nhật danh mục thành công!');
        }).catch(error => {
            alert('Cập nhật danh mục thất bại!')
            console.log("Error", error)
        })
    }

    // xóa sp
    $scope.delete = function (item) {
        $http.delete(`/rest/categories/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(c => c.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert('Xóa danh mục thành công!')
            window.location.reload();
        }).catch(error => {
            alert('Xóa danh mục thất bại!')
            console.log("Error", error)
        })
    }

    $scope.pageSize = 10;
    $scope.start = 0;
    $scope.pageIndex = 0;
    $scope.pageNo = 0;

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
    $scope.count = function(){
        return Math.ceil(1.0 * $scope.items.length / $scope.pageSize);
    }

})