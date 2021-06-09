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

   let clear=$("#treninzi2");
    clear.empty();

    naziv = document.forms['tr'].inputNaziv.value;
   
    opis = document.forms['tr'].inputOpis.value;
   
    tip = document.forms['tr'].inputTip.value;
    let cena=0;
    cena = document.forms['tr'].inputCenaOd.value;
    let trajanje=0;
    trajanje = document.forms['tr'].inputTrajanjeOd.value;
   
    let pocetak = $("#inputPocetak").val();
    let kraj = $("#inputKraj").val();
   
 


    if(cena ==="" || isNaN(cena)){ cena=10000; } //nije nis stavio ili nije stavio br

    if(trajanje==="" || isNaN(trajanje)){ trajanje=90000;}

    if(kraj ===""){kraj="2025-09-09";}
   
    if(pocetak===""){pocetak="2000-09-09";}
    

    
    let pretraga = {
        naziv,
        opis,
        tip,
        cena,
       trajanje,
       pocetak,
       kraj
       
    }

    console.log(pretraga);
    $.ajax({
        type: "POST",                                               
        url: "http://localhost:8080/api/termin/pretraga",                
        dataType: "json",                                        
        contentType: "application/json",   
        data: JSON.stringify(pretraga),  
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