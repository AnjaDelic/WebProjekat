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
        type: "GET",
        url: "http://localhost:8080/api/trener/sviTR",
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let tr of response) {
                let row = "<tr>";
                row += "<td>" + tr.name + "</td>";
                row += "<td>" + tr.surname + "</td>";
                row += "<td>" + tr.username + "</td>";
                row += "<td>" + tr.active + "</td>";


                 // ubacujemo button u poslednju ćeliju reda
                btn = "<button class='btn-danger' data-id=" + tr.id + ">Obrisi</button>";
                row += "<td>" + btn + "</td>";

                row += "</tr>";

                $('#brisanje').append(row); //novi red
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
        url: "http://localhost:8080/api/trener/delete/" + id ,
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