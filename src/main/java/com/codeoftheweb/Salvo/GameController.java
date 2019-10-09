package com.codeoftheweb.Salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GameController {

  @Autowired
  private PlayerRepository playerRepository;
  @Autowired
  private GameRepository gameRepository;
  @Autowired
  private GamePlayerRepository gamePlayerRepository;

  @RequestMapping(path = "/games", method = RequestMethod.POST)
  public ResponseEntity<Object> createGame(Authentication authentication) {

    if(isGuest(authentication)) {
      return new ResponseEntity<>("No autorizao", HttpStatus.UNAUTHORIZED);
    }
    Player player = playerRepository.findByUserName(authentication.getName()).orElse(null);

    if (player == null){
      return new ResponseEntity<>("No esta autorizado", HttpStatus.UNAUTHORIZED);
    }
    Game game = gameRepository.save(new Game());
    GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(game, player));

    return new ResponseEntity<>(makeMap("gpid", gamePlayer.getId()), HttpStatus.CREATED);
  }
  private boolean isGuest(Authentication authentication) {
    return authentication == null || authentication instanceof AnonymousAuthenticationToken;
  }

  private Map<String, Object> makeMap(String key, Object value){
    Map<String, Object> mp = new LinkedHashMap<String, Object>();
    mp.put(key, value);
    return mp;
  }
}
