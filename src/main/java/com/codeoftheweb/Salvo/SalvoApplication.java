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
									  SalvoRepository salvoRepository,
									  ScoreRepository scoreRepository){
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

			Game g4 = new Game(date1);
			gameRepository.save(g4);

			Game g5 = new Game(date1);
			gameRepository.save(g5);

			Game g6 = new Game(date1);
			gameRepository.save(g6);

			Game g7 = new Game(date1);
			gameRepository.save(g7);

			Game g8 = new Game(date1);
			gameRepository.save(g8);
			//
			GamePlayer gp1 =new GamePlayer(date1,g1, p1);
			GamePlayer gp2 =new GamePlayer(date1,g1, p2);
			//
			GamePlayer gp3 =new GamePlayer(date1,g2, p1);
			GamePlayer gp4 =new GamePlayer(date1,g2, p2);
			//
			GamePlayer gp5 =new GamePlayer(date1,g3, p2);
			GamePlayer gp6 =new GamePlayer(date1,g3, p4);

			GamePlayer gp7 = new GamePlayer(date1, g4, p2);
			GamePlayer gp8 = new GamePlayer(date1, g4, p1);

			GamePlayer gp9 = new GamePlayer(date1, g5, p4);
			GamePlayer gp10 = new GamePlayer(date1, g5, p1);

			GamePlayer gp11 = new GamePlayer(date1, g6, p3);
			GamePlayer gp12 = new GamePlayer(date1, g7, p4);

			GamePlayer gp13 = new GamePlayer(date1, g8, p3);
			GamePlayer gp14 = new GamePlayer(date1, g8, p4);
			gamePlayerRepository.save(gp1);
			gamePlayerRepository.save(gp2);
			gamePlayerRepository.save(gp3);
			gamePlayerRepository.save(gp4);
			gamePlayerRepository.save(gp5);
			gamePlayerRepository.save(gp6);
			gamePlayerRepository.save(gp7);
			gamePlayerRepository.save(gp8);
			gamePlayerRepository.save(gp9);
			gamePlayerRepository.save(gp10);
			gamePlayerRepository.save(gp11);
			gamePlayerRepository.save(gp12);
			gamePlayerRepository.save(gp13);
			gamePlayerRepository.save(gp14);

			//juego 1 1er jugador
			List<String> pos1 = Arrays.asList("H2","H3", "H4");
			Ship s1= new Ship("Destroyer", pos1, gp1);
			List<String> pos2 = Arrays.asList("E1","F1", "G1");
			Ship s2= new Ship("Submarine", pos2, gp1);
			List<String> pos3 = Arrays.asList("B4","B5");
			Ship s3= new Ship("Patrol Boat", pos3, gp1);
			shipRepository.save(s1);
			shipRepository.save(s2);
			shipRepository.save(s3);

			//juego 1 2do jugador
			List<String> pos4 = Arrays.asList("B5","C5", "D5");
			Ship s4= new Ship("Destroyer", pos4, gp2);
			List<String> pos5 = Arrays.asList("F1","F2");
			Ship s5= new Ship("Patrol Boat", pos5, gp2);
			List<String> pos6 = Arrays.asList("B5","C5","D5");
			Ship s6= new Ship("Destroyer", pos6, gp2);
			shipRepository.save(s4);
			shipRepository.save(s5);
			shipRepository.save(s6);

			//juego 1 2do jugador
			List<String> pos7 = Arrays.asList("B5","C5", "D5");
			Ship s7= new Ship("Destroyer", pos4, gp2);
			List<String> pos8 = Arrays.asList("F1","F2");
			Ship s8= new Ship("Patrol Boat", pos5, gp2);
			List<String> pos9 = Arrays.asList("B5","C5","D5");
			Ship S9= new Ship("Destroyer", pos6, gp2);
			shipRepository.save(s7);
			shipRepository.save(s8);
			shipRepository.save(S9);

			//juego 1 2do jugador
			List<String> pos10 = Arrays.asList("B5","C5", "D5");
			Ship s10= new Ship("Destroyer", pos4, gp2);
			List<String> pos11 = Arrays.asList("F1","F2");
			Ship s11= new Ship("Patrol Boat", pos5, gp2);
			List<String> pos12= Arrays.asList("B5","C5","D5");
			Ship s12= new Ship("Destroyer", pos6, gp2);
			shipRepository.save(s10);
			shipRepository.save(s11);
			shipRepository.save(s12);

			//juego 1 2do jugador
			List<String> pos13 = Arrays.asList("B5","C5", "D5");
			Ship s13= new Ship("Destroyer", pos4, gp2);
			List<String> pos14 = Arrays.asList("F1","F2");
			Ship s14= new Ship("Patrol Boat", pos5, gp2);
			List<String> pos15= Arrays.asList("B5","C5","D5");
			Ship s15= new Ship("Destroyer", pos6, gp2);
			shipRepository.save(s13);
			shipRepository.save(s14);
			shipRepository.save(s15);

			//juego 1 2do jugador
			List<String> pos16 = Arrays.asList("B5","C5", "D5");
			Ship s16= new Ship("Destroyer", pos4, gp2);
			List<String> pos17 = Arrays.asList("F1","F2");
			Ship s17= new Ship("Patrol Boat", pos5, gp2);
			List<String> pos18= Arrays.asList("B5","C5","D5");
			Ship s18= new Ship("Destroyer", pos6, gp2);
			shipRepository.save(s16);
			shipRepository.save(s17);
			shipRepository.save(s18);

			//juego 1 2do jugador
			List<String> pos19 = Arrays.asList("B5","C5", "D5");
			Ship s19= new Ship("Destroyer", pos4, gp2);
			List<String> pos20 = Arrays.asList("F1","F2");
			Ship s20= new Ship("Patrol Boat", pos5, gp2);
			List<String> pos21= Arrays.asList("B5","C5","D5");
			Ship s21= new Ship("Destroyer", pos6, gp2);
			shipRepository.save(s19);
			shipRepository.save(s20);
			shipRepository.save(s21);

			//juego 1 2do jugador
			List<String> pos22 = Arrays.asList("B5","C5", "D5");
			Ship s22= new Ship("Destroyer", pos4, gp2);
			List<String> pos23 = Arrays.asList("F1","F2");
			Ship s23= new Ship("Patrol Boat", pos5, gp2);
			List<String> pos24= Arrays.asList("B5","C5","D5");
			Ship s24= new Ship("Destroyer", pos6, gp2);
			shipRepository.save(s22);
			shipRepository.save(s23);
			shipRepository.save(s24);

			//juego 1 2do jugador
			List<String> pos25 = Arrays.asList("B5","C5", "D5");
			Ship s25= new Ship("Destroyer", pos4, gp2);
			List<String> pos26 = Arrays.asList("F1","F2");
			Ship s26= new Ship("Patrol Boat", pos5, gp2);
			List<String> pos27= Arrays.asList("B5","C5","D5");
			Ship s27= new Ship("Destroyer", pos6, gp2);
			shipRepository.save(s25);
			shipRepository.save(s26);
			shipRepository.save(s27);

			Set <String> location7 = new HashSet<String>();
			location7.add("C6");
			location7.add("C7");
			Ship s7 = new Ship("Patrol Boat",gp2,location7 );
			Set <String> location8 = new HashSet<String>();
			location8.add("A2");
			location8.add("A3");
			location8.add("A4");
			Ship s8 = new Ship("Submarine",gp2,location8 );
			Set <String> location9 = new HashSet<String>();
			location9.add("G6");
			location9.add("H6");
			Ship s9 = new Ship("Patrol Boat",gp2,location9);
			Set <String> location10 = new HashSet<String>();
			location10.add("B5");
			location10.add("C5");
			location10.add("D5");
			Ship s10 = new Ship("Destroyer",gp3,location10 );
			Set <String> location11 = new HashSet<String>();
			location11.add("C6");
			location11.add("C7");
			Ship s11 = new Ship("Patrol Boat",gp3,location11 );
			Set <String> location12 = new HashSet<String>();
			location12.add("A2");
			location12.add("A3");
			location12.add("A4");
			Ship s12 = new Ship("Submarine",gp3,location12 );
			Set <String> location13 = new HashSet<String>();
			location13.add("G6");
			location13.add("H6");
			Ship s13 = new Ship("Patrol Boat",gp3,location13 );
			Set <String> location14 = new HashSet<String>();
			location14.add("B5");
			location14.add("C5");
			location14.add("D5");
			Ship s14 = new Ship("Destroyer",gp4,location14 );
			Set <String> location15= new HashSet<String>();
			location15.add("C6");
			location15.add("C7");
			Ship s15 = new Ship("Patrol Boat",gp4,location15 );
			Set <String> location16= new HashSet<String>();
			location16.add("A2");
			location16.add("A3");
			location16.add("A4");
			Ship s16 = new Ship("Submarine",gp4,location16 );
			Set <String> location17 = new HashSet<String>();
			location17.add("G6");
			location17.add("H6");
			Ship s17 = new Ship("Patrol Boat",gp4,location17 );
			Set <String> location18 = new HashSet<String>();
			location18.add("B5");
			location18.add("C5");
			location18.add("D5");
			Ship s18 = new Ship("Destroyer",gp5,location18 );
			Set <String> location19= new HashSet<String>();
			location19.add("C6");
			location19.add("C7");
			Ship s19 = new Ship("Patrol Boat",gp5,location19 );
			Set <String> location20= new HashSet<String>();
			location20.add("A2");
			location20.add("A3");
			location20.add("A4");
			Ship s20 = new Ship("Submarine",gp5,location20);
			Set <String> location21 = new HashSet<String>();
			location21.add("G6");
			location21.add("H6");
			Ship s21 = new Ship("Patrol Boat",gp5,location21 );
			Set <String> location22 = new HashSet<String>();
			location22.add("B5");
			location22.add("C5");
			location22.add("D5");
			Ship s22 = new Ship("Destroyer",gp6,location22 );
			Set <String> location23= new HashSet<String>();
			location23.add("C6");
			location23.add("C7");
			Ship s23 = new Ship("Patrol Boat",gp6,location23 );
			Set <String> location24= new HashSet<String>();
			location24.add("B5");
			location24.add("C5");
			location24.add("D5");
			Ship s24 = new Ship("Destroyer",gp8,location24 );
			Set <String> location25 = new HashSet<String>();
			location25.add("C6");
			location25.add("C7");
			Ship s25 = new Ship("Patrol Boat",gp8,location25 );
			Set <String> location26= new HashSet<String>();
			location26.add("A2");
			location26.add("A3");
			location26.add("A4");
			Ship s26 = new Ship("Submarine",gp8,location26 );
			Set <String> location27 = new HashSet<String>();
			location27.add("G6");
			location27.add("H6");
			Ship s27 = new Ship("Patrol Boat",gp8,location27 );

			shipRepository.save(s7);
			shipRepository.save(s8);
			shipRepository.save(s9);
			shipRepository.save(s10);
			shipRepository.save(s11);
			shipRepository.save(s12);
			shipRepository.save(s13);
			shipRepository.save(s14);
			shipRepository.save(s15);
			shipRepository.save(s16);
			shipRepository.save(s17);
			shipRepository.save(s18);
			shipRepository.save(s20);
			shipRepository.save(s21);
			shipRepository.save(s22);
			shipRepository.save(s23);
			shipRepository.save(s24);
			shipRepository.save(s25);
			shipRepository.save(s26);
			shipRepository.save(s27);

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

			Score score1= new Score(p1,g1,0.5,date2);
			scoreRepository.save(score1);
			Score score2= new Score(p2,g1,0.5,date2);
			scoreRepository.save(score2);

			Score score3= new Score(p3,g2,1.0,date2);
			scoreRepository.save(score3);
			Score score4= new Score(p4,g2,0.0,date2);
			scoreRepository.save(score4);

			Score score5= new Score(p1,g3,1.0,date2);
			scoreRepository.save(score5);
			Score score6= new Score(p2,g3,0.0,date2);
			scoreRepository.save(score6);








			Set <String> locationS1 = new HashSet<String>();
			locationS1.add("B5");
			locationS1.add("C5");
			locationS1.add("F1");
			Salvo salvo1 = new Salvo(1,gp1,locationS1 );
			Set <String> locationS2 = new HashSet<String>();
			locationS2.add("B4");
			locationS2.add("B5");
			locationS2.add("B6");
			Salvo salvo2 = new Salvo(1,gp1,locationS2 );
			Set <String> locationS3 = new HashSet<String>();
			locationS3.add("F2");
			locationS3.add("D5");
			Salvo salvo3 = new Salvo(2,gp1,locationS3 );
			Set <String> locationS4 = new HashSet<String>();
			locationS4.add("E1");
			locationS4.add("H3");
			locationS4.add("A2");
			Salvo salvo4 = new Salvo(2,gp1,locationS4 );
			Set <String> locationS5 = new HashSet<String>();
			locationS5.add("A2");
			locationS5.add("A4");
			locationS5.add("G6");
			Salvo salvo5 = new Salvo(1,gp2,locationS5 );
			Set <String> locationS6 = new HashSet<String>();
			locationS6.add("B5");
			locationS6.add("D5");
			locationS6.add("C7");
			Salvo salvo6 = new Salvo(1,gp2,locationS6 );
			Set <String> locationS7 = new HashSet<String>();
			locationS7.add("A3");
			locationS7.add("H6");
			Salvo salvo7 = new Salvo(2,gp2,locationS7 );
			Set <String> locationS8 = new HashSet<String>();
			locationS8.add("C5");
			locationS8.add("C6");
			Salvo salvo8 = new Salvo(2,gp2,locationS8 );
			Set <String> locationS9 = new HashSet<String>();
			locationS9.add("G6");
			locationS9.add("H6");
			locationS9.add("A4");
			Salvo salvo9 = new Salvo(1,gp3,locationS9);
			Set <String> locationS10 = new HashSet<String>();
			locationS10.add("H1");
			locationS10.add("H2");
			locationS10.add("H3");
			Salvo salvo10 = new Salvo(1,gp3,locationS10 );
			Set <String> locationS11 = new HashSet<String>();
			locationS11.add("A2");
			locationS11.add("A3");
			locationS11.add("D8");
			Salvo salvo11 = new Salvo(2,gp3,locationS11 );
			Set <String> locationS12 = new HashSet<String>();
			locationS12.add("E1");
			locationS12.add("F2");
			locationS12.add("G3");
			Salvo salvo12 = new Salvo(2,gp3,locationS12 );
			Set <String> locationS13 = new HashSet<String>();
			locationS13.add("A3");
			locationS13.add("A4");
			locationS13.add("F7");
			Salvo salvo13 = new Salvo(1,gp4,locationS13 );
			Set <String> locationS14 = new HashSet<String>();
			locationS14.add("B5");
			locationS14.add("C6");
			locationS14.add("H1");
			Salvo salvo14 = new Salvo(1,gp4,locationS14 );
			Set <String> locationS15= new HashSet<String>();
			locationS15.add("A2");
			locationS15.add("G6");
			locationS15.add("H6");
			Salvo salvo15 = new Salvo(2,gp4,locationS15 );
			Set <String> locationS16= new HashSet<String>();
			locationS16.add("C5");
			locationS16.add("C7");
			locationS16.add("D5");
			Salvo salvo16 = new Salvo(2,gp4,locationS16 );
			Set <String> locationS17 = new HashSet<String>();
			locationS17.add("A1");
			locationS17.add("A2");
			locationS17.add("A3");
			Salvo salvo17 = new Salvo(1,gp5,locationS17 );
			Set <String> locationS18 = new HashSet<String>();
			locationS18.add("B5");
			locationS18.add("B6");
			locationS18.add("C7");
			Salvo salvo18 = new Salvo(1,gp5,locationS18 );
			Set <String> locationS19= new HashSet<String>();
			location19.add("G6");
			location19.add("G7");
			location19.add("G8");
			Salvo salvo19 = new Salvo(2,gp5,locationS19 );
			Set <String> locationS20= new HashSet<String>();
			locationS20.add("C6");
			locationS20.add("D6");
			locationS20.add("E6");
			Salvo salvo20 = new Salvo(2,gp5,locationS20);
			Set <String> locationS21 = new HashSet<String>();
			locationS21.add("H1");
			locationS21.add("H8");
			Salvo salvo21 = new Salvo(3,gp5,locationS21 );
			salvoRepository.save(salvo1);
			salvoRepository.save(salvo2);
			salvoRepository.save(salvo3);
			salvoRepository.save(salvo4);
			salvoRepository.save(salvo5);
			salvoRepository.save(salvo6);
			salvoRepository.save(salvo7);
			salvoRepository.save(salvo8);
			salvoRepository.save(salvo9);
			salvoRepository.save(salvo10);
			salvoRepository.save(salvo11);
			salvoRepository.save(salvo12);
			salvoRepository.save(salvo13);
			salvoRepository.save(salvo14);
			salvoRepository.save(salvo15);
			salvoRepository.save(salvo16);
			salvoRepository.save(salvo17);
			salvoRepository.save(salvo18);
			salvoRepository.save(salvo19);
			salvoRepository.save(salvo20);
			salvoRepository.save(salvo21);
		};
	}
}

