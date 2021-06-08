$(document).ready(function () {   
    $.ajax({
        type: "GET",                                                // HTTP metoda
        url: "http://localhost:8080/api/FC",                 // URL koji se gađa
        dataType: "json",                                           // tip povratne vrednosti
        success: function (response) {                              
            console.log("SUCCESS:\n", response);                    

            for (let fc of response) {                        
                let row = "<tr>";                                  
                row += "<td>" + fc.naziv+ "</td>";       
                row += "<td>" + fc.adresa + "</td>";
                row += "<td>" + fc.broj + "</td>";
                row += "<td>" + fc.email + "</td>";
             
                let btn = "<button class='btnSeeMore' data-id=" + fc.id + ">See More</button>";
                row += "<td>" + btn + "</td>";                      
        
                row += "</tr>";                                     

                $('#fcs').append(row);                       
            }
        },
        error: function (response) {                               
            console.log("ERROR:\n", response);
        }
    });
});