//OVDE MI FALI PROVERA DA LI SU PSW I PSW REP ISTI

$(document).on("submit", "#signup", function (event) {
    event.preventDefault();

    let name = $("#inputName").val();
    let surname = $("#inputSurname").val();
    let birthday = $("#rodjendan").val();
    let username = $("#inputUsername").val();
    let password = $("#inputPassword4").val();
    let passwordREP = $("#psw-repeat").val();
    let email = $("#inputEmail4").val();
    let phone = $("#inputNumber").val();
    let uloga = document.forms['signup'].uloga.value;
    //pravim objekat koji cu proslediti na backend
    //NAZIVI DA SE POKLAPAJU
    if (password !== passwordREP) {
        $('.alert-danger').show();
        return;
    }
    let noviKorisnik = {
        name,
        surname,
        username,
        password,
        email,
        phone,
        birthday
    }



    if(uloga==="trener"){
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/trener/post",                 // URL na koji saljem,odg na post metodu
            dataType: "json",                                           // povratna vrednost
            contentType: "application/json",                            //saljemo
            data: JSON.stringify(noviKorisnik),                                //  pretvara JavaScript objekat u JSON
            success: function (response) {
                console.log(response);


                alert("CESTITAMO:Vasa registracija je primljena! Nakon sto administrator odobri Vas zahtev imacete pristup sajtu");
                window.location.href="index.html";

            },
            error: function () {
                alert("ERROR:Molimo proverite da li su podaci koje ste uneli validni");
            }
        });
    }
    else{



        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/Clan/post",                 // URL na koji saljem,odg na post metodu
            dataType: "json",                                           // povratna vrednost
            contentType: "application/json",                            //saljemo
            data: JSON.stringify(noviKorisnik),                                //  pretvara JavaScript objekat u JSON
            success: function (response) {
                console.log(response);
                alert("Uspešno ste se registrovali!");
                localStorage.setItem('uloga','clan');
                var index=response.id;
                localStorage.setItem('id',index);
                //window.location.href="";

            },
            error: function () {
                alert("ERROR:Molimo proverite da li su podaci koje ste uneli validni");
            }
        });



    }





});

