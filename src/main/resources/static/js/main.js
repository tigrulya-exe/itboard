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
            const card = document.createElement('div');
            card.classList.add('col-md-3', 'card')

            const eventTemplate = createCardTemplate(event);
            card.innerHTML = eventTemplate;

            const followButton = document.createElement('button');
            followButton.classList.add('btn', 'btn-primary');
            followButton.dataset.id = event.id;
            followButton.addEventListener('click', subscribeToEvent);
            followButton.textContent = 'Присоедениться';

            card.appendChild(followButton);
            row.appendChild(card);
        });

        pageContent.appendChild(row);
    })
}

async function subscribeToEvent() {
    const userId = sessionStorage.getItem('id');
    const eventId = this.dataset.id;
    const url = `/events/${eventId}/${userId}`;

    const response = await fetch(url, {method: 'POST'});

    if (response.status == 200 || response.status == 201)
        alert('Вы подписаны на событие!');
    else
        alert('Произшла ошибка!');
}

function createCardTemplate(event) {
    return `
    <p>Максимальное количество участников: ${event.maxParticipants}</p>
    <p>Расположение: ${event.location}</p>
    <p>Дата: ${event.beginDate}</p>
    <p>Продолжительность: ${event.duration}</p>
    <p>Описание: ${event.description}</p>
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