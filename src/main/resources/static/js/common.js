
window.onload = function() {
    // 햄버거 메뉴
    var burger = document.querySelector('.navbar-burger');
    var menu = document.querySelector('.navbar-menu');
    burger.addEventListener('click', function() {
        burger.classList.toggle('is-active');
        menu.classList.toggle('is-active');
    });
}

function alertLogin() {
    alert('로그인 후 이용하실 수 있습니다.');
}

