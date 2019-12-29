const searchPageTemplate = `
<div class="row justify-content-center">
          <form class="col-md-6" id = "searchForm">
            <div class="row">
              <input type="text" placeholder="Введите параметры поиска" class = "form-control mb-2" id = "searchInput">
            </div>
            <div class="row">
              <div class="col-md-12">
                <p>Поиск по: </p>
                <div class="row">
                  <div class="col-md-6">
                    <input type="radio" class = "form-check-input" checked name = "category" value = "users">
                    <label class="form-check-label">
                        Пользователям:
                    </label>
                  </div>
                  <div class="col-md-3">
                    <input type="radio" class = "form-check-input" name = "category" value = "events">
                    <label class="form-check-label">
                        Мероприятиям:
                    </label>
                  </div>
                </div>
              </div>
            </div>
            <button class="btn btn-primary mt-3" type="submit">Искать</button>
          </form>
        </div>
`;

function devideToSubarrays(array) {
    let size = 3;
    let subarray = [];

    for (let i = 0; i < Math.ceil(array.length / size); i++) {
        subarray[i] = array.slice(i * size, i * size + size);
    }

    return subarray;
}

function addListeners() {
    document
        .querySelector("#getEvents")
        .addEventListener("click", getAllEvents);

    document.querySelector("#search").addEventListener("click", showSearchPage);

    document
        .querySelector("#showProfile")
        .addEventListener("click", showProfileInfo);
}

async function getAllEvents() {
    const url = "/events";

    const response = await fetch(url);
    const events = await response.json();

    drawEvents(events);
}

function drawEvents(events, isMenu = false) {
    const pageContent = document.querySelector("#content__content");
    pageContent.innerHTML = '';

    if (!isMenu)
        document.querySelector('#content__menu').innerHTML = '';

    const zippedEvents = devideToSubarrays(events);

    zippedEvents.forEach(pack => {
        const row = document.createElement("div");
        row.classList.add("row", 'justify-content-around', 'mt-3');

        pack.forEach(event => {
            const card = document.createElement("div");
            card.classList.add("col-md-3", "card", "mr-1");

            const eventTemplate = createCardTemplate(event);
            card.innerHTML = eventTemplate;

            const followButton = document.createElement("button");
            followButton.classList.add("btn", "btn-primary");
            followButton.dataset.id = event.id;
            followButton.addEventListener("click", subscribeToEvent);
            followButton.textContent = "Присоедениться";

            card.appendChild(followButton);
            row.appendChild(card);
        });

        pageContent.appendChild(row);
    });
}

async function subscribeToEvent() {
    const userId = sessionStorage.getItem("id");
    const eventId = this.dataset.id;
    const url = `/events/${eventId}/${userId}`;

    const response = await fetch(url, { method: "POST" });

    if (response.status == 200 || response.status == 201)
        alert("Вы подписаны на событие!");
    else 
        alert("Произшла ошибка!");
}

function createCardTemplate(event) {
    return `
    <p>Название: ${event.name}</p>
    <p>Максимальное количество участников: ${event.maxParticipants}</p>
    <p>Расположение: ${event.location}</p>
    <p>Дата: ${event.beginDate}</p>
    <p>Продолжительность: ${event.duration}</p>
    <p>Описание: ${event.description}</p>
    `;
}

function showSearchPage() {
    const pageContent = document.querySelector("#content__menu");
    pageContent.innerHTML = searchPageTemplate;

    document.querySelector("#content__content").innerHTML = '';

    const searchForm = document.querySelector("#searchForm");
    searchForm.addEventListener("submit", search);
}

async function search(event) {
    event.preventDefault();

    const searchValue = document.querySelector("#searchInput").value;
    const searchCategoty = Object.fromEntries(new FormData(event.target).entries()).category;
    
    const url = `/${searchCategoty}/search?name=${searchValue}`

    const response = await fetch(url);
    const data = await response.json();

    if (searchCategoty === 'events')
        drawEvents(data, true)
    else
        drawUsers(data);
}

function showProfileInfo() {
    const pageContent = document.querySelector("#page-content");

    pageContent.textContent = `Макет профиля`;
}

window.onload = () => {
    addListeners();
    getAllEvents();
};
