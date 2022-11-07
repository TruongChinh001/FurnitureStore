const app = angular.module('shopping-cart-app', []);
app.controller('shopping-cart-ctrl', function ($scope, $http) {
	/* QUẢN LÝ GIỎ HÀNG */
	$scope.cartfurniture= {
		items: [],

		// add to cart
		add(id) {
			var item = this.items.find(item => item.id == id);
			if (item) { // nếu sản phẩm có trong giỏ hàng thì tăng số lượng
				item.quantity++;
				this.saveToLocalStorage();
			} else { // ngược lại nếu chưa có sản phẩm trong giỏ hàng thì số lượng = 1
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.quantity = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				});
			}
		},

		// delete 
		remove(id) {
			var item = this.items.findIndex(item => item.id == id);
			this.items.splice(item, 1);
			this.saveToLocalStorage();
		},

		// clear
		clear() {
			this.items = [];
			this.saveToLocalStorage();
		},

		//total item
		get totalItem() {
			return (this.items).length;
		},

		// total count
		get count() {
			return this.items
				.map(item => item.quantity)
				.reduce((total, quantity) => total += quantity, 0);
		},

		// money/total product
		get amount() {
			return this.items
				.map(item => item.quantity * item.price)
				.reduce((total, quantity) => total += quantity, 0);
		},

		// save to local storage
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cartfurniture", json);
		},
		// read local storage
		loadFromLocalStorage() {
			var json = localStorage.getItem("cartfurniture");
			this.items = json ? JSON.parse(json) : [];
		},
		plus(item, num) {
			item.quantity += num;
			this.saveToLocalStorage();
			this.amount;
			console.log(this.amount);
			if (item.quantity <= 0) {
				$scope.del(item);
			}
		}
	}

	$scope.cartfurniture.loadFromLocalStorage();
	$scope.order = {
		createDate: new Date(),
		addressLine1:"",
		provinceCity:"",
		district:"",
		wardVillage:"",
		hamlet:"",
		account:{username:$('#username').text()},

		get orderDetails(){
			return $scope.cartfurniture.items.map(item => {
				return {
					product:{id: item.id},
					price: item.price,
					quantity: item.quantity
				}
			});
		},
		purchase(){
			var order = angular.copy(this);
			$http.post('/rest/orders', order).then(resp => {
				alert('Đặt hàng thành công!');
				$scope.cartfurniture.clear();
				console.log(resp.data);
				location.href = '/order/detail/' + resp.data.id;
			}).catch(error => {
				alert('Lỗi');
				console.log(error);
			});
		}
	}
})