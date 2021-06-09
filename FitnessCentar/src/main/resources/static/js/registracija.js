
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
    let vrati= 0;
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
        birthday,
        vrati
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
                if(response.vrati===0){
                    alert("CESTITAMO:Vasa registracija je primljena! Nakon sto administrator odobri Vas zahtev imacete pristup sajtu");
                }
                else {
                    alert("Greska!");
                }
                
                localStorage.setItem('uloga','1');
                var index=response.id;
                localStorage.setItem('id',index);

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

                if(response.vrati===0){
                    alert("Uspešno ste se registrovali!");
                }
                else {
                    alert("Greska!");
                }
                
                localStorage.setItem('uloga','2');
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

