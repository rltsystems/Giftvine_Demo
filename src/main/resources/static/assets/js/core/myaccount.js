document.querySelector("#deleteAccBtn").addEventListener("click",deleteAccount);
document.querySelector("#changeAccBtn").addEventListener("click",changeAccount);

async function changeAccount() {
    let checkbox = document.querySelector("#checkbox").value;
    //Once we get a checkbox value that is either "username" or "password"
    //We will then use that to update corresponding username or password via API call

    let currentUsername = localStorage.getItem("loggedInUser");
    let newInfo = document.querySelector('#input1').value; //this is the first input box that can be either username or password
    let secondBox = document.querySelector('#input2').value;
//section for  the username/password change
    var requestOptions = {
        method: 'PATCH',
        redirect: 'follow'
    };

    //validation portion
    if (newInfo !== secondBox) {
        alert("Passwords or Usernames do not match.")
    } else if((newInfo.length < 6) && (document.querySelector('#usercheckbox').value == 'password') ){
        alert("Password must be at least 6 characters");
    } else {
        if (document.querySelector('#usercheckbox').value == 'username') {
            //section that updates username
            fetch("https://intense-springs-54966.herokuapp.com/api/updateUser?originalUsername=${currentUsername}&newUsername=${newInfo}", requestOptions)
                .then(response => response.text())
                .then(result => console.log(result))
                .catch(error => console.log('error', error));
        } else if (document.querySelector('#passcheckbox').value == 'password') {
            //Section that updates password
            fetch("https://intense-springs-54966.herokuapp.com/api/updateUser?originalUsername=${originalUsername}&password=${newInfo}", requestOptions)
                .then(response => response.text())
                .then(result => console.log(result))
                .catch(error => console.log('error', error));
        }
    }
}
async function deleteAccount(){
    let username = document.querySelector("#username").value;

    var requestOptions = {
        method: 'DELETE',
        redirect: 'follow'
    };

    fetch("https://intense-springs-54966.herokuapp.com/api/deleteUser?username=${username}", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}



