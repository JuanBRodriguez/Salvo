package com.codeoftheweb.Salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
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

    public Player(){}

    public Player(String userName) { //Constructor
        this.userName = userName;
    }
    //Getters
    public String getUserName() {
        return userName;
    }

    public long getId() {
        return id;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    //Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }
}
