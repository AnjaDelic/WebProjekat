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
        //window.location.href = "trener.html";
        
    }
    if(uloga == 2){ //clan
        alert(" Nemate pristup ovoj stranici!");
        //window.location.href="clan.html";
    }
 });


$(document).on("submit", "#dodavanjeTrenera", function (event) {
    event.preventDefault();

    let name = $("#inputName").val();
    let surname = $("#inputSurname").val();
    let birthday = $("#rodjendan").val();
    let username = $("#inputUsername").val();
    let password = $("#inputPassword4").val();
    let passwordREP = $("#psw-repeat").val();
    let email = $("#inputEmail4").val();
    let phone = $("#inputNumber").val();
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


    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/trener/postAdmin",
        dataType: "json",                                           
        contentType: "application/json",                           
        data: JSON.stringify(noviKorisnik),                               
        success: function (response) {
            console.log(response);
            if(response.vrati===0){
                alert("CESTITAMO:Vasa registracija je primljena! ");
            }
            else {
                alert("Greska prilikom unosa podataka!"); 
            }
            
            window.href="admin.html";


        },
        error: function () {
            alert("ERROR:Molimo proverite da li su podaci koje ste uneli validni");
        }
    });

});