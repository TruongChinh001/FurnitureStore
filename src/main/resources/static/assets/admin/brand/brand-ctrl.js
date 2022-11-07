app.controller('brand-ctrl', function($scope, $http){
    $scope.items = [];
    $scope.listBrands = [];
    $scope.form = {};

    $scope.initialize = function(){
        $http.get('/rest/brands').then(resp => {
            $scope.items = resp.data;
        });
    }

    // start
    $scope.initialize();

    //rest
    $scope.reset = function(){
        $scope.form = {};
    }

    // view
    $scope.edit = function(item){
        $scope.form = angular.copy(item);
        $('.nav-tabs li:eq(0) button').tab('show')
    }

    //create
    $scope.create = function () {
        var item = angular.copy($scope.form);
        $http.post(`/rest/brands`, item).then(resp => {
            // $scope.item.multipartFile = $scope.item.id;
            $scope.items.push(resp.data);
            $scope.reset();
            alert('Thêm mới thương hiệu thành công!');
        }).catch(error => {
            alert('Thêm mới thương hiệu thất bại!');
            console.log('Error', error);
        })
    }

    // $scope.imageChanged = function (files) {
    //     var data = new FormData();
    //     data.append('multipartFile', files[0]);
    //     $http.post(`/rest/brands/upload/images`, data, {
    //         transformRequest: angular.identity,
    //         headers: { 'Content-Type': undefined }
    //     }).then(resp => {
    //         $scope.form.logo = resp.data.name;
    //     }).catch(error => {
    //         alert("Lỗi upload hình ảnh");
    //         console.log("Error", error);
    //     })
    // }

    $scope.update = function(){
        var item = angular.copy($scope.form);
        $http.put(`/rest/brands/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(b => b.id == item.id);
            $scope.items[index] = item;
            alert('Cập nhật thương hiệu thành công!');
        }).catch(error => {
            alert('Cập nhật thương hiệu thất bại!');
            console.log("Error", error);
        });
    }

    $scope.delete = function(item){
        $http.delete(`/rest/brands/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(b => b.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert('Xóa thương hiệu thành công!');
            window.location.reload();
        }).catch(error => {
            alert('Xóa thương hiệu thất bại!');
            console.log("Error", error);
        });
    }

    // upload
    $scope.imageChanged = function(files){
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/brands/upload/images', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.form.logo = resp.data.name;
        }).catch(error => {
            alert('Lỗi tải lên hình ảnh');
            console.log("Error", error);
        })
    }

    $scope.pageSize = 10;
    $scope.start = 0;
    $scope.pageIndex = 0;

    $scope.next = function(){
        if($scope.start < $scope.items.length - $scope.pageSize){
            $scope.start += $scope.pageSize;
            $scope.pageIndex++;
        }
    }


    $scope.count = function(){
        return Math.ceil(1.0 * $scope.items.length / $scope.pageSize);
    }

})