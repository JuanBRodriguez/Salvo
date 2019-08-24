//Referencias al Dooom
var lista = document.getElementById("lista");

//variables globales

window.addEventListener('load', function () {

    console.log("hola termino de cargar");
    $.ajax({
        url: '/api/games',
        success: function(games) {
           // console.log(games);
        },
        error: function() {
            console.log("Error, No se ha podido obtener");
        }
    })
        .done(function( games ) {
            console.log(games);
        });

    $.get("/api/games")
        .done(function (games) {
            console.log(games)
            cargarLista(games)
        })
        .fail(function () {
            console.log("Error, No se ha podido obtener")
        })
        .always(function() {
            console.log( "finished" );
        });


});

function cargarLista(obj){
    var ele = "<li>";
    for (const e in obj) {
        ele+=obj[e];
        ele+="</li>"

        console.log(obj[e]);
        lista.append(ele);
    }
}



