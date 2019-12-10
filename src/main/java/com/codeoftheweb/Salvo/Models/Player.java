package com.codeoftheweb.Salvo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Player {

    private String userName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy = "player",fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy = "player",fetch = FetchType.EAGER)
    private Set<Score> scores;

    private String password;

    public Player(){}

    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    //Getters
    public String getUserName() {
        return userName;
    }

    public long getId() {
        return id;
    }
    @JsonIgnore
    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }
    @JsonIgnore
    public Set<Score> getScores() {
        return scores;
    }

    public String getPassword() {
        return password;
    }

    //Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Map<String, Object> makePlayerDTO() {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", this.getId());
        dto.put("email", this.getUserName());
        //dto.put("score", this.getScores());
        return dto;
    }

    public Map<String,Object>   makePlayerScoreDTO(){
        Map<String,  Object>    dto =    new LinkedHashMap<>();
        Map<String,  Object>    score =    new LinkedHashMap<>();

        dto.put("id",   this.getId());
        dto.put("email", this.getUserName());
        dto.put("score",score);
        score.put("total", this.getTotalScore());
        score.put("won", this.getWinScore());
        score.put("lost", this.getLostScore());
        score.put("tied", this.getTiedScore());
        return  dto;
    }

    public Double   getTotalScore(){
        return  this.getWinScore() * 1.0D  +   this.getTiedScore()  * 0.5D;
    }
    public long  getWinScore(){
        return this.getScores().stream().filter(score -> score.getScore() == 1.0D).count();
    }
    public long  getLostScore(){
        return this.getScores().stream().filter(score -> score.getScore() == 0.0D).count();
    }
    public long  getTiedScore(){
        return this.getScores().stream().filter(score -> score.getScore() == 0.5D).count();
    }


}
