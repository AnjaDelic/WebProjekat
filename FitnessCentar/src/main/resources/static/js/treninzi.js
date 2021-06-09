$(document).ready(function () {   
    $.ajax({
        type: "GET",                                                // HTTP metoda
        url: "http://localhost:8080/api/termin",                 // URL koji se gađa
        dataType: "json",                                           // tip povratne vrednosti
        success: function (response) {                              
            console.log("SUCCESS:\n", response);                    

            for (let tr of response) {                        
                let row = "<tr>";                                  
                row += "<td>" + tr.naziv+ "</td>";       
                row += "<td>" + tr.opis + "</td>";
                row += "<td>" + tr.tip + "</td>";
                row += "<td>" +  tr.trajanje + "</td>";
                row += "<td>" +  tr.cena + "</td>";
                row += "<td>" +  tr.pocetak + "</td>";
                row += "<td>" +  tr.kraj + "</td>";
                                     
        
                row += "</tr>";                                     

                $('#treninzi').append(row);                       
            }
        },
        error: function (response) {                               
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on("submit", "#sviTreninzi", function (event) {  
    event.preventDefault(); 

    
    naziv = document.forms['tr'].inputNaziv.value;
   
    opis = document.forms['tr'].inputOpis.value;
   
    tip = document.forms['tr'].inputTip.value;
    let cena=0;
    cena = document.forms['tr'].inputCenaOd.value;
    let trajanje=0;
    trajanje = document.forms['tr'].inputTrajanjeOd.value;
   
    let pocetak = $("#inputPocetak").val();
    let kraj = $("#inputKraj").val();
   
    let kriterijum=0;

   

    if(naziv!="Izaberite..."){kriterijum =1;}

    if(opis!="Izaberite..."){kriterijum =2;}

    if(tip!=""){kriterijum =3;}

    if(cena!=""){ kriterijum =4; }

    if(trajanje!=""){ kriterijum =5;}

    if(pocetak!=""){kriterijum=6; }
   
    if(kraj!=""){kriterijum=7;}
    

    
    let pretraga = {
        naziv,
        opis,
        tip,
        cena,
        kriterijum,
       trajanje,
       pocetak,
       kraj
       
    }

    $.ajax({
        type: "GET",                                               
        url: "http://localhost:8080/api/termin/pretraga",                
      
        data: JSON.stringify(pretraga),  
        success: function (response) {                              
            console.log("SUCCESS:\n", response);                    

        },
        error: function (response) {                               
            console.log("ERROR:\n", response);
        }


    });

});