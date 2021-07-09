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

   
});


$(document).on('click', "#button", function (event) {
    event.preventDefault();

     
        let idFC = localStorage.getItem('idFC');
        let id = localStorage.getItem('id');
        
        let cena = $("#inputCena").val();
        let pocetak = $("#inputPocetak").val();
        let kraj = $("#inputKraj").val();
        let oznaka = $("#inputSale").val();
        let naziv = $("#inputName").val();
        let tip = $("#inputTip").val();
        let trajanje = $("#inputTrajanje").val();
        let opis = $("#inputOpis").val();


        if(cena=="" || pocetak=="" || kraj==""){alert("Unesite trazene podatke"); return;}
       
       
        let noviTrening = {
           pocetak,
           kraj,
           cena,
           naziv,
           oznaka,
           tip,
           trajanje,
           opis,
           idFC
        
        }
       
   
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/termin/putNoviTrening/"+id,                 // URL na koji saljem,odg na post metodu
            dataType: "json",                                           // povratna vrednost
            contentType: "application/json",                            //saljemo
            data: JSON.stringify(noviTrening),                                //  pretvara JavaScript objekat u JSON
            success: function (response) {
                console.log(response);

           
               alert("Uspešno ste dodali trening");
                window.location.href="trener.html";
               
                

            },
            error: function () {
                alert("ERROR:Molimo proverite da li su podaci koje ste uneli validni");
            }
        });


});