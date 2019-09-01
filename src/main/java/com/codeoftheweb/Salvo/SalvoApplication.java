package com.codeoftheweb.Salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository,
									  GameRepository gameRepository,
									  GamePlayerRepository gamePlayerRepository,
									  ShipRepository shipRepository,
									  SalvoRepository salvoRepository){
		return (args) -> {

			Player p1= new Player("j.bauer@ctu.gov");
			playerRepository.save(p1);
			Player p2= new Player("c.obrian@ctu.gov");
			playerRepository.save(p2);
			Player p3= new Player("kim_bauer@gmail.gov");
			playerRepository.save(p3);
			Player p4= new Player("t.almeida@ctu.gov");
			playerRepository.save(p4);
			Player p5= new Player("jbcrodriguezsud@gmail.com");
			playerRepository.save(p5);

			Date date1 = new Date();
			Game g1 = new Game(date1);
			gameRepository.save(g1);

			Date date2 = Date.from(date1.toInstant().plusSeconds(3600));
			Game g2 = new Game(date1);
			gameRepository.save(g2);

			Date date3 = Date.from(date2.toInstant().plusSeconds(3600));
			Game g3 = new Game(date3);
			gameRepository.save(g3);

			//
			GamePlayer gp1 =new GamePlayer(date1,g1, p1);
			GamePlayer gp2 =new GamePlayer(date1,g1, p2);
			//
			GamePlayer gp3 =new GamePlayer(date1,g2, p1);
			GamePlayer gp4 =new GamePlayer(date1,g2, p2);
			//
			GamePlayer gp5 =new GamePlayer(date1,g3, p2);
			GamePlayer gp6 =new GamePlayer(date1,g3, p4);

			gamePlayerRepository.save(gp1);
			gamePlayerRepository.save(gp2);
			gamePlayerRepository.save(gp3);
			gamePlayerRepository.save(gp4);
			gamePlayerRepository.save(gp5);
			gamePlayerRepository.save(gp6);

			//juego 1 1er jugador
			List<String> pos1 = Arrays.asList("A1","A2", "A3");
			Ship s1= new Ship("Submarine", pos1, gp1);
			List<String> pos2 = Arrays.asList("B1","B2", "B3");
			Ship s2= new Ship("Destroyer", pos2, gp1);
			List<String> pos3 = Arrays.asList("F1","F2");
			Ship s3= new Ship("Patrol Boat", pos3, gp1);
			shipRepository.save(s1);
			shipRepository.save(s2);
			shipRepository.save(s3);

			//juego 1 2do jugador
			List<String> pos4 = Arrays.asList("A1","B1", "C3");
			Ship s4= new Ship("Submarine", pos4, gp2);
			List<String> pos5 = Arrays.asList("B8","B9", "B10");
			Ship s5= new Ship("Destroyer", pos5, gp2);
			List<String> pos6 = Arrays.asList("F11","F12");
			Ship s6= new Ship("Patrol Boat", pos6, gp2);
			shipRepository.save(s4);
			shipRepository.save(s5);
			shipRepository.save(s6);

			//salvo game 1 turn 1
			List<String> po1 = Arrays.asList("B5","C5", "F1");
			Salvo sal1 = new Salvo(gp1, 1, po1);
			List<String> po2 = Arrays.asList("B4","B5", "B6");
			Salvo sal2 = new Salvo(gp2, 1, po2);
			//salvo game 1 turn 2
			List<String> po3 = Arrays.asList("F2","D5");
			Salvo sal3 = new Salvo(gp1, 2, po3);
			List<String> po4 = Arrays.asList("E1","H3", "A2");
			Salvo sal4 = new Salvo(gp2, 2, po4);

			salvoRepository.save(sal1);
			salvoRepository.save(sal2);
			salvoRepository.save(sal3);
			salvoRepository.save(sal4);
		};
	}
}

