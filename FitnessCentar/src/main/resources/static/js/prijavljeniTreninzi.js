$(document).ready(function () {   
    let id = localStorage.getItem('id');
   
    $.ajax({
        type: "GET",                                               
        url: "http://localhost:8080/api/termin/getPrijavljeni/"+id,                 
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

                let btn1 = "<button id='dugmeOdjava' class='btn-danger' data-id=" + tr.id + ">Odjavite prijavljen trening</button>";
                row += "<td>" + btn1 + "</td>";
                                     
        
                row += "</tr>";                                     

                $('#prijavljeni').append(row);                       
            }
        },
        error: function (response) {                               
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '#dugmeOdjava', function () {  
    let id=this.dataset.id; //id termina
    localStorage.setItem('idTermina',id);
    
    let idClana = localStorage.getItem('id'); //id clana

    let posalji={
        idClana
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/termin/prijavljeniPut/"+id ,                 // URL na koji saljem,odg na post metodu
        dataType: "json",                                           // povratna vrednost
        contentType: "application/json",                            //saljemo
        data: JSON.stringify(posalji),                                //  pretvara JavaScript objekat u JSON
        success: function (response) {
            console.log(response);

            alert("Uspesno odjavljen trening");
            window.location.href="clan.html";

        },
        error: function () {
            console.log(response);
            alert("Greška!");
        }
    });


});
