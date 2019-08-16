package com.codeoftheweb.Salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {

    private String userName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

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

    //Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
