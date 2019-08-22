package com.codeoftheweb.Salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {
    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("/games")
    public Set<Game> getGame(Set<Game> games) {
        return new HashSet<>(games);
    }
    @RequestMapping("/allgames")
    public List<Game> getAll() {
        return gameRepository.findAll();
    }
}