$(document).ready(function () {   
    let id = localStorage.getItem('id');
    localStorage.removeItem('idPass');
    $.ajax({
        type: "GET",                                               
        url: "http://localhost:8080/api/termin/getOdradjeni/"+id,                 
        dataType: "json",                                           
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

                $('#odradjeni').append(row);                       
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

    let id = localStorage.getItem('id');
    ocena = document.forms['tr'].inputOcena.value;

    if(ocena==="ocenjeni"){
    $.ajax({
        type: "POST",                                               
        url: "http://localhost:8080/pretragaOcenjenih/"+ id,                
        dataType: "json",                                        
       
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

                $('#odradjeni').append(row);                       
            }

        },
        error: function (response) {                               
            console.log("ERROR:\n", response);
        }


    }); }
    else if(ocena==="neocenjeni"){
        $.ajax({
            type: "POST",                                               
            url: "http://localhost:8080/pretragaNeocenjenih/"+ id,                
            dataType: "json",                                        
          
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


                    let btn1 = "<button id='dugmeOcena' class='btn btn-primary' data-id=" + tr.id + ">Ocenite trening</button>";
                    row += "<td>" + btn1 + "</td>";
                   
            
                    row += "</tr>";                                     
    
                    $('#odradjeni').append(row);                       
                }
               
    
            },
            error: function (response) {                               
                console.log("ERROR:\n", response);
            }
    
    
        });
    }

    else{
        $.ajax({
            type: "GET",                                               
            url: "http://localhost:8080/api/termin/getOdradjeni/"+id,                 
            dataType: "json",                                           
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
    
                    $('#odradjeni').append(row);                       
                }
            },
            error: function (response) {                               
                console.log("ERROR:\n", response);
            }
        });
    }

});


$(document).on('click', '#dugmeOcena', function () {  
    let id=this.dataset.id;
    localStorage.setItem('idTermina',id);
    window.location.href="ocena.html";

});