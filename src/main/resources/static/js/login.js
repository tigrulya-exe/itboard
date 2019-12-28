
function reqListener () {
    console.log(this.responseText);
}

function login(){
    var userName = document.getElementById("username").value;
    console.log(userName);
    var oReq = new XMLHttpRequest();
    oReq.onload = reqListener;
    oReq.open("get", "http://localhost:8080/login/" + userName, true);
    oReq.send();
}