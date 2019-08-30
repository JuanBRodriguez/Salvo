//Referencias al Dooom
var cuadri = document.getElementById("cuadricula");

//variables globales
window.addEventListener('load', function () {

    console.log("hola termino de cargar");
    $.get("/api/game_view/1")
        .done(function (games) {
            console.log(games)
        })
        .fail(function ( jqXHR, textStatus) {
            console.log("Error, No se ha podido obtener"+ textStatus)
        })
        .always(function() {
            console.log( "finished" );
        });
});

function cargarLista(e){
    var htmlList = "";
    htmlList +='<li>';
    htmlList +=new Date(e.creationDate).toLocaleString();
    htmlList += ' ' + e.gamePlayers.map(function(p) { return p.player.email}).join(',');
    htmlList += '<br>' + e.ships.map(function(p) { return p.type}).join(',');
    htmlList +='</li>';
    cuadri.innerHTML = htmlList;
}



