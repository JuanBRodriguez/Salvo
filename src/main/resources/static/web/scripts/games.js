//Referencias al Dooom
var lista = document.getElementById("lista");

window.addEventListener('load', function () {
    console.log("hola termino de cargar");

    $.get("/api/games")
        .done(function (games) {
            console.log(games)
            if(games.player === "guest"){
              $("#logueo").show();
              $("#deslogueo").hide();
            }else{
              $("#logueo").hide();
              $("#deslogueo").show();
            }
            cargarTabla(games.games)
            cargarLista(games.games)

            console.log("juegos cargados correctamente");
        })
        .fail(function( jqXHR, textStatus ) {
            console.log("Failed: " + textStatus );
        });
});

function cargarTabla(obj){
    let calc;
    let players = verJugadores(obj)
    calc = calculos(obj, players);
    //console.log(calc);
    calc.sort(function (a,b) { return b.to - a.to; });
    //console.log(calc);
    for (i=0;i<3; i++){
        $("#na"+i).html(calc[i].pl);
        $("#to"+i).html(calc[i].to);
        $("#wo"+i).html(calc[i].wo);
        $("#lo"+i).html(calc[i].lo);
        $("#ti"+i).html(calc[i].ti);
    }
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
    //console.log(players);
    return players;
}

function calculos(obj, players){
    let i =0;
    let data= [];
    players.forEach(d => {
        let total = 0;
        let won = 0;
        let lost = 0;
        let tied = 0;
        //console.log("jugador: "+d)
        obj.forEach(e => {
            e.score.map(function(p) {
                if(d == p.email){
                    total +=p.score;
                    switch (p.score){
                        case 0:
                            lost++;
                            break;
                        case 0.5:
                            tied++;
                            break;
                        case 1:
                            won++;
                            break;
                    }
                }
            });
        });

        //console.log("ganados"+won)
        //console.log("perdidos"+lost)
        //console.log("empatados"+tied)
        data.push({pl: d,to: total,wo: won, lo: lost, ti: tied})
        i++;
    });
    return data;
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

function login(){
  let usuario = $("#email").val();
  let contrase = $("#pass").val();

  $.post("/api/login", { username: usuario, password: contrase })
            .done(function() {
              console.log("logged in!");
              location.reload();
            })
            .fail(function( jqXHR, textStatus ) {
                        console.log("Failed: " + textStatus );
            });
}
function registrar(){
  let usuario = $("#email").val();
  let contrase = $("#pass").val();

  $.post("/api/players", { username: usuario, password: contrase})
            .done(function() {
                console.log("registred success");
                $.post("/api/login", { username: usuario, password: contrase })
                            .done(function() {
                              console.log("logged in!");
                              location.reload();
                            })
            })
            .fail(function( jqXHR, textStatus ) {
                  console.log("Failed: " + textStatus );
            });
}

function desloguear(){
  $.post("/api/logout")
              .done(function() {
                console.log("logged out");
                location.reload();
              })
              .fail(function( jqXHR, textStatus ) {
                          console.log("Failed: " + textStatus );
               });
}

function joinGame(){

 console.log("se unio");
}