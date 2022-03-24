document.querySelector("#createAccBtn").addEventListener("click",createAccount);

if(!("loggedInUser" in localStorage)){
    localStorage.setItem("loggedInUser", null);
}

async function createAccount(){
    let username = document.querySelector("#username").value;
    let password = document.querySelector("#password").value;
    let confirmPass = document.querySelector("#confirmPass").value;

    if(password !== confirmPass){
        alert("Passwords don't match");
    }
    else if(password.length < 6){
        alert("Password must be at least 6 characters");
    }
    else{
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
}