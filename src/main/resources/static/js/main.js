// var id;

// function reqListener () {
//     console.log(this.responseText);
//     id = this.responseText;
//     window.location.href = "http://localhost:8080/menu.html";
// }

// function login(){
//     var userName = document.getElementById("username").value;
//     var oReq = new XMLHttpRequest();
//     oReq.onload = reqListener;
//     oReq.open("get", "http://localhost:8080/login/" + userName, true);
//     oReq.send();
// }

// function showEvents() {
//     document.getElementById('page-content').innerHTML = this.responseText;
// }

// function showProfile() {
//     document.getElementById('page-content').innerHTML = "profile";
// }

// function showSearch() {
//     document.getElementById('page-content').innerHTML = "search";
// }

// function getEvents() {
//     var oReq = new XMLHttpRequest();
//     oReq.onload = showEvents;
//     oReq.open("get", "http://localhost:8080/event/list", true);
//     oReq.send();
// }




async function getAllEvents() {
    const url = '/events';

    const events = await fetch(url);

    console.log(await events.json());
}

window.onload = () => {
    getAllEvents();
}