package com.codeoftheweb.Salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
		@Bean
		public CommandLineRunner initData(PlayerRepository playerRepository,
										  GameRepository gameRepository,
										  GamePlayerRepository gamePlayerRepository){
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

			};

	}

}

