$(document).ready(function () {
    let uloga = localStorage.getItem('uloga');
    if(uloga == null){
        localStorage.setItem('uloga', 0);
    }
    if(uloga == 0){
        alert(" Nemate pristup ovoj stranici!");
        window.location.href = "index.html";

    }
    if(uloga == 1){ //trener
        alert("Nemate pristup ovoj stranici !");
        window.location.href = "index.html";
        window.location.href = "trener.html";

    }
    if(uloga == 2){ //clan
        alert(" Nemate pristup ovoj stranici!");
        window.location.href="clan.html";
    }
});