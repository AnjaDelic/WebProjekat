$(document).ready(function () {   
  localStorage.removeItem('idPass'); 
    $.ajax({
        type: "GET",                                                // HTTP metoda
        url: "http://localhost:8080/api/termin/slobodni",                 // URL koji se gađa
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
                row+="<td>" +  tr.br + "</td>";
                
                let btn1 = "<button id='zakazi' class='btn-success' data-id=" + tr.id + ">Prijavi se</button>";
                row += "<td>" + btn1 + "</td>"; 

                let btn2 = "<button id='info' class='btn-info' data-id=" + tr.naziv + ">Detalji treninga</button>";
                row += "<td>" + btn2 + "</td>"; 
        
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

    id=localStorage.getItem('idTermin');
    naziv = document.forms['tr'].inputNaziv.value;
   
    opis = document.forms['tr'].inputOpis.value;
   
    tip = document.forms['tr'].inputTip.value;
    let cena=0;
    cena = document.forms['tr'].inputCenaOd.value;
    let trajanje=0;
    trajanje = document.forms['tr'].inputTrajanjeOd.value;
    
   
    let pocetak = $("#inputPocetak").val();
    let kraj = $("#inputKraj").val();
    let br=0;
 


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
       kraj,
       br,
       id

       
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
                row+="<td>" +  tr.br + "</td>";
               
                let btn1 = "<button id='zakazi' class='btn-success' data-id=" + tr.id + ">Prijavi se </button>";
                row += "<td>" + btn1 + "</td>"; 

                let btn2 = "<button id='info' class='btn-info' data-id=" + tr.naziv + ">Detalji treninga</button>";
                row += "<td>" + btn2 + "</td>"; 
                                     
        
                row += "</tr>";                                     

                $('#treninzi').append(row);                       
            }

        },
        error: function (response) {                               
            console.log("ERROR:\n", response);
        }


    });

});


function sortTable(n) {
  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
  table = document.getElementById("treninzi");
  switching = true;

  dir = "asc";
 
  while (switching) {

    switching = false;
    rows = table.rows;

    for (i = 1; i < (rows.length - 1); i++) {
   
      shouldSwitch = false;
    
      x = rows[i].getElementsByTagName("TD")[n];
      y = rows[i + 1].getElementsByTagName("TD")[n];
      
      if (dir == "asc") {
        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
   
          shouldSwitch = true;
          break;
        }
      } else if (dir == "desc") {
        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
 
          shouldSwitch = true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      /* If a switch has been marked, make the switch
      and mark that a switch has been done: */
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
      // Each time a switch is done, increase this count by 1:
      switchcount ++;
    } else {
      /* If no switching has been done AND the direction is "asc",
      set the direction to "desc" and run the while loop again. */
      if (switchcount == 0 && dir == "asc") {
        dir = "desc";
        switching = true;
      }
    }
  }
}


function sortNumber(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("treninzi");
    switching = true;
  
    dir = "asc";
   
    while (switching) {
  
      switching = false;
      rows = table.rows;
  
      for (i = 1; i < (rows.length - 1); i++) {
     
        shouldSwitch = false;
      
        x = rows[i].getElementsByTagName("TD")[n];
        y = rows[i + 1].getElementsByTagName("TD")[n];
        
        if (dir == "asc") {
          if (Number (x.innerHTML.toLowerCase()) > Number(y.innerHTML.toLowerCase())) {
     
            shouldSwitch = true;
            break;
          }
        } else if (dir == "desc") {
          if (Number(x.innerHTML.toLowerCase()) < Number(y.innerHTML.toLowerCase())) {
   
            shouldSwitch = true;
            break;
          }
        }
      }
      if (shouldSwitch) {
        /* If a switch has been marked, make the switch
        and mark that a switch has been done: */
        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
        switching = true;
        // Each time a switch is done, increase this count by 1:
        switchcount ++;
      } else {
        /* If no switching has been done AND the direction is "asc",
        set the direction to "desc" and run the while loop again. */
        if (switchcount == 0 && dir == "asc") {
          dir = "desc";
          switching = true;
        }
      }
    }
  }

  $(document).on('click', '#info', function () {  
    let naziv=this.dataset.id;
    if(naziv=="crossfit"){ window.location.href="crossfit.html";}
    else if(naziv=="box"){ window.location.href="box.html";}
    else if(naziv=="zumba"){ window.location.href="zumba.html";}
    else if(naziv=="tabata"){ window.location.href="tabata.html";}
    else{alert("Ovaj trening je verovatno nov pa za njega jos uvek nema info"); return;}

    

});