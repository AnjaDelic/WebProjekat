$(document).ready(function () {
    let uloga = localStorage.getItem('uloga');
    if(uloga == null){
        localStorage.setItem('uloga', 0);
    }
    if(uloga == 0){
        alert(" Nemate pristup ovoj stranici!");
        window.location.href = "index.html";

    }
    if(uloga == 1){ //trener
        alert("Nemate pristup ovoj stranici !");
        window.location.href = "index.html";
        //window.location.href = "trener.html";

    }
    if(uloga == 2){ //clan
        alert(" Nemate pristup ovoj stranici!");
        //window.location.href="clan.html";
    }
});

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
             
                let btn = "<button class='btn-danger' data-id=" + fc.id + ">Obrisi</button>";
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

$(document).on('click', '.btn-danger', function () {

    let id=this.dataset.id;

    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/FC/delete/" + id ,
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