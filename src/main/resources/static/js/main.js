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


function addListeners() {
    document.querySelector('#getEvents')
        .addEventListener('click', getAllEvents);

    document.querySelector('#search')
        .addEventListener('click', showSearchPage);

    document.querySelector('#showProfile')
        .addEventListener('click', showProfileInfo);
}

async function getAllEvents() {
    const url = '/events';

    const response = await fetch(url);
    const events = await response.json();

    drawEvents(events);
}

function drawEvents(events) {
    const pageContent = document.querySelector('#page-content');
    
    pageContent.textContent = `С сервера пришло ${events.length} событий`;
}

function showSearchPage() {
    const pageContent = document.querySelector('#page-content');
    
    pageContent.textContent = `Макет поисковой хрени`;
}

function showProfileInfo() {
    const pageContent = document.querySelector('#page-content');
    
    pageContent.textContent = `Макет профиля`;
}

window.onload = () => {
    addListeners();
    getAllEvents();
}