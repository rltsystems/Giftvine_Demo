

async function populateLists(){
    let username = localStorage.getItem("loggedInUser");
    let url = `https://intense-springs-54966.herokuapp.com/api/userLists?username=${username}`;
}