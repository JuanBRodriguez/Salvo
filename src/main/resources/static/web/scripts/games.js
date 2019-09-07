//Referencias al Dooom
var lista = document.getElementById("lista");

//variables globales
window.addEventListener('load', function () {
    console.log("hola termino de cargar");

    $.get("/api/games")
        .done(function (games) {
            console.log(games)
            cargarTabla(games)
            cargarLista(games)
        })
        .fail(function( jqXHR, textStatus ) {
            console.log("Failed: " + textStatus );
        });
});
function cargarTabla(obj){
    players = verJugadores(obj)
    let i =0;
    let total= [];
    let won= []
    let lost= [];
    let tied= [];
    players.forEach(e => {
        total[i]=0;
        won[i]=0;
        lost[i]=0;
        tied[i]=0;
        obj.forEach(d => {
            d.gamePlayers.map(function(p) {
                if(players[i]== p.player.email){
                    //total[i]+= JSON.parse(p.player.score).score;
                    console.log(players[i] +"/" +p.player.email)
                    let scr=p.player.score[0];
                    let s = src.score + ""; // simply convert a number to string
                    let d = parseInt(s);
                    console.log(d)
                }
            });
        });
        i++;
    });
    console.log(total)

}
Array.prototype.unique=function(a){
    return function(){return this.filter(a)}}(function(a,b,c){return c.indexOf(a,b+1)<0
});
function verJugadores(obj){
    let i=0;
    let players= [];
    obj.forEach(e => {
        e.gamePlayers.map(function(p) {
            players[i]=p.player.email;
            i++;
        });
    });
    players = players.unique();
    console.log(players);
    return players;
}
function calcularTotal(obj){

}


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



