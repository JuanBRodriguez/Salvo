package com.codeoftheweb.Salvo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(value = "Salvo Controller")
public class SalvoController {
  @Autowired
  private GameRepository gameRepository;
  @Autowired
  private GamePlayerRepository gamePlayerRepository;
  @Autowired
  private PlayerRepository playerRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;


  @RequestMapping("/games")
  @ApiOperation(value = "Games Information")
  public Map<String, Object> getGames(Authentication authentication) {
    Map<String, Object> dto = new LinkedHashMap<String, Object>();
    if (isGuest(authentication)) {
      dto.put("player", "guest");
    } else {
      Player player = playerRepository.findByUserName(authentication.getName()).get();
      dto.put("player", player.makePlayerDTO());
    }
    //dto.put("player", player.makePlayerDTO());
    dto.put("games", gameRepository.findAll().stream().map(Game::makeGameDTO).collect(Collectors.toList()));
    return dto;
  }

  @RequestMapping(path = "/players", method = RequestMethod.POST)
  @ApiOperation(value = "User Register")
  public ResponseEntity<Object> register(@RequestParam String username, @RequestParam String password) {
    if (username.isEmpty() || password.isEmpty()) {
      return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
    }
    if (playerRepository.findByUserName(username).orElse(null) != null) {
      return new ResponseEntity<>("Name alredy in use", HttpStatus.FORBIDDEN);
    }
    playerRepository.save(new Player(username, passwordEncoder.encode(password)));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(path = "/game/{gameId}/players", method = RequestMethod.POST)
  @ApiOperation(value = "Join Game")
  public ResponseEntity<Map<String, Object>> joinGame(@PathVariable Long gameId, Authentication authentication) {

    if (isGuest(authentication)) {
      return new ResponseEntity<>(makeMap("error:","Usuario no logeado"), HttpStatus.UNAUTHORIZED);
    }
    Player player = playerRepository.findByUserName(authentication.getName()).orElse(null);
    Game game = gameRepository.getOne(gameId);

    if (game == null) {
      return new ResponseEntity<>(makeMap("error:", "juego no encontrado"), HttpStatus.FORBIDDEN);
    }
    if (player == null) {
      return new ResponseEntity<>(makeMap("error:", "jugador no encontrado"), HttpStatus.FORBIDDEN);
    }
    int gamePlayersCount = game.getGamePlayers().size();

    if (gamePlayersCount == 1) {
      if (game.getOneGamePlayer().getPlayer().getUserName() == player.getUserName()) {
        return new ResponseEntity<>(makeMap("error:", "El jugador ya se encuentra en el juego"), HttpStatus.UNAUTHORIZED);
      } else {
        GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(game, player));
        return new ResponseEntity<>(makeMap("gpId", gamePlayer.getId()), HttpStatus.CREATED);
      }
    } else {
      return new ResponseEntity<>(makeMap("error", "Juego completo"), HttpStatus.OK);
    }
  }

  @RequestMapping("/leaderBoard")
  @ApiOperation(value = "Leader board")
  public  List<Map<String,Object>> leaderBoard(){

    return  playerRepository.findAll()
            .stream()
            .map(player  ->  player.makePlayerScoreDTO())
            .collect(Collectors.toList());
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