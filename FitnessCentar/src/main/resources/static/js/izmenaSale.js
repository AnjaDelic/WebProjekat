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

    $(document).on("submit", "#izmenaSale", function (event) {
    event.preventDefault();

    let kapacitet = $("#kapacitet").val();
    let oznaka = $("#oznaka").val();

    if(kapacitet==""){kapacitet= -1; }
    if(oznaka==""){oznaka=null;}
   
    //pravim objekat koji cu proslediti na backend
    //NAZIVI DA SE POKLAPAJU

    let izmena = {
        kapacitet,
        oznaka,
        
    }
    let id = localStorage.getItem('idSale');
    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/sala/put/"+id ,                 // URL na koji saljem,odg na post metodu
        dataType: "json",                                           // povratna vrednost
        contentType: "application/json",                            //saljemo
        data: JSON.stringify(izmena),                                //  pretvara JavaScript objekat u JSON
        success: function (response) {
            console.log(response);


            alert("Sala je uspešno izmenjena!");
            window.location.href="admin.html";

        },
        error: function () {
            alert("Greška prilikom izmene!!!");
        }
    });


});




