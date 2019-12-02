package com.codeoftheweb.Salvo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(value = "Game Controller")
public class GameController {

  @Autowired
  private PlayerRepository playerRepository;
  @Autowired
  private GameRepository gameRepository;
  @Autowired
  private GamePlayerRepository gamePlayerRepository;
  @Autowired
  private ShipRepository shipRepository;
  @Autowired
  private SalvoRepository salvoRepository;

  @RequestMapping("/game_view/{gamePlayerId}")
  @ApiOperation(value = "Game View Infomation")
  public ResponseEntity<Map<String, Object>> getGameView(@PathVariable Long gamePlayerId, Authentication authentication) {

    if (isGuest(authentication)) {
      return new ResponseEntity<>(makeMap("error:", "Unauthorized"), HttpStatus.UNAUTHORIZED);
    }
    Player player = playerRepository.findByUserName(authentication.getName()).orElse(null);
    GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId).orElse(null);

    if (player == null) {
      return new ResponseEntity<>(makeMap("error:", "Unauthorized"), HttpStatus.UNAUTHORIZED);
    }
    if (gamePlayer == null) {
      return new ResponseEntity<>(makeMap("error:", "Unauthorized"), HttpStatus.UNAUTHORIZED);
    }

    if (gamePlayer.getPlayer().getId() != player.getId()) {
      return new ResponseEntity<>(makeMap("error:", "Conflict"), HttpStatus.CONFLICT);
    }

    Map<String, Object> dto = new LinkedHashMap<String, Object>();
    dto.put("idGame", gamePlayer.getId());
    dto.put("creationDate", gamePlayer.getGame().getCreationDate());
    dto.put("gamePlayers", gamePlayer.getGame().getAllGamePlayer(gamePlayer.getGame().getGamePlayers()));
    dto.put("gameState", "PLAY");
    dto.put("ships", gamePlayer.getShips());
    dto.put("hits", gamePlayer.getHits());
    dto.put("salvoes", gamePlayer.getAllSalvoes(gamePlayer.getGame().getGamePlayers()));

    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @RequestMapping(path = "/games/players/{gpId}/ships", method = RequestMethod.POST)
  @ApiOperation(value = "Add ships")
  public ResponseEntity<Map<String, Object>> addShips(@PathVariable Long gpId, @RequestBody List<Ship> ships, Authentication auth) {

    if (isGuest(auth)) {
      return new ResponseEntity<>(GameController.makeMap("error: Des Autorizado, usuario no logeado", "usuario no logeado"), HttpStatus.UNAUTHORIZED);
    }
    Player player = playerRepository.findByUserName(auth.getName()).orElse(null);
    GamePlayer gamePlayer = gamePlayerRepository.findById(gpId).orElse(null);

    if (player.getId() != gamePlayer.getPlayer().getId()) {
      return new ResponseEntity<>(GameController.makeMap("error: jugador equivocado", "jugador equivocado"), HttpStatus.UNAUTHORIZED);
    }
    if (!gamePlayer.getShips().isEmpty()) {
      return new ResponseEntity<>(GameController.makeMap("error: ships ya seteados", "ya tienes barcos"), HttpStatus.FORBIDDEN);
    }
    ships.stream().map(ship -> {
      ship.setGamePlayer(gamePlayer);
      return shipRepository.save(ship);
    }).collect(Collectors.toList());
    return new ResponseEntity<>(GameController.makeMap("ships correctos", ships), HttpStatus.CREATED);
  }

  @RequestMapping(path = "/games/players/{gpId}/salvoes", method = RequestMethod.POST)
  @ApiOperation(value = "Add salvoes")
  public ResponseEntity<Map<String, Object>> addSalvoes(@PathVariable Long gpId, @RequestBody Salvo salvo, Authentication auth) {

    if (isGuest(auth)) {
      return new ResponseEntity<>(GameController.makeMap("error: Des Autorizado, usuario no logeado", "usuario no logeado"), HttpStatus.UNAUTHORIZED);
    }
    Player player = playerRepository.findByUserName(auth.getName()).orElse(null);
    GamePlayer gamePlayer = gamePlayerRepository.findById(gpId).orElse(null);

    if (player.getId() != gamePlayer.getPlayer().getId()) {
      return new ResponseEntity<>(GameController.makeMap("error: jugador equivocado", "jugador equivocado"), HttpStatus.UNAUTHORIZED);
    }

    if (gamePlayer.getSalvos().isEmpty()){
      salvo.setTurno(1);
    }
    if (gamePlayer.getSalvos().size() <= gamePlayer.getOppo().getSalvos().size()){
      salvo.setTurno(gamePlayer.getSalvos().size()+1);
    } else {
      return new ResponseEntity<>(GameController.makeMap("Espere a que finalice el turno", ""), HttpStatus.UNAUTHORIZED);
    }
    salvo.setGamePlayer(gamePlayer);
    salvoRepository.save(salvo);
    return new ResponseEntity<>(GameController.makeMap("Salvos guardados",""), HttpStatus.CREATED);
  }

  private boolean isGuest(Authentication authentication) {
    return authentication == null || authentication instanceof AnonymousAuthenticationToken;
  }

  public static Map<String, Object> makeMap(String key, Object value){
    Map<String, Object> mp = new LinkedHashMap<String, Object>();
    mp.put(key, value);
    return mp;
  }

}
