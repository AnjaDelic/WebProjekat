
$(document).on('click', '.btnSeeMore', function () {   
      
    let sviDiv = $("#svi");                     
    sviDiv.hide(); 

    let id=this.dataset.id;
    
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/FC/get/" + id ,
        data: JSON.stringify(id),
        success: function (response) {
            console.log("SUCCESS:\n", response);

                let row = "<tr>";                                  
                row += "<td>" + response.naziv+ "</td>";  
              
                row += "<td>" + response.adresa + "</td>";
             
                row += "<td>" + response.broj + "</td>";
              
                row += "<td>" + response.email + "</td>";
                let btn = "<button id='izmenaFC' class='btnSeeMore' data-id=" + response.id + ">Izmeni</button>";
                row += "<td>" + btn + "</td>";  
                   
                let btn1 = "<button id='dodaj' class='btn-success' data-id=" + response.id + ">Dodaj novu salu</button>";
                row += "<td>" + btn1 + "</td>"; 
        
                row += "</tr>";                                     

                $('#fcs1').append(row); 
           
                let detailsDiv = $("#details");               
                detailsDiv.show();   
              
           

        },
        error: function (response) {
            console.log("ERROR:\n", response);
            
        }

    });

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/FC/getSale/" + id ,
        data: JSON.stringify(id),
        success: function (response1) {
            console.log("SUCCESS:\n", response1);

            for (let sale of response1) {                        
                let row = "<tr>";                                  
                row += "<td>" + sale.kapacitet+ "</td>";      
                row += "<td>" + sale.oznaka + "</td>";
                let btn1 = "<button id='izmenaSale' class='btnSeeMore' data-id=" + sale.id + ">Izmeni</button>";
                row += "<td>" + btn1 + "</td>";
                row += "<td>" + sale.broj + "</td>";         
                btn = "<button  class='btn-danger' data-id=" + sale.id + ">Odbisi salu</button>";
                row += "<td>" + btn + "</td>";
                              
        
                row += "</tr>";                                     

                $('#sale').append(row);                       
            }
           
                let detailsDiv = $("#details");               
                detailsDiv.show();   
              
           

        },
        error: function (response) {
            console.log("ERROR:\n", response);
            
        }

    });



});

$(document).on('click', '#izmenaFC', function () {  
    let id=this.dataset.id;
    localStorage.setItem('idFC',id);
    window.location.href="izmenaFC.html";

});

$(document).on('click', '#izmenaSale', function () {  
    let id=this.dataset.id;
    localStorage.setItem('idSale',id);
    window.location.href="izmenaSale.html";

});

$(document).on('click', "#dodaj", function () {
  
    let id=this.dataset.id;
    localStorage.setItem('idFC',id);
    window.location.href="dodavanjeSale.html";

});


$(document).on('click', '.btn-danger', function () {

    let id=this.dataset.id;

    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/sala/delete/" + id ,
        data: JSON.stringify(id),
        success: function (response) {
            console.log("SUCCESS:\n", response);
            alert("Uspeh");
            $('[data-id="' + id + '"]').parent().parent().remove();

        },
        error: function (response) {
            console.log("ERROR:\n", response);
            alert("Greska");
        }

    });


});