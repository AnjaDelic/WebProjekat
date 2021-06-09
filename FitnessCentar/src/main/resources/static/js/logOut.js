$(document).on('click', ".logOut", function (event) {
    event.preventDefault();
    

    localStorage.setItem('uloga', '0');
    localStorage.setItem('id', '0');

    window.location.href="index.html"





 });