document.querySelector("#createAccBtn").addEventListener("click",createAccount);

if(!("loggedInUser" in localStorage)){
    localStorage.setItem("loggedInUser", null);
}

async function createAccount(){
    let username = document.querySelector("#username").value;
    let password = document.querySelector("#password").value;
    let confirmPass = document.querySelector("#confirmPass").value;

    if(password !== confirmPass){
        console.log("PASSWORDS DONT MATCH");
    }
    else{
        let url = `https://intense-springs-54966.herokuapp.com/api/addUser?username=${username}&password=${password}`;

        const requestOptions = {
            method: 'POST',
            redirect: 'follow'
        };

        await fetch(url, requestOptions)
            .then(response => response.text())
            .then(result => console.log(result))
            .catch(error => console.log('error', error));

        localStorage.setItem("loggedInUser", username);
        console.log(localStorage.getItem("loggedInUser"));
        document.location.href = `https://intense-springs-54966.herokuapp.com/profile.html`
    }
}

async function fetchData(url){
    let response = await (url);
    let data = await response.json();
    console.log(data);
    return data;
}