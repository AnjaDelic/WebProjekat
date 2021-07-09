
$(document).on("submit", "#forma", function (event) {
    event.preventDefault();

        let id = localStorage.getItem('id');
          let pass = localStorage.getItem('idPass');
        let name = $("#inputName").val();
        let surname = $("#inputSurname").val();
        let birthday = $("#rodjendan").val();
        let username = $("#inputUsername").val();
        let password = $("#inputPassword4").val();
        let old = $("#inputPassword").val();
        let passwordREP = $("#psw-repeat").val();
        let email = $("#inputEmail4").val();
        let phone = $("#inputNumber").val();
        let vrati= 0;
        let active=true;
     
        if (password !== passwordREP) {
            $('.alert-danger').show();
            return;
        }
        if(old!==pass){
             $('.alert-warning').show();
            return;}

        if(name==""){name=null;}
        if(surname==""){surname=null;}
        if(birthday==""){birthday=null;}
        if(username==""){username=null;}
        if(password=="" ){password=null; passwordREP=null; }  
        if(email==""){email=null;}
        if(phone==""){phone=null;}
        if(old=="" && password!==""){alert("Unesite staru lozinku prvo"); return;}
       
       
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