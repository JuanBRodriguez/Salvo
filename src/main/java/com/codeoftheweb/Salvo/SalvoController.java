package com.codeoftheweb.Salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/games")
    public Map<String, Object> getGames(Authentication authentication) {
        Player player = playerRepository.findByUserName(authentication.getName());
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("player", player.makePlayerDTO());
        dto.put("games",gameRepository.findAll().stream().map(Game::makeGameDTO).collect(Collectors.toList() ));
        return dto;
    }
    @RequestMapping("/game_view/{gamePlayerId}")
    public Map<String, Object> getGameView(@PathVariable Long gamePlayerId) {
        GamePlayer gamePlayer =gamePlayerRepository.getOne(gamePlayerId);

        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("idGame", gamePlayer.getId());
        dto.put("creationDate", gamePlayer.getGame().getCreationDate());
        dto.put("gamePlayers", gamePlayer.getGame().getAllGamePlayer(gamePlayer.getGame().getGamePlayers()));
        dto.put("ships", gamePlayer.getShips());
        dto.put("salvoes", gamePlayer.getAllSalvoes(gamePlayer.getGame().getGamePlayers()));
        return dto;
    }

}