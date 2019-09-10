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
    calculos(obj, players);
}
Array.prototype.unique=function(a){
    return function(){return this.filter(a)}}(function(a,b,c){return c.indexOf(a,b+1)<0
});
function verJugadores(obj){
    let i=0;
    let players= [];
    obj.forEach(e => {
        e.score.map(function(p) {
            players[i]=p.email;
            i++;
        });
    });
    players = players.unique();
    console.log(players);
    return players;
}

function calculos(obj, players){
    let i =0;
    let total= [];
    let won= []
    let lost= [];
    let tied= [];
    obj.forEach(e => {
        players.forEach(d => {
            total[i]=0;
            won[i]=0;
            lost[i]=0;
            tied[i]=0;
            console.log(d)
            e.score.map(function(p) {
                if(d == p.email){
                    console.log(d +"/" + p.email)
                    switch (p.score){
                        case 0:
                            lost[i]++;
                            break;
                        case 0.5:
                            tied[i]++;
                            break;
                        case 1:
                            won[i]++;
                            break;
                    }
                    console.log();
                }
            });
            console.log(won[i])
            console.log(lost[i])
            console.log(tied[i])
            i++;
        });
    });
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



