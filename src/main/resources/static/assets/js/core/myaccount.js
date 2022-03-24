document.querySelector("#deleteAccBtn").addEventListener("click", deleteAccount);
document.querySelector("#changeAccBtn").addEventListener("click", changeAccount);

var username = localStorage.getItem("loggedInUser");
async function changeAccount() {
    //Once we get a checkbox value that is either "username" or "password"
    //We will then use that to update corresponding username or password via API call

    let newInfo = document.querySelector('#input1').value; //this is the first input box that can be either username or password
    let secondBox = document.querySelector('#input2').value;
    //section for  the username/password change
    var requestOptions = {
        method: 'PATCH',
        redirect: 'follow'
    };

    //validation portion
    if (newInfo !== secondBox) {
        alert("Passwords or Usernames do not match.");
    } else if (newInfo == secondBox) {
        if (document.querySelector('#usercheckbox').value == 'username') {
            //section that updates username
            let url = `https://intense-springs-54966.herokuapp.com/api/updateUser?originalUsername=${username}&newUsername=${newInfo}`;
            await fetch(url, requestOptions)
                .then(response => response.text())
                .then(result => console.log(result), alert("Success Username Changed!"))
                .catch(error => console.log('error', error));
        }
        if (document.querySelector('#passcheckbox').value == 'password') {
            if (newInfo.length < 6) {
                alert("Password must be at least 6 characters");
            } else {
                //Section that updates password
                let url1 = `https://intense-springs-54966.herokuapp.com/api/updateUser?originalUsername=${username}&password=${newInfo}`;
                await fetch(url1, requestOptions)
                    .then(response => response.text())
                    .then(result => console.log(result), alert("Success Password Changed!"))
                    .catch(error => console.log('error', error));
            }
        }
    }
}
async function deleteAccount() {

    var requestOptions = {
        method: 'DELETE',
        redirect: 'follow'
    };
    let url = `https://intense-springs-54966.herokuapp.com/api/deleteUser?username=${username}`;
    await fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}