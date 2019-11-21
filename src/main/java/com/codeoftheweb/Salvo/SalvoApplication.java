package com.codeoftheweb.Salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository,
									  GameRepository gameRepository,
									  GamePlayerRepository gamePlayerRepository,
									  ShipRepository shipRepository,
									  SalvoRepository salvoRepository,
									  ScoreRepository scoreRepository){
		return (args) -> {

			Player p1 = new Player("j.bauer@ctu.gov", passwordEncoder().encode("1234"));
			playerRepository.save(p1);
			Player p2 = new Player("c.obrian@ctu.gov", passwordEncoder().encode("1234"));
			playerRepository.save(p2);
			Player p3 = new Player("kim_bauer@gmail.gov", passwordEncoder().encode("1234"));
			playerRepository.save(p3);
			Player p4 = new Player("t.almeida@ctu.gov", passwordEncoder().encode("1234"));
			playerRepository.save(p4);
			Player p5 = new Player("jbcrodriguezsud@gmail.com", passwordEncoder().encode("1234"));
			playerRepository.save(p5);

			Date date1 = new Date();
			Game g1 = new Game();
			gameRepository.save(g1);

			Date date2 = Date.from(date1.toInstant().plusSeconds(3600));
			Game g2 = new Game();
			gameRepository.save(g2);

			Date date3 = Date.from(date2.toInstant().plusSeconds(3600));
			Game g3 = new Game();
			gameRepository.save(g3);

			Game g4 = new Game();
			gameRepository.save(g4);

			Game g5 = new Game();
			gameRepository.save(g5);

			Game g6 = new Game();
			gameRepository.save(g6);

			Game g7 = new Game();
			gameRepository.save(g7);

			Game g8 = new Game();
			gameRepository.save(g8);
			//
			GamePlayer gp1 = new GamePlayer(g1, p1);
			GamePlayer gp2 = new GamePlayer(g1, p2);
			//
			GamePlayer gp3 = new GamePlayer(g2, p1);
			GamePlayer gp4 = new GamePlayer(g2, p2);
			//
			GamePlayer gp5 = new GamePlayer(g3, p2);
			GamePlayer gp6 = new GamePlayer(g3, p4);

			GamePlayer gp7 = new GamePlayer(g4, p2);
			GamePlayer gp8 = new GamePlayer(g4, p1);

			GamePlayer gp9 = new GamePlayer(g5, p4);
			GamePlayer gp10 = new GamePlayer(g5, p1);

			GamePlayer gp11 = new GamePlayer(g6, p3);

			GamePlayer gp12 = new GamePlayer(g7, p4);

			GamePlayer gp13 = new GamePlayer(g8, p3);
			GamePlayer gp14 = new GamePlayer(g8, p4);
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
			List<String> pos1 = Arrays.asList("H2", "H3", "H4");
			Ship s1 = new Ship("destroyer", pos1, gp1);
			List<String> pos2 = Arrays.asList("E1", "F1", "G1");
			Ship s2 = new Ship("submarine", pos2, gp1);
			List<String> pos3 = Arrays.asList("B4", "B5");
			Ship s3 = new Ship("patrolboat", pos3, gp1);
			shipRepository.save(s1);
			shipRepository.save(s2);
			shipRepository.save(s3);

			//juego 1 2do jugador
			List<String> pos4 = Arrays.asList("B5", "C5", "D5");
			Ship s4 = new Ship("destroyer", pos4, gp2);
			List<String> pos5 = Arrays.asList("F1", "F2");
			Ship s5 = new Ship("patrolboat", pos5, gp2);
			shipRepository.save(s4);
			shipRepository.save(s5);

			//juego 2 1er jugador
			List<String> pos6 = Arrays.asList("B5", "C5", "D5");
			Ship s6 = new Ship("destroyer", pos6, gp3);
			List<String> pos7 = Arrays.asList("C6", "C7");
			Ship s7 = new Ship("patrolboat", pos7, gp3);
			shipRepository.save(s6);
			shipRepository.save(s7);

			//juego 2 2 jugador

			List<String> pos8 = Arrays.asList("A2", "A3", "A4");
			Ship s8 = new Ship("submarine", pos8, gp4);
			shipRepository.save(s8);
			List<String> pos9 = Arrays.asList("G6", "H6");
			Ship S9 = new Ship("patrolboat", pos9, gp4);
			shipRepository.save(S9);

			//juego 3 1 jugador
			List<String> pos10 = Arrays.asList("B5", "C5", "D5");
			Ship s10 = new Ship("destroyer", pos10, gp5);
			List<String> pos11 = Arrays.asList("C6", "C7");
			Ship s11 = new Ship("patrolboat", pos11, gp5);
			shipRepository.save(s10);
			shipRepository.save(s11);

			//juego 3 2 jugador
			List<String> pos12 = Arrays.asList("A2", "A3", "A4");
			Ship s12 = new Ship("submarine", pos12, gp6);
			shipRepository.save(s12);
			List<String> pos13 = Arrays.asList("G6", "H6");
			Ship s13 = new Ship("destroyer", pos13, gp6);
			shipRepository.save(s13);

			//juego 4 1 jugador
			List<String> pos14 = Arrays.asList("B5", "C5", "D5");
			Ship s14 = new Ship("destroyer", pos14, gp7);
			List<String> pos15 = Arrays.asList("C6", "C7");
			Ship s15 = new Ship("patrolboat", pos15, gp7);
			shipRepository.save(s14);
			shipRepository.save(s15);

			//juego 4 2 jugador
			List<String> pos16 = Arrays.asList("A2", "A3", "A4");
			Ship s16 = new Ship("destroyer", pos16, gp8);
			List<String> pos17 = Arrays.asList("G6", "H6");
			Ship s17 = new Ship("patrolboat", pos17, gp8);
			shipRepository.save(s16);
			shipRepository.save(s17);

			//juego 5 1 jugador
			List<String> pos18 = Arrays.asList("B5", "C5", "D5");
			Ship s18 = new Ship("destroyer", pos18, gp9);
			List<String> pos19 = Arrays.asList("C6", "C7");
			Ship s19 = new Ship("patrolboat", pos19, gp9);
			shipRepository.save(s18);
			shipRepository.save(s19);

			//juego 5 2 jugador
			List<String> pos20 = Arrays.asList("A2", "A3", "A4");
			Ship s20 = new Ship("submarine", pos20, gp10);
			List<String> pos21 = Arrays.asList("G6", "H6");
			Ship s21 = new Ship("patrolboat", pos21, gp10);
			shipRepository.save(s20);
			shipRepository.save(s21);

			//juego 6 1 jugador
			List<String> pos22 = Arrays.asList("B5", "C5", "D5");
			Ship s22 = new Ship("destroyer", pos22, gp11);
			List<String> pos23 = Arrays.asList("C6", "C7");
			Ship s23 = new Ship("patrolboat", pos23, gp11);
			shipRepository.save(s22);
			shipRepository.save(s23);

			//juego 8 1 jugador
			List<String> pos24 = Arrays.asList("B5", "C5", "D5");
			Ship s24 = new Ship("destroyer", pos24, gp13);
			List<String> pos25 = Arrays.asList("C6", "C7");
			Ship s25 = new Ship("patrolboat", pos25, gp13);
			shipRepository.save(s24);
			shipRepository.save(s25);

			//juego 8 2 jugador
			List<String> pos26 = Arrays.asList("A2", "A3", "A4");
			Ship s26 = new Ship("submarine", pos26, gp14);
			List<String> pos27 = Arrays.asList("G6", "H6");
			Ship s27 = new Ship("patrolboat", pos27, gp14);
			shipRepository.save(s26);
			shipRepository.save(s27);

			//******************************SALVOS*****************************************
			//salvo game 1 turn 1
			List<String> po1 = Arrays.asList("B5", "C5", "F1");
			Salvo sal1 = new Salvo(gp1, 1, po1);
			List<String> po2 = Arrays.asList("B4", "B5", "B6");
			Salvo sal2 = new Salvo(gp2, 1, po2);
			salvoRepository.save(sal1);
			salvoRepository.save(sal2);

			//salvo game 1 turn 2
			List<String> po3 = Arrays.asList("F2", "D5");
			Salvo sal3 = new Salvo(gp1, 2, po3);
			List<String> po4 = Arrays.asList("E1", "H3", "A2");
			Salvo sal4 = new Salvo(gp2, 2, po4);
			salvoRepository.save(sal3);
			salvoRepository.save(sal4);

			//salvo game 2 turn 1
			List<String> po5 = Arrays.asList("A2", "A4", "G6");
			Salvo sal5 = new Salvo(gp3, 2, po5);
			List<String> po6 = Arrays.asList("B5", "D5", "C7");
			Salvo sal6 = new Salvo(gp4, 2, po6);
			salvoRepository.save(sal5);
			salvoRepository.save(sal6);

			//salvo game 2 turn 2
			List<String> po7 = Arrays.asList("A3", "H6");
			Salvo sal7 = new Salvo(gp3, 2, po7);
			List<String> po8 = Arrays.asList("C5", "C6");
			Salvo sal8 = new Salvo(gp4, 2, po8);
			salvoRepository.save(sal7);
			salvoRepository.save(sal8);

			//salvo game 3 turn 1
			List<String> po9 = Arrays.asList("G6", "H6", "A4");
			Salvo sal9 = new Salvo(gp5, 2, po9);
			List<String> po10 = Arrays.asList("H1", "H2", "H3");
			Salvo sal10 = new Salvo(gp6, 2, po10);
			salvoRepository.save(sal9);
			salvoRepository.save(sal10);

			//salvo game 3 turn 2
			List<String> po11 = Arrays.asList("A2", "A3", "D8");
			Salvo sal11 = new Salvo(gp5, 2, po11);
			List<String> po12 = Arrays.asList("E1", "F2", "G3");
			Salvo sal12 = new Salvo(gp6, 2, po12);
			salvoRepository.save(sal11);
			salvoRepository.save(sal12);

			//salvo game 4 turn 1
			List<String> po13 = Arrays.asList("A3", "A4", "F7");
			Salvo sal13 = new Salvo(gp7, 2, po13);
			List<String> po14 = Arrays.asList("B5", "C6", "H1");
			Salvo sal14 = new Salvo(gp8, 2, po14);
			salvoRepository.save(sal13);
			salvoRepository.save(sal14);

			//salvo game 4 turn 2
			List<String> po15 = Arrays.asList("A2", "G6", "H6");
			Salvo sal15 = new Salvo(gp7, 2, po15);
			List<String> po16 = Arrays.asList("C5", "C7", "D5");
			Salvo sal16 = new Salvo(gp8, 2, po16);
			salvoRepository.save(sal15);
			salvoRepository.save(sal16);


			//salvo game 5 turn 1
			List<String> po17 = Arrays.asList("A1","A2", "A3");
			Salvo sal17 = new Salvo(gp9, 2, po17);
			List<String> po18 = Arrays.asList("B5","B6", "C7");
			Salvo sal18 = new Salvo(gp10, 2, po18);
			salvoRepository.save(sal17);
			salvoRepository.save(sal18);

			//salvo game 5 turn 2
			List<String> po19 = Arrays.asList("G6","G7", "G8");
			Salvo sal19 = new Salvo(gp9, 2, po19);
			List<String> po20 = Arrays.asList("C6","D6", "E6");
			Salvo sal20 = new Salvo(gp10, 2, po20);
			salvoRepository.save(sal19);
			salvoRepository.save(sal20);

			//salvo game 5 turn 3
			List<String> po21 = Arrays.asList("H1","H8");
			Salvo sal21 = new Salvo(gp10, 2, po21);
			salvoRepository.save(sal21);

			//*************************************SCORESS******************************************


			Score score1= new Score(p1,g1,1.0,date2);
			scoreRepository.save(score1);
			Score score2= new Score(p2,g1,0.0,date2);
			scoreRepository.save(score2);

			Score score3= new Score(p1,g2,0.5,date2);
			scoreRepository.save(score3);
			Score score4= new Score(p2,g2,0.5,date2);
			scoreRepository.save(score4);

			Score score5= new Score(p2,g3,1.0,date2);
			scoreRepository.save(score5);
			Score score6= new Score(p4,g3,0.0,date2);
			scoreRepository.save(score6);

			Score score7= new Score(p1,g4,0.5,date2);
			scoreRepository.save(score7);
			Score score8= new Score(p2,g4,0.5,date2);
			scoreRepository.save(score8);

			Score score9= new Score(p1,g5,0.5,date2);
			scoreRepository.save(score9);
			Score score10= new Score(p4,g5,0.5,date2);
			scoreRepository.save(score10);





		};
	}
}

//******************************************

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	PlayerRepository playerRepository;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inputName-> {
			Player player = playerRepository.findByUserName(inputName).get();
			if (player != null) {
				return new User(player.getUserName(), player.getPassword(),
								AuthorityUtils.createAuthorityList("USER"));
			} else {
				throw new UsernameNotFoundException("Unknown user: " + inputName);
			}
		});
	}
}

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
						//.antMatchers("/admin/**").hasAuthority("ADMIN")
						.antMatchers("/web/games.html").permitAll()
						.antMatchers("/web/**").permitAll()
						.antMatchers("/api/games").permitAll()
						.antMatchers("/api/players").permitAll()
						.antMatchers("/api/game_view/*").hasAuthority("USER")
						.antMatchers("/rest").denyAll()
						.anyRequest().permitAll();
		http.formLogin()
						.usernameParameter("username")
						.passwordParameter("password")
						.loginPage("/api/login");

		http.logout().logoutUrl("/api/logout");

		// turn off checking for CSRF tokens
		http.csrf().disable();

		// if user is not authenticated, just send an authentication failure response
		http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if login is successful, just clear the flags asking for authentication
		http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

		// if login fails, just send an authentication failure response
		http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

		// if logout is successful, just send a success response
		http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
	}
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}
}
