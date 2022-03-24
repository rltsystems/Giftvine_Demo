var username = localStorage.getItem("loggedInUser");
console.log(localStorage.getItem("loggedInUser"));
var currentList = localStorage.getItem("currentList");
console.log(localStorage.getItem("currentList"));

populateItems().then(r => console.log(r));

async function populateItems(){
    let url = `https://intense-springs-54966.herokuapp.com/api/listItems?listId=${currentList}`;

    let res = await fetchData(url);
    console.log(res);

    // if there are issues, its probably priority and description
    for(let i = 0; i < res.length; i++){
        document.querySelector("#itemDisplay").innerHTML +=
            `<dt class="col-sm-3">${res[i].itemName}</dt>
             <dd class="col-sm-9">
                <p class="text-muted">${res[i].description}</p>
                <p class="text-muted">${res[i].priority}</p>
                <p class="text-muted"><a href="${res[i].itemUrl}">Link</a></p>`;
    }

}
async function fetchData(url){
    let response = await fetch(url);
    let data = await response.json();
    console.log(data);
    return data;
}