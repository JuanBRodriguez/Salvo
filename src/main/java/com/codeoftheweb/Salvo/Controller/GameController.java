package com.codeoftheweb.Salvo.Controller;

import com.codeoftheweb.Salvo.GameState;
import com.codeoftheweb.Salvo.Models.*;
import com.codeoftheweb.Salvo.Repositories.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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
    @Autowired
    private ScoreRepository scoreRepository;

    @RequestMapping("/game_view/{gamePlayerId}")
    @ApiOperation(value = "Game View Infomation")
    public ResponseEntity<Map<String, Object>> getGameView(@PathVariable Long gamePlayerId, Authentication authentication) {

        if (isGuest(authentication)) {
            return new ResponseEntity<>(makeMap("error", " Unauthorized"), HttpStatus.UNAUTHORIZED);
        }
        Player player = playerRepository.findByUserName(authentication.getName()).orElse(null);
        GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId).orElse(null);

        if (player == null) {
            return new ResponseEntity<>(makeMap("error", " Unauthorized"), HttpStatus.UNAUTHORIZED);
        }
        if (gamePlayer == null) {
            return new ResponseEntity<>(makeMap("error", " Unauthorized"), HttpStatus.UNAUTHORIZED);
        }

        if (gamePlayer.getPlayer().getId() != player.getId()) {
            return new ResponseEntity<>(makeMap("error", " Conflict"), HttpStatus.CONFLICT);
        }

        GameState gameState = gamePlayer.getGameState();

        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("idGame", gamePlayer.getGame().getId());
        dto.put("creationDate", gamePlayer.getGame().getCreationDate());
        dto.put("gamePlayers", gamePlayer.getGame().getAllGamePlayer(gamePlayer.getGame().getGamePlayers()));
        dto.put("gameState", gameState);
        dto.put("ships", gamePlayer.getShips());
        dto.put("hits", gamePlayer.getHits());
        dto.put("salvoes", gamePlayer.getAllSalvoes(gamePlayer.getGame().getGamePlayers()));

        Set<Score> scores = gamePlayer.getGame().getScores();
        int scoreQuantity = scores.size();

        if (gameState == GameState.WON && scoreQuantity < 2 && player != scores.stream().filter(score -> score.getPlayer() == player)) {
            Score score = new Score(player, gamePlayer.getGame(), 1.0, new Date());
            scoreRepository.save(score);
        }
        if (gameState == GameState.TIE && scoreQuantity < 2 && player != scores.stream().filter(score -> score.getPlayer() == player)) {
            Score score = new Score(player, gamePlayer.getGame(), 0.5, new Date());
            scoreRepository.save(score);
        }
        if (gameState == GameState.LOST && scoreQuantity < 2 && player != scores.stream().filter(score -> score.getPlayer() == player)) {
            Score score = new Score(player, gamePlayer.getGame(), 0.0, new Date());
            scoreRepository.save(score);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(path = "/games/players/{gpId}/ships", method = RequestMethod.POST)
    @ApiOperation(value = "Add ships")
    public ResponseEntity<Map<String, Object>> addShips(@PathVariable Long gpId, @RequestBody List<Ship> ships, Authentication auth) {

        if (isGuest(auth)) {
            return new ResponseEntity<>(GameController.makeMap("error", " usuario no logeado"), HttpStatus.UNAUTHORIZED);
        }
        Player player = playerRepository.findByUserName(auth.getName()).orElse(null);
        GamePlayer gamePlayer = gamePlayerRepository.findById(gpId).orElse(null);

        if (player.getId() != gamePlayer.getPlayer().getId()) {
            return new ResponseEntity<>(GameController.makeMap("error", " Unauthorized player "), HttpStatus.UNAUTHORIZED);
        }
        if (!gamePlayer.getShips().isEmpty()) {
            return new ResponseEntity<>(GameController.makeMap("error", " ships already established"), HttpStatus.FORBIDDEN);
        }
        ships.stream().map(ship -> {
            ship.setGamePlayer(gamePlayer);
            return shipRepository.save(ship);
        }).collect(Collectors.toList());
        return new ResponseEntity<>(GameController.makeMap("OK", " ships successfully saved"), HttpStatus.OK);
    }

    @RequestMapping(path = "/games/players/{gpId}/salvoes", method = RequestMethod.POST)
    @ApiOperation(value = "Add salvoes")
    public ResponseEntity<Map<String, Object>> addSalvoes(@PathVariable Long gpId, @RequestBody Salvo salvo, Authentication auth) {

        if (isGuest(auth)) {
            return new ResponseEntity<>(GameController.makeMap("error", " Unauthorized player 'Guest' "), HttpStatus.UNAUTHORIZED);
        }
        Player player = playerRepository.findByUserName(auth.getName()).orElse(null);
        GamePlayer gamePlayer = gamePlayerRepository.findById(gpId).orElse(null);

        if (player.getId() != gamePlayer.getPlayer().getId()) {
            return new ResponseEntity<>(GameController.makeMap("error", " Unauthorized player "), HttpStatus.UNAUTHORIZED);
        }
        if (salvo.getLocations().size() > 5) {
            return new ResponseEntity<>(GameController.makeMap("error", " Unauthorized quantity of salvos"), HttpStatus.UNAUTHORIZED);
        }
        if (gamePlayer.getSalvos().isEmpty()) {
            salvo.setTurno(1);
        }
        if (gamePlayer.getSalvos().size() <= gamePlayer.getOpponent().getSalvos().size()) {
            salvo.setTurno(gamePlayer.getSalvos().size() + 1);
        } else {
            return new ResponseEntity<>(GameController.makeMap("error", " Please wait for the turn to end"), HttpStatus.UNAUTHORIZED);
        }
        salvo.setGamePlayer(gamePlayer);
        salvoRepository.save(salvo);
        return new ResponseEntity<>(GameController.makeMap("OK", " Salvos successfully saved"), HttpStatus.CREATED);
    }

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    public static Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> mp = new LinkedHashMap<String, Object>();
        mp.put(key, value);
        return mp;
    }

}
