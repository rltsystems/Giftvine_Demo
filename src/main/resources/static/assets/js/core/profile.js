document.querySelector("#createList").addEventListener("click",createList);

var username = localStorage.getItem("loggedInUser");

populateLists();

async function populateLists(){
    let url = `https://intense-springs-54966.herokuapp.com/api/userLists?username=${username}`;

    const requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    let res;
    await fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            res = result;
        })
        // .then(response => console.log(response))
        .catch(error => console.log('error', error));

    console.log("USERLISTS RESPONSE:" + res);

    // TODO: find a way to make this dynamic with number of lists?
    for(let i = 0; i < 5; i++){
        document.querySelector("#listspace").innerHTML +=
            `<div class="col-lg-5">
                <div class="card-body px-lg-5 py-lg-5">
                    <a href="./list-page.html" class="btn btn-primary btn-lg">${res[i].listName}</a>
                </div>
            </div>`;
    }

}

async function createList(){
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
        })
        // .then(response => console.log(response))
        .catch(error => console.log('error', error));

    document.querySelector("#newWishlistName").value = "";

    window.location.reload();

}