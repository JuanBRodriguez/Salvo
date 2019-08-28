package com.codeoftheweb.Salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String type;

    @ElementCollection
    @Column(name="locations")
    private List<String> shipLocations=new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayerID")
    private GamePlayer gamePlayer;
    //***********************************************
    public Ship() {
    }
    public Ship(String type, List<String> shipLocations, GamePlayer gamePlayer) {
        this.type = type;
        this.shipLocations = shipLocations;
        this.gamePlayer = gamePlayer;
    }
    //***********************************************
    public void setType(String type) {
        this.type = type;
    }

    public void setShipLocations(List<String> shipLocations) {
        this.shipLocations = shipLocations;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
    //****************************************

    public String getType() {
        return type;
    }

    public List<String> getShipLocations() {
        return shipLocations;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
}
