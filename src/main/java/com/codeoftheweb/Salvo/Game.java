package com.codeoftheweb.Salvo;

import org.hibernate.annotations.GenericGenerator;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private Date creationDate;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    public Game() {
    }

    public Game(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public Map<String, Object> makeGameDTO() {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", this.getId());
        dto.put("creationDate", this.getCreationDate().getTime());
        dto.put("gamePlayers", this.getAllGamePlayer(this.getGamePlayers()) );
        return dto;
    }

    public List<Map<String, Object>> getAllGamePlayer(Set <GamePlayer> gamePlayers) {
        return gamePlayers.stream().map(GamePlayer -> GamePlayer.makeGamePlayerDTO()).collect(Collectors.toList());
    }
}
