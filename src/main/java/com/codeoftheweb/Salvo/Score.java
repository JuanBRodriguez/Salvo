package com.codeoftheweb.Salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
public class Score {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
  @GenericGenerator(name = "native", strategy = "native")
  private long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="playerId")
  private Player player;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name="score")
  private Game game;

  private Double score;

  private Date finishDate;

  public Score() {
  }

  public Score(Player player, Game game, Double score, Date finishDate) {
    this.player = player;
    this.game = game;
    this.score = score;
    this.finishDate = finishDate;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public void setFiishDate(Date fiishDate) {
    this.finishDate = finishDate;
  }
  //********************
  public long getId() {
    return id;
  }

  public Player getPlayer() {
    return player;
  }

  public Game getGame() {
    return game;
  }

  public Double getScore() {
    return score;
  }

  public Date getFiishDate() {
    return finishDate;
  }


}
