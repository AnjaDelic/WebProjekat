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
        window.location.href = "trener.html";

    }
    if(uloga == 3){ //admin
        alert(" Nemate pristup ovoj stranici!");
        window.location.href="admin.html";
    }
});


$(document).on("submit", "#forma", function (event) {
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
        let active=true;
     
        if (password !== passwordREP) {
            $('.alert-danger').show();
            return;
        }

        if(name==""){name=null;}
        if(surname==""){surname=null;}
        if(birthday==""){birthday=null;}
        if(username==""){username=null;}
        if(password=="" ){password=null; passwordREP=null;}
        if(email==""){email=null;}
        if(phone==""){phone=null;}

        let noviKorisnik = {
            name,
            surname,
            birthday,
            username,
            password,
            email,
            phone,
            vrati,
            active
        }
        let id = localStorage.getItem('id');
   
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/Clan/put/"+id,                 // URL na koji saljem,odg na post metodu
            dataType: "json",                                           // povratna vrednost
            contentType: "application/json",                            //saljemo
            data: JSON.stringify(noviKorisnik),                                //  pretvara JavaScript objekat u JSON
            success: function (response) {
                console.log(response);

                if(response.vrati===0){
                    alert("Uspešno ste azurirali profil");
                    window.location.href="clan.html";
                }
                else {
                    alert("Greska!");
                }
                
                

            },
            error: function () {
                alert("ERROR:Molimo proverite da li su podaci koje ste uneli validni");
            }
        });


});