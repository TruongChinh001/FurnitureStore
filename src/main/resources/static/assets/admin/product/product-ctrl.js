app.controller('product-ctrl', function ($scope, $http) {

    // $filter('filter')(array, expression, comparator, anyPropertyKey)
    $scope.items = [];
    $scope.listCategories = [];
    $scope.listBrands = [];
    $scope.form = {};

    $scope.initialize = function () {
        $http.get('/rest/brands').then(resp => {
            $scope.listBrands = resp.data;
        })
        $http.get('/rest/categories').then(resp => {
            $scope.listCategories = resp.data;
        })
        $http.get('/rest/products').then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                if (item.image == null) {
                    item.image = "default.png";
                }
                // item.createDate = new Date(item.createDate);
            })
        })
    }

    // Khởi đầu
    $scope.initialize();

    $scope.richText = function () {
        $('#shortDesc').richText();
        $('#longDesc').richText();
        if ($('#shortDesc').empty()) {
            $('#shortDesc').remove();
        } if ($('#longDesc').empty()) {
            $('#longDesc').remove()
        }
    }

    // Xóa form
    // $scope.reset = function () {
    //     $scope.form = {
    //         createDate: new Date(),
    //         image: 'default.png',
    //         available: true
    //     }
    // }
    $scope.reset = function(){
        $scope.form = {};
    }

    //hiển thị trên form
    $scope.edit = function (item) {
        console.log("click r nè")
        $scope.form = angular.copy(item);
        $('.nav-tabs li:eq(0) button').tab('show')
        console.log($scope.form.name);
    }

    $scope.create = function () {
        var item = angular.copy($scope.form);

        // // item.brand = Number(item.brand);
        // $http.get(`/rest/brands/${item.brand}`).then(resp => {
        //     item.brand = resp.data;
        // })

        // // item.category = Number(item.category);
        // $http.get(`/rest/categories/${item.category}`).then(resp => {
        //     item.category = resp.data;
        // })
        // item.mainImage = "null";
        // item.shortDesc = "abc";
        // item.longDesc = "cccc";

        $http.post(`/rest/products`, item).then(resp => {
            $scope.items.push(resp.data);
            console.log(resp.data);
            $scope.reset();
            alert('Thêm mới sản phẩm thành công!');
        }).catch(error => {
            alert('Thêm mới sản phẩm thất bại!');
            console.log('Error', error);
        });
    }

    // update
    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert('Cập nhật sản phẩm thành công!');
            window.location.reload();
        }).catch(error => {
            alert('Cập nhật sản phẩm thất bại!')
            console.log("Error", error)
        })
    }

    // xóa sp
    // $scope.delete = function (item) {
    //     $http.delete(`/rest/products/${item.id}`).then(resp => {
    //         var index = $scope.items.findIndex(p => p.id == item.id);
    //         $scope.items.splice(index, 1);
    //         $scope.reset();
    //         alert('Xóa sản phẩm thành công!')
    //     }).catch(error => {
    //         alert('Xóa sản phẩm thất bại!')
    //         console.log("Error", error)
    //     })
    // }

    // upload hình
    // $scope.imageChanged = function(files){
    //     var data = new FormData();
    //     data.append('file', files[0]);
    //     $http.post('/rest/products/upload/images', data, {
    //         transformRequest: angular.identity,
    //         headers: {'Content-Type': 'multipart/form-data'}
    //     }).then(resp => {
    //         $scope.form.mainImage = resp.data.name;
    //     }).catch(error => {
    //         alert('Lỗi tải lên hình ảnh');
    //         console.log("Error", error);
    //     })
    // }

    //phân trang
    // $scope.pager = {
    //     page:0,
    //     size:10,
    //     get items(){
    //         var start = this.page * this.size;
    //         return $scope.items.slice(start, start + this.size);
    //     },
    //     get count(){
    //         return Math.ceil(1.0 * $scope.items.length / this.size)
    //     },
    //     first(){
    //         this.page = 0;
    //     },
    //     prev(){
    //         this.page--;
    //         if(this.page < 0){
    //             this.last();
    //         }
    //     },
    //     next(){
    //         this.page++;
    //         if(this.page >= this.count){
    //             this.first();
    //         }
    //     },
    //     last(){
    //         this.page = this.count - 1;
    //     }
    // }

    // $scope.pageSize = 5;
    // $scope.start = 0;
    // $scope.pageIndex = 0;

    // $scope.next = function () {
    //     if ($scope.start < $scope.items.length - $scope.pageSize) {
    //         $scope.start += $scope.pageSize;
    //         $scope.pageIndex++;
    //     }
    // }
    // $scope.prev = function () {
    //     if ($scope.start > 0) {
    //         $scope.start -= $scope.pageSize;
    //         $scope.pageIndex--;
    //     }
    // }
    // $scope.first = function () {
    //     $scope.start = 0;
    //     $scope.pageIndex = 0;
    // }
    // $scope.last = function () {
    //     sotrang = Math.ceil($scope.items.length / $scope.pageSize);
    //     $scope.start = $scope.pageSize * (sotrang - 1);
    //     $scope.pageIndex = $scope.count() - 1;
    // }
    // $scope.count = function () {
    //     return Math.ceil(1.0 * $scope.items.length / $scope.pageSize);
    // }

})