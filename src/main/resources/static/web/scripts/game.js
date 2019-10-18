
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
