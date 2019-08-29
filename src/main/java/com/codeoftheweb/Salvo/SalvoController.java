package com.codeoftheweb.Salvo;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/games")
    public List<Map<String, Object>> getGames() {
        return gameRepository.findAll().stream().map(Game -> Game.makeGameDTO()).collect(Collectors.toList());
    }
    @RequestMapping("/game_view/{gamePlayerId}")
    public List<Map<String, Object>> getGameView(@PathVariable Long gamePlayerId) {
        return gamePlayerRepository.findById(gamePlayerId).stream().map(gamePlayer -> gamePlayer.makeGameViewDTO()).collect(Collectors.toList());
    }

}