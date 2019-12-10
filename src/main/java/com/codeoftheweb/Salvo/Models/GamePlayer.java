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
        salvos = new HashSet<>();
        ships = new HashSet<>();
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


        /*public Map<String, Object> getHits() {
            Map<String, Object> hits = new LinkedHashMap<String, Object>();
            List<Map<String, Object>> self =new ArrayList<>();
            List<Map<String, Object>> oppo =new ArrayList<>();

            this.getSalvos().stream().map(Sal -> oppo.add(Sal.makeSalvoDTO())).collect(Collectors.toList());
            this.getOppo().getSalvos().stream().map(Sal -> self.add(Sal.makeSalvoDTO())).collect(Collectors.toList());

            hits.put("opponent", oppo );
            hits.put("self", self);
            return hits;
        }*/

    public Map<String, Object> getHits() {
        Map<String, Object> hits = new LinkedHashMap<String, Object>();
        if (this.getGame().getGamePlayers().size()>1) {
            hits.put("opponent", calcHits(this.getOppo()));
        }else{
            hits.put("opponent", new ArrayList<>());
        }

        hits.put("self", calcHits(this));
        return hits;
    }

    private List<Map> calcHits(GamePlayer gamePlayer) {
        List<Map> hits  = new ArrayList<>();

        int carrierDamage = 0;
        int battleshipDamage = 0;
        int submarineDamage = 0;
        int destroyerDamage = 0;
        int patrolboatDamage = 0;

        List <String> carrierLocation = getLocatiosShip("carrier",gamePlayer);
        List <String> battleshipLocation = getLocatiosShip("battleship",gamePlayer);
        List <String> submarineLocation = getLocatiosShip("submarine",gamePlayer);
        List <String> destroyerLocation = getLocatiosShip("destroyer",gamePlayer);
        List <String> patrolboatLocation = getLocatiosShip("patrolboat",gamePlayer);
        if (gamePlayer.getGame().getGamePlayers().size()>1) {
            for (Salvo salvo : gamePlayer.getOppo().getSalvos()) {

                long carrierHitsInTurn = 0;
                long battleshipHitsInTurn = 0;
                long submarineHitsInTurn = 0;
                long destroyerHitsInTurn = 0;
                long patrolboatHitsInTurn = 0;
                long missedShots = salvo.getLocations().size();

                Map<String, Object> hitsMapPerTurn = new LinkedHashMap<>();
                Map<String, Object> damagesPerTurn = new LinkedHashMap<>();

                Set<String> hitCellsList = new HashSet<>();
                Set<String> missedCellsList = new HashSet<>();

                for (String salvoShot : salvo.getLocations()) {
                    if (carrierLocation.contains(salvoShot)) {
                        carrierDamage++;
                        carrierHitsInTurn++;
                        hitCellsList.add(salvoShot);
                    } else if (battleshipLocation.contains(salvoShot)) {
                        battleshipDamage++;
                        battleshipHitsInTurn++;
                        hitCellsList.add(salvoShot);
                    }else if (submarineLocation.contains(salvoShot)) {
                        submarineDamage++;
                        submarineHitsInTurn++;
                        hitCellsList.add(salvoShot);
                    } else if (destroyerLocation.contains(salvoShot)) {
                        destroyerDamage++;
                        destroyerHitsInTurn++;
                        hitCellsList.add(salvoShot);
                    }else if (patrolboatLocation.contains(salvoShot)) {
                        patrolboatDamage++;
                        patrolboatHitsInTurn++;
                        hitCellsList.add(salvoShot);
                    }else {
                        missedCellsList.add(salvoShot);
                        missedShots++;
                    }
                }
                damagesPerTurn.put("carrierHits", carrierHitsInTurn);
                damagesPerTurn.put("battleshipHits", battleshipHitsInTurn);
                damagesPerTurn.put("submarineHits", submarineHitsInTurn);
                damagesPerTurn.put("destroyerHits", destroyerHitsInTurn);
                damagesPerTurn.put("patrolboatHits", patrolboatHitsInTurn);
                damagesPerTurn.put("carrier", carrierDamage);
                damagesPerTurn.put("battleship", battleshipDamage);
                damagesPerTurn.put("submarine", submarineDamage);
                damagesPerTurn.put("destroyer", destroyerDamage);
                damagesPerTurn.put("patrolboat", patrolboatDamage);

                hitsMapPerTurn.put("turn", salvo.getTurn());
                hitsMapPerTurn.put("hitLocations", hitCellsList);
                hitsMapPerTurn.put("locationsMissed", missedCellsList);
                hitsMapPerTurn.put("damages", damagesPerTurn);
                hitsMapPerTurn.put("missed", missedShots);
                hits.add(hitsMapPerTurn);
            }
        }
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

    public GamePlayer getOppo() {
        return this.getGame().getGamePlayers()
                                .stream()
                                .filter(gp -> gp.getId() != this.getId())
                                .findFirst()
                                .orElse(new GamePlayer());
    }

    private List<String>  getLocatiosShip(String type, GamePlayer gamePlayer){
        return  gamePlayer.getShips().size()  ==  0 ? new ArrayList<>() : gamePlayer.getShips().stream().filter(ship -> ship.getType().equals(type)).findFirst().get().getShipLocations();
    }
}


