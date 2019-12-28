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

function devideToSubarrays(array) {
    let size = 3;
    let subarray = [];

    for (let i = 0; i <Math.ceil(array.length/size); i++){
        subarray[i] = array.slice((i*size), (i*size) + size);
    }

    return subarray;
}

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

    console.log(events);

    drawEvents(events);
}

function drawEvents(events) {
    const pageContent = document.querySelector('#page-content');
    pageContent.innerHTML = '';

    const zippedEvents = devideToSubarrays(events);

    zippedEvents.forEach(pack => {
        const row = document.createElement('div');
        row.classList.add('row');

        pack.forEach(event => {
            const eventTemplate = createCardTemplate(event);
            row.innerHTML += eventTemplate;
        });

        pageContent.appendChild(row);
    })
}

function createCardTemplate(event) {
    return `
    <div class="col-md-3 card" data-id="${event.id}">
    <p>Максимальное количество участников: ${event.maxParticipants}</p>
    <p>Расположение: ${event.location}</p>
    <p>Дата: ${event.beginDate}</p>
    <p>Продолжительность: ${event.duration}</p>
    <p>Описание: ${event.description}</p>
  </div>
    `
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