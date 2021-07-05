

$(document).on("submit", "form", function (event) {  
    event.preventDefault(); 
    let username = document.forms['loginform'].username.value;
    let password = document.forms['loginform'].password.value;
    let uloga = document.forms['loginform'].uloga.value;
    
    let prijava = {
        username,
        password,
        
       
    }

  if(uloga === "trener") {
        //var ulogaa = 1;

        console.log(username);
        console.log(password);
        console.log(uloga);
        
        $.ajax({
            type: "POST",                                               
            url: "http://localhost:8080/api/trener/login" ,             
            dataType: "json",                                          
            contentType: "application/json",                            
            data: JSON.stringify(prijava),                          
            success: function (response) {                                   
                console.log(response);
               
                if(response.active==false){
                    alert("Nije vam jos odobren profil");
                    window.location.href="index.html";
                }
                else{

                alert("Uspešna prijava!");
                localStorage.setItem('uloga','1');
                var index=response.id;
                localStorage.setItem('id',index);
                //window.location.href="trener.html";
                    }
                
            },
            error: function (res) { 
               // console.log(response); POSLE KOMITA SAM SAMO OVO ZAKOMENTARISALA
                alert("ERROR:Molimo proverite da li su podaci koje ste uneli validni");
            }
        });
    }

    
    if(uloga === "clan") {
        // var ulogaa = 2;
        $.ajax({
         type: "POST",                                               
         url: "http://localhost:8080/api/Clan/login" ,             
         dataType: "json",                                          
         contentType: "application/json",                            
         data: JSON.stringify(prijava),                          
         success: function (response) {                                   
             console.log(response);
         
             alert("Uspesna prijava!");
             localStorage.setItem('uloga','2');
             var index=response.id;
             localStorage.setItem('id',index);
             //window.location.href="clan.html";
             
         },
         error: function (res) { 
           // console.log(response);                               
             alert("ERROR:Molimo proverite da li su podaci koje ste uneli validni");
         }
     });
 
     }

    if(uloga === "admin") {
       // var ulogaa = 3;

        console.log(username);
        console.log(password);
        console.log(uloga);

        $.ajax({
            type: "POST",                                               
            url: "http://localhost:8080/api/administrator/login" ,             
            dataType: "json",                                          
            contentType: "application/json",                            
            data: JSON.stringify(prijava),                          
            success: function (response) {                                   
                console.log(response);
            
                alert("Uspešna prijava!");
                localStorage.setItem('uloga','3');
                var index=response.id;
                localStorage.setItem('id',index);
                window.location.href="admin.html";
                
            },
            error: function (res) { 
               // console.log(response);                             
                alert("ERROR:Molimo proverite da li su podaci koje ste uneli validni");
            }
        });

       
    }
    
   
});

