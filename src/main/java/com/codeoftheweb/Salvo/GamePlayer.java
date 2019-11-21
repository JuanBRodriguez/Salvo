package com.codeoftheweb.Salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Entity
public class GamePlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
    @GenericGenerator(name = "native", strategy = "native")

    private long id;
    private Date joinDate = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    private Set<Ship> ships;

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    private Set<Salvo> salvos;
    //
    public GamePlayer() {
    }
    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
    }
    //
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setShips(Set<Ship> ships) {
        this.ships = ships;
    }

    //
    public long getId() {
        return id;
    }
    public Date getJoinDate() {
        return joinDate;
    }
    @JsonIgnore
    public Game getGame() {
        return game;
    }
    @JsonIgnore
    public Player getPlayer() {
        return player;
    }
    @JsonIgnore
    public Set<Ship> getShips() {
        return ships;
    }
    @JsonIgnore
    public Set<Salvo> getSalvos() {
        return salvos;
    }

    public Map<String, Object> makeGamePlayerDTO() {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", this.getId());
        dto.put("player", this.getPlayer().makePlayerDTO());
        return dto;
    }
    public Map<String, Object> getHits(Set<GamePlayer> gamePlayers, Player player) {
        Map<String, Object> hits = new LinkedHashMap<String, Object>();
        List<Map<String, Object>> self =new ArrayList<>();
        List<Map<String, Object>> oppo =new ArrayList<>();

        List<Map<String, Object>> inu = gamePlayers.stream().flatMap(GP -> GP.getSalvos().stream()
                        .map(Sal -> {
                                        if (Sal.getGamePlayer().getPlayer().getUserName() == player.getUserName()){
                                            oppo.add(Sal.makeSalvoDTO());
                                            return Sal.makeSalvoDTO();
                                        }else{
                                            self.add(Sal.makeSalvoDTO());
                                            return Sal.makeSalvoDTO();
                                        }
                                    }
                                    )).collect(Collectors.toList());
        hits.put("opponent", oppo );
        hits.put("self", self);
        return hits;
    }
    public List<Map<String, Object>> getAllSalvoes(Set <GamePlayer> gamePlayers) {
        return gamePlayers
                .stream()
                .flatMap(GP -> GP.getSalvos()
                        .stream()
                        .map(Sal -> Sal.makeSalvoDTO()))
                .collect(Collectors.toList());
    }


}

