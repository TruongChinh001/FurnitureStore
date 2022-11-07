function openNav() {
  document.getElementById("mySidebar").style.width = "100%";
}

function closeNav() {
  document.getElementById("mySidebar").style.width = "0";
  document.getElementById("main").style.marginLeft = "0";
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



