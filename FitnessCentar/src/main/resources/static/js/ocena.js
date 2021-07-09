$(document).ready(function() {
    const btn = document.querySelector(".submit-rating");
    const thanksmsg = document.querySelector(".thanks-msg");
    const starRating = document.querySelector(".star-input");
    // Success msg show/hide
    btn.onclick = () => {
      
        
        
     ocene = document.forms['z'].rating.value;
  
    var ocena;
    if(ocene=='5'){ocena=5.0;}
    if(ocene=='4'){ocena=4.0;}
    if(ocene=='3'){ocena=3.0;}
    if(ocene=='2'){ocena=2.0;}
    if(ocene=='1'){ocena=1.0;}
    if(ocene==undefined){ocena=0.0;}


    let idClan = localStorage.getItem('id');
    let idTermin = localStorage.getItem('idTermina');
    
    let novaOcena = {
        ocena,
        idClan,
        idTermin
       
    }


    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/ocena/put",
        dataType: "json",                                           
        contentType: "application/json",                           
        data: JSON.stringify(novaOcena),                               
        success: function (response) {
            console.log(response);

            alert("Hvala na oceni i poverenju");
           window.location.href="clan.html"; 
           return ;

        },
        error: function () {
            alert("ERROR");
       
        }
    });

    starRating.style.display = "none";
     thanksmsg.style.display = "table";
    return false;
     
        
    };
  


});






    

