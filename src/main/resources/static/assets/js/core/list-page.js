
var username = localStorage.getItem("loggedInUser");
console.log(localStorage.getItem("loggedInUser"));

async function populateItems(){

}
async function fetchData(url){
    let response = await fetch(url);
    let data = await response.json();
    console.log(data);
    return data;
}