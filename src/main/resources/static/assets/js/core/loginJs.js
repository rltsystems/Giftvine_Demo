


async function login(){
    let username = document.querySelector("#username").value;
    let password = document.querySelector("#password").value;

    let url = `https://intense-springs-54966.herokuapp.com/api/addUser?username=${username}&password=${password}`;

    const requestOptions = {
        method: 'POST',
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

    console.log("RESPONSE:" + res);
    if(res === "saved"){
        localStorage.setItem("loggedInUser", username);
        console.log(localStorage.getItem("loggedInUser"));
        document.location.href = `https://intense-springs-54966.herokuapp.com/profile.html`
    }
    else{
        alert(res);
    }
}