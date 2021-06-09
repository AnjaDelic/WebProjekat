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
        //window.location.href = "trener.html";
        
    }
    if(uloga == 2){ //clan
        alert(" Nemate pristup ovoj stranici!");
        //window.location.href="clan.html";
    }
 });

    $(document).on("submit", "#dodajFC", function (event) {
    event.preventDefault();

    let naziv = $("#naziv").val();
    let adresa = $("#adresa").val();
    let broj = $("#broj").val();
    let email = $("#email").val();
    //pravim objekat koji cu proslediti na backend
    //NAZIVI DA SE POKLAPAJU

    let noviFC = {
        naziv,
        adresa,
        broj,
        email
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/FC/post",                 // URL na koji saljem,odg na post metodu
        dataType: "json",                                           // povratna vrednost
        contentType: "application/json",                            //saljemo
        data: JSON.stringify(noviFC),                                //  pretvara JavaScript objekat u JSON
        success: function (response) {
            console.log(response);


            alert("FC je uspešno kreiran!");
            window.location.href="admin.html";

        },
        error: function () {
            alert("Greška prilikom kreiranja FC!!!");
        }
    });


});




