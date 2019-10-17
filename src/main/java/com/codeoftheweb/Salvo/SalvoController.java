package com.codeoftheweb.Salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
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
    public Map<String, Object> getGames(Authentication authentication) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        if(isGuest(authentication)) {
            dto.put("player", "guest");
        }else{
            Player player = playerRepository.findByUserName(authentication.getName()).get();
            dto.put("player", player.makePlayerDTO());
        }
        //dto.put("player", player.makePlayerDTO());
        dto.put("games",gameRepository.findAll().stream().map(Game::makeGameDTO).collect(Collectors.toList() ));
        return dto;
    }
    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    @RequestMapping("/game_view/{gamePlayerId}")
    public ResponseEntity<Map<String, Object>> getGameView(@PathVariable Long gamePlayerId, Authentication authentication) {

        if(isGuest(authentication)){
            return new ResponseEntity<>(GameController.makeMap("error:","Algo salio mal"), HttpStatus.UNAUTHORIZED);
        }
        Player player = playerRepository.findByUserName(authentication.getName()).orElse(null);
        GamePlayer gamePlayer =gamePlayerRepository.findById(gamePlayerId).orElse(null);

        if (player == null){
            return new ResponseEntity<>(GameController.makeMap("error:","Algo salio mal"), HttpStatus.UNAUTHORIZED);
        }
        if (gamePlayer == null){
            return new ResponseEntity<>(GameController.makeMap("error:","Algo salio mal"), HttpStatus.UNAUTHORIZED);
        }

        if (gamePlayer.getPlayer().getId() != player.getId()){
            return new ResponseEntity<>(GameController.makeMap("error:","Algo salio mal"), HttpStatus.CONFLICT);
        }

        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("idGame", gamePlayer.getId());
        dto.put("creationDate", gamePlayer.getGame().getCreationDate());
        dto.put("gamePlayers", gamePlayer.getGame().getAllGamePlayer(gamePlayer.getGame().getGamePlayers()));
        dto.put("ships", gamePlayer.getShips());
        dto.put("salvoes", gamePlayer.getAllSalvoes(gamePlayer.getGame().getGamePlayers()));

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping (path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String username, @RequestParam String password){
        if (username.isEmpty()||password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (playerRepository.findByUserName(username).orElse(null)!= null) {
            return new ResponseEntity<>("Name alredy in use",HttpStatus.FORBIDDEN);
        }
        playerRepository.save(new Player(username, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/game/{gameId}/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> joinGame(@PathVariable Long gameId, Authentication authentication){

        if(isGuest(authentication)){
            return new ResponseEntity<>(GameController.makeMap("error:","Des Autorizado usuario no logeado"), HttpStatus.UNAUTHORIZED);
        }
        Player player = playerRepository.findByUserName(authentication.getName()).orElse(null);
        Game game =gameRepository.getOne(gameId);

        if (game == null){
            return new ResponseEntity<>(GameController.makeMap("error:","juego no encontrado"), HttpStatus.FORBIDDEN);
        }
        if (player == null){
            return new ResponseEntity<>(GameController.makeMap("error:","jugador no encontrado"), HttpStatus.FORBIDDEN);
        }
        int gamePlayersCount = game.getGamePlayers().size();

        if (gamePlayersCount == 1){
            GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(game, player));
            return new ResponseEntity<>(GameController.makeMap("gpId",gamePlayer.getId()), HttpStatus.CREATED);
        }else{
        return new  ResponseEntity<>(GameController.makeMap("error","Juego completo"),HttpStatus.OK);
        }
    }

}