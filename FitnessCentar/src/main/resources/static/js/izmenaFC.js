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

    $(document).on("submit", "#izmeniFC", function (event) {
    event.preventDefault();

    let naziv = $("#naziv").val();
    let adresa = $("#adresa").val();
    let broj = $("#broj").val();
    let email = $("#email").val();
    //pravim objekat koji cu proslediti na backend
    //NAZIVI DA SE POKLAPAJU
    
    if(naziv==""){naziv=null;}
    if(adresa==""){adresa=null;}
    if(broj==""){broj=null;}
    if(email==""){email=null;}

    let noviFC = {
        naziv,
        adresa,
        broj,
        email
    }
    let id = localStorage.getItem('idFC');
    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/FC/put/"+id ,                 // URL na koji saljem,odg na post metodu
        dataType: "json",                                           // povratna vrednost
        contentType: "application/json",                            //saljemo
        data: JSON.stringify(noviFC),                                //  pretvara JavaScript objekat u JSON
        success: function (response) {
            console.log(response);


            alert("FC je uspešno izmenjen!");
            window.location.href="admin.html";

        },
        error: function () {
            console.log(response);
            alert("Greška prilikom izmene FC!!!");
        }
    });


});




