$(document).ready(function () {   
    let idFC = localStorage.getItem('idFC');
    
    $.ajax({
        type: "GET",                                               
        url: "http://localhost:8080/api/sala/"+idFC,                 
        dataType: "json",                                           
        success: function (response) {                              
            console.log("SUCCESS:\n", response);                    

            for (let tr of response) {                        
                let row = "<option>";                                  
                row +=  tr.oznaka ;       
                
                row += "</option>";                                     

                $('#inputSale').append(row);                       
            }
        },
        error: function (response) {                               
            console.log("ERROR:\n", response);
        }
    });

    $.ajax({
        type: "GET",                                               
        url: "http://localhost:8080/api/trening/"+idFC,                 
        dataType: "json",                                           
        success: function (response) {                              
            console.log("SUCCESS:\n", response);                    

            for (let tr of response) {                        
                let row = "<option>";                                  
                row +=  tr.naziv ;       
                
                row += "</option>";                                     

                $('#inputTreninzi').append(row);                       
            }
        },
        error: function (response) {                               
            console.log("ERROR:\n", response);
        }
    });
});


$(document).on('click', '.signupbtn', function (event) {
    event.preventDefault();

     
        let id = localStorage.getItem('id');
  
        
        let cena = $("#inputCena").val();
        let pocetak = $("#inputPocetak").val();
        let kraj = $("#inputKraj").val();
        let oznaka = $("#inputSale").val();
        let naziv = $("#inputTreninzi").val();
  
       
        let noviTermin = {
           pocetak,
           kraj,
           cena,
           naziv,
           oznaka,
       
           
        }
       
   
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/termin/put/"+id,                 // URL na koji saljem,odg na post metodu
            dataType: "json",                                           // povratna vrednost
            contentType: "application/json",                            //saljemo
            data: JSON.stringify(noviTermin),                                //  pretvara JavaScript objekat u JSON
            success: function (response) {
                console.log(response);

           
               alert("Uspešno ste dodali termin");
                window.location.href="trener.html";
               
                

            },
            error: function () {
                alert("ERROR:Molimo proverite da li su podaci koje ste uneli validni");
            }
        });


});