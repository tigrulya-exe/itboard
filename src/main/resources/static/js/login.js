function addListeners() {
    console.log('*');

    document.querySelector('#loginForm')
        .addEventListener('submit', login);
}

async function login(event) {
    event.preventDefault();

    const loginField = document.querySelector('#username');
    const login = loginField.value;
    const url = `/login/${login}`;

    const response = await fetch(url);
    const data = await response.text();

    sessionStorage.setItem('id', data);
    
    document.location.href = '/menu.html';
}

window.onload = () => {
    addListeners();
}
