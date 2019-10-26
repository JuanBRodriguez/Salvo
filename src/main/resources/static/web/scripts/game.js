
window.addEventListener('load', function () {
    console.log("hola termino de cargar");
    loadGame();
});

function getParameterByName(name) {
    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
};

function loadGame() {
    $.get('/api/game_view/'+getParameterByName('gp'))
        .done(function(data) {
            console.log(data);
            let playerInfo = [];
            let turnAct = 2;
            if(data.gamePlayers[0].id == getParameterByName('gp')){
                playerInfo = [data.gamePlayers[0].player.email, data.gamePlayers[1].player.email];
            }
            else {
                playerInfo = [data.gamePlayers[1].player.email, data.gamePlayers[0].player.email];
            }
            $('#playerInfo').text("Partida de :"+playerInfo[0] + ' (you) vs ' + playerInfo[1]);
            data.ships.forEach(function(ship){
                ship.shipLocations.forEach(function(location){
                    $('#'+location).css("background-color", "grey");//.addClass('ship-piece');
                })
            });
            data.salvoes.forEach(function(s){
                if(s.player === playerInfo[0]){ //s.turn <= turnAct &&
                    s.locations.forEach(function(location){
                        //console.log(location);
                        $('#'+location+"s").css("background-color", "crimson");//addClass('salvo');
                    })
                }
            });
            data.salvoes.forEach(function(s){
                if(s.player == playerInfo[1]){ //s.turn <= turnAct &&
                    s.locations.forEach(function(location){
                        console.log(location);
                        data.ships.forEach(function(ship){
                            ship.shipLocations.forEach(function(locShip){
                                if(locShip==location){
                                $('#'+location).css("background-color", "darkmagenta");
                                console.log("Disparo en "+location+" a dado en el blanco!");
                                }
                            })
                        });

                    })
                }
            });
        })
        .fail(function( jqXHR, textStatus ) {
            console.log("Failed: " + textStatus );
        });
};


function addShips(){
  $.post({
      url: "/api/games/players/"+ getParameterByName('gp')+"/ship",
      data: JSON.stringify( [{ "type": "destroyer", "shipLocations": ["A1", "B1", "C1"]},
                            { "type": "patrol boat", "shipLocations": ["H5", "H6"] },
                            { "type": "submarine", "shipLocations": ["H8", "H9", "H10"] }]
                           ),
      dataType: "text",
      contentType: "application/json"
  })
  .done(function (response, status, jqXHR) {
     console.log( "ships guardados: " + response );
     setTimeout(function(){ location.reload(); }, 2000);
  })
  .fail(function (jqXHR, textStatus, httpError) {
    console.log("shisps no guardados: " + textStatus + " " + httpError);
  })
}
