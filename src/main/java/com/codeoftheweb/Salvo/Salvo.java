package com.codeoftheweb.Salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
public class Salvo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayerId")
    private GamePlayer gamePlayer;

    private long turn;

    @ElementCollection
    @Column(name="locations")
    private List<String> locations=new ArrayList<>();

    //
    public Salvo() {
    }
    public Salvo(GamePlayer gamePlayer, long turn, List<String> locations) {
        this.gamePlayer = gamePlayer;
        this.turn = turn;
        this.locations = locations;
    }
    //
    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
    public void setTurno(long turn) {
        this.turn = turn;
    }
    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
    //
    public long getId() {
        return id;
    }
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
    public long getTurn() {
        return turn;
    }
    public List<String> getLocations() {
        return locations;
    }

    public Map<String, Object> makeSalvoDTO() {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("turn",this.getTurn() );
        dto.put("Player",this.getGamePlayer().getPlayer().getUserName());
        dto.put("locations", this.getLocations());
        return dto;
    }

}
