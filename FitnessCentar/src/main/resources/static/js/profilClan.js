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
        window.location.href = "trener.html";

    }
    if(uloga == 3){ //admin
        alert(" Nemate pristup ovoj stranici!");
        window.location.href="admin.html";
    }
});


$(document).ready(function () {
    let id = localStorage.getItem('id');

    $.ajax({
        type: "GET",                                                // HTTP metoda
        url: "http://localhost:8080/api/Clan/get/" +id,                 // URL koji se gađa
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

            row += "<td>" + response.password + "</td>";
            
            
            let btn = "<button id='izmenaProfila' class='btnSeeMore' data-id=" + response.id + ">Izmeni profil</button>";
            row += "<td>" + btn + "</td>";  
               
            row += "</tr>";                                     

            $('#tabela').append(row); 
       
              
        },
        error: function (response) {                                // ova f-ja se izvršava posle neuspešnog zahteva
            console.log("ERROR:\n", response);
        }
    });




 });

 $(document).on('click', '#izmenaProfila', function () {  
    let id=this.dataset.id;
    localStorage.setItem('id',id);
    window.location.href="izmenaClan.html";
    

});
