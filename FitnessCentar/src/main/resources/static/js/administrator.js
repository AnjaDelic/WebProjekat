
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
            
                alert("Uspešna prijava!");
                localStorage.setItem('uloga','1');
                var index=response.id;
                localStorage.setItem('id',index);
                window.location.href="trener.html";
                
            },
            error: function (res) { 
                //console.log(res);                                      
                alert("Greška!");
            }
        });
    }

    if(uloga === "clan") {
       // var ulogaa = 2;
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
                                                    
                alert("Greška!");
            }
        });

       
    }
    
   
});

/*$(document).ready(function () {
    let uloga = localStorage.getItem("uloga");
    if(uloga == null){
       localStorage.setItem("uloga", 0);
    }
    if(uloga == 1){
        window.location.href = "";
        alert("Vec ste prijavljeni!");
    }
    if(uloga == 2){
        window.location.href = "";
        alert("Vec ste prijavljeni!");
    }
    if(uloga == 3){
        window.location.href = "..admin.html";
        alert("Vec ste prijavljeni!");
    }
});*/