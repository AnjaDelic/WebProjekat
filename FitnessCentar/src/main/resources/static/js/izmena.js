$(document).ready(function () {   
    let idFC = localStorage.getItem('idFC');
    

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


                $('#inputNazivT').append(row);                       
            }
        },
        error: function (response) {                               
            console.log("ERROR:\n", response);
        }
    });
});


$(document).on('click', "#izmeni", function (event) {
    event.preventDefault();

     
        let idFC = localStorage.getItem('idFC');
        let id = localStorage.getItem('id');
        let idTermina=localStorage.getItem('idTermin');
        
        let naziv = $("#inputNazivT").val();
       let vrati="";

       if(idTermina==null)
       {
           alert("idTermina nije def");
           return;
       }

       if(idFC==null)
       {
           alert("idFC nije def");
           return;
       }
       
        let noviRaspored = {
           
           naziv,
           idFC,
           idTermina,
           vrati
        
        }
       
   
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/termin/putNoviRaspored/"+id,                
            dataType: "json",                                           
            contentType: "application/json",                            
            data: JSON.stringify(noviRaspored),                                
            success: function (response) {
                console.log(response);
                if(vrati==="ne"){ alert("Trajanje odabranog treninga je duze od zauzetog termina"); return;}

           
               alert("Uspešno ste promenili raspored za odabrani termin");
                window.location.href="trener.html";
               
                

            },
            error: function () {
                alert("ERROR:Molimo proverite da li su podaci koje ste uneli validni");
            }
        });


});