document.querySelector("#createList").addEventListener("click",createList);

var username = localStorage.getItem("loggedInUser");
console.log(localStorage.getItem("loggedInUser"));

populateLists().then(r => console.log(r));

async function populateLists(){
    let url = `https://intense-springs-54966.herokuapp.com/api/userLists?username=${username}`;

    let res = await fetchData(url);
    console.log(res);

    for(let i = 0; i < res.length; i++){
        document.querySelector("#listspace").innerHTML +=
            `<div class="col-lg-5">
                <div class="card-body px-lg-5 py-lg-5">
                    <button class="btn btn-warning btn-lg" onclick="listTeleporter(i, url)">${res[i].listName}</button>
                </div>
            </div>`;
    }

}

async function createList(){
    console.log("HERE");
    let newName = document.querySelector("#newWishlistName").value;
    let url = `https://intense-springs-54966.herokuapp.com/api/addList?username=${username}&listName=${newName}`;

    const requestOptions = {
        method: 'PUT',
        redirect: 'follow'
    };

    let res;
    await fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            res = result;
            console.log(res);
        })
        .then(response => console.log(response))
        .catch(error => console.log('error', error));

    // document.querySelector("#newWishlistName").value = "";

    // window.location.reload();

}

async function listTeleporter(listNum, url){
    // get wishlists again
    let res = await fetchData(url);
    // grab the Id of the current wishlist
    let listId = res[listNum].wishlistId;
    // store it
    localStorage.setItem("currentList", listId);
    // navigate to list page
    window.location.href = "./list-page.html"
}

async function fetchData(url){
    let response = await fetch(url);
    let data = await response.json();
    console.log(data);
    return data;
}