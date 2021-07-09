
$(document).ready(function () {
    let id = localStorage.getItem('id');

    if(id==null)
        {
            alert("idTrenera nije def");
            return;
        }
 
    $.ajax({
        type: "GET",                                                // HTTP metoda
        url: "http://localhost:8080/api/trener/get/" +id,                 // URL koji se gađa
        dataType: "json",                                           // tip povratne vrednosti
        success: function (response) {                              // ova f-ja se izvršava posle uspešnog zahteva
            console.log("SUCCESS:\n", response);                    // ispisujemo u konzoli povratnu vrednost radi provere

            let row = "<tr>";                                  
            row += "<td>" + response.name+ "</td>";  

            row += "<td>" + response.surname + "</td>";

            row += "<td>" + response.username + "</td>";

            row += "<td>" + response.email + "</td>";

            row += "<td>" + response.phone + "</td>";

            row += "<td>" + response.birthday + "</td>";

            row += "<td>" + response.prosek + "</td>";
        
            
               
            row += "</tr>";                                     
            localStorage.setItem('idFC',response.idFC);
            $('#tabela').append(row); 
       
              
        },
        error: function (response) {                                // ova f-ja se izvršava posle neuspešnog zahteva
            console.log("ERROR:\n", response);
        }
    });



 });

 $(document).on('click', '#trening ', function () {  
    
    window.location.href="noviTrening.html";
    

});

$(document).on('click', '#termin ', function () {  
    
    window.location.href="noviTermin.html";
    

});

$(document).on('click', '#raspored ', function () {  
    
    window.location.href="treninziTrener.html";
    

});
