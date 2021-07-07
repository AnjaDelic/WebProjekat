
$(document).on('click', '#zakazi', function () {  
    let id=this.dataset.id; //ID TERMINA
    localStorage.setItem('idTermina',id);
    
    let idClana = localStorage.getItem('id'); //id clana

    let posalji={
        idClana
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/termin/prijava/"+id ,                 
        dataType: "json",                                           
        contentType: "application/json",                            
        data: JSON.stringify(posalji),                               
        success: function (response) {
            console.log(response);

            if(response.vrati=="0"){alert("Uspesno prijavili trening");  window.location.href="clan.html";}
            if(response.vrati=="1"){alert("Vec ste prijavili trening");}
            if(response.vrati=="2"){alert("Trenutno nema mesta");}

           

        },
        error: function () {
            console.log(response);
            alert("Greška!");
        }
    });


});
