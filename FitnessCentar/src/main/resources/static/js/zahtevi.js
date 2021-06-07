$(document).ready(function () {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trener",
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let tr of response) {
                let row = "<tr>";
                row += "<td>" + tr.name + "</td>";
                row += "<td>" + tr.surname + "</td>";

                let btn = "<button class='btn-success'  data-id=" + tr.id + ">Prihvati</button>";
                row += "<td>" + btn + "</td>";                      // ubacujemo button u poslednju ćeliju reda
                btn = "<button class='btn-danger' data-id=" + tr.id + ">Odbij</button>";
                row += "<td>" + btn + "</td>";

                row += "</tr>";

                $('#zahtevi').append(row); //novi red
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '.btn-success', function () {

    let id=this.dataset.id;

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/trener/put/" + id ,
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

