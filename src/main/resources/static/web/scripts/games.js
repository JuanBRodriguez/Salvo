//Referencias al Dooom
var lista = document.getElementById("lista");

//variables globales
window.addEventListener('load', function () {

    console.log("hola termino de cargar");

    /*$.ajax({
        url: '/api/games',
        success: function(games) {
           // console.log(games);
           console.log("se logroooo");
        },
        error: function() {
            console.log("Error, No se ha podido obtener");
        }
    })
    .done(function( games ) {
        console.log(games);
     });*/

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
    var htmlList = "";
    obj.forEach(e => {
              htmlList +='<li>';
              htmlList +=new Date(e.creationDate).toLocaleString();
              htmlList += ' ' + e.gamePlayers.map(function(p) { return p.player.email}).join(',');
              htmlList +='</li>';
        });
    lista.innerHTML = htmlList;
}



