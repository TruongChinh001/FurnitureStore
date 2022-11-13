app.controller('product-ctrl', function ($scope, $http) {


    $scope.orightml = '<h2>Mô tả ngắn!</h2><p>textAngular is a super cool WYSIWYG Text Editor directive for AngularJS</p><p><b>Features:</b></p><ol><li>Automatic Seamless Two-Way-Binding</li><li>Super Easy <b>Theming</b> Options</li><li style="color: green;">Simple Editor Instance Creation</li><li>Safely Parses Html for Custom Toolbar Icons</li><li class="text-danger">Doesn&apos;t Use an iFrame</li><li>Works with Firefox, Chrome, and IE8+</li></ol><p><b>Code at GitHub:</b> <a href="https://github.com/fraywing/textAngular">Here</a> </p>';
    $scope.htmlcontent = $scope.orightml;
    $scope.disabled = false;


    // $filter('filter')(array, expression, comparator, anyPropertyKey)
    $scope.items = [];
    $scope.listCategories = [];
    $scope.listBrands = [];
    $scope.listUnitProducts = [];
    $scope.listMaterials = [];
    $scope.form = {};

    $scope.initialize = function () {
        $http.get('/rest/brands').then(resp => {
            $scope.listBrands = resp.data;
        })
        $http.get('/rest/categories').then(resp => {
            $scope.listCategories = resp.data;
        })
        $http.get('/rest/unit-products').then(resp => {
            $scope.listUnitProducts = resp.data;
        })
        $http.get('/rest/materials').then(resp => {
            $scope.listMaterials = resp.data;
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

    $scope.reset = function () {
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

        $http.post(`/rest/products`, item).then(resp => {
            $scope.items.push(resp.data);
            console.log(resp.data);
            $scope.reset();
            $scope.initialize();
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
    $scope.delete = function (item) {
        $http.delete(`/rest/products/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert('Xóa sản phẩm thành công!')
        }).catch(error => {
            alert('Xóa sản phẩm thất bại!')
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
            $scope.form.mainImage = resp.data.name;
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