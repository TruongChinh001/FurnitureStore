function openNav() {
  document.getElementById("mySidebar").style.width = "100%";
}

function closeNav() {
  document.getElementById("mySidebar").style.width = "0";
  document.getElementById("main").style.marginLeft = "0";
}

// render province city, district, ward
var citis = document.getElementById("city");
var districts = document.getElementById("district");
var wards = document.getElementById("ward");
var Parameter = {
  url: "http://localhost:8080/rest/address/province-cities",
  method: "GET",
  responseType: "application/json",
};
var promise = axios(Parameter);
promise.then(function (result) {
  renderCity(result.data);
});

function renderCity(data) {
  for (const x of data) {
    citis.options[citis.options.length] = new Option(x.name, x.id);
  }
  citis.onchange = function () {
    district.length = 1;
    ward.length = 1;
    if (this.value != "") {
      const result = data.filter(n => n.id == this.value);
      console.log(result)

      for (const k of result[0].districts) {
        district.options[district.options.length] = new Option(k.name, k.id);
      }
    }
  };
  district.onchange = function () {
    ward.length = 1;
    const dataCity = data.filter((n) => n.id == citis.value);
    if (this.value != "") {
      const dataWards = dataCity[0].districts.filter(n => n.id == this.value)[0].wards;

      for (const w of dataWards) {
        wards.options[wards.options.length] = new Option(w.name, w.id);
      }
    }
  };
}







// const userAction = async (id) => {
//     const response = await fetch(`http://localhost:8080/rest/category-groups/${id}`);
//     const data = await response.json(); //extract JSON from the http response
//     console.log(data)
//     show(data);
//     // do something with myJson
// }

// const show= function(data){
//     let tab = ``;
//     for (let c of data){
//         tab += `<a href="/products?cid=${c.id}">${c.name}</a>`
//         document.getElementById("name-category").innerHTML = tab;
//     }
// }



