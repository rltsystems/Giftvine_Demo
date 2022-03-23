document.querySelector("#createAccBtn").addEventListener("click",createAccount);

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
    }
}

async function fetchData(url){
    let response = await (url);
    let data = await response.json();
    console.log(data);
    return data;
}