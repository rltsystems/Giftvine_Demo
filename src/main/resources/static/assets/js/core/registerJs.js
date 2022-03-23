document.querySelector("#createAccBtn").addEventListener("click",createAccount);

async function createAccount(){
    let username = document.querySelector("#username").value;
    let password = document.querySelector("#password").value;
    let confirmPass = document.querySelector("#confirmPass").value;

    if(password !== confirmPass){
        console.log("PASSWORDS DONT MATCH");
    }
    else{
        let url = `https://intense-springs-54966.herokuapp.com/api/addUser`; //?username=${username}&password=${password}
        let data = { username: username, password: password };

        await fetch(
            url,
            {
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data),
                method: "POST"
            }
        )
            .then(data => data.json())
            .then((json) => {
                alert(JSON.stringify(json));
            });
        // let response = await fetchData(url, {
        //         method: "POST"
        //     });
        // let data = await response.json();
        // console.log(data)
    }
}

async function fetchData(url){
    let response = await (url);
    let data = await response.json();
    console.log(data);
    return data;
}