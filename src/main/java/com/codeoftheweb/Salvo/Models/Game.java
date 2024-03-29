package com.codeoftheweb.Salvo.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Date creationDate = new Date();

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private Set<Score> scores;
    //**********
    public Game() {
    }

    //**********
    public long getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public Set<Score> getScores() {
        return scores;
    }

    //**********
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Map<String, Object> makeGameDTO() {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", this.getId());
        dto.put("creationDate", this.getCreationDate().getTime());
        dto.put("gamePlayers", this.getAllGamePlayer(this.getGamePlayers()));
        dto.put("score", this.getAllScores(getScores()));
        return dto;
    }

    private List<Map<String, Object>>getAllScores(Set<Score> scores) {
            return scores.stream().map(Score -> Score.makeScoresDTO()).collect(Collectors.toList());
    }

    public List<Map<String, Object>> getAllGamePlayer(Set <GamePlayer> gamePlayers) {
        return gamePlayers.stream().map(GamePlayer -> GamePlayer.makeGamePlayerDTO()).collect(Collectors.toList());
    }

    public GamePlayer getOneGamePlayer() {
        return this.gamePlayers.iterator().next();
    }

}
