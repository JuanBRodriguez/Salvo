package com.codeoftheweb.Salvo.Repositories;

//import java.util.List;
import com.codeoftheweb.Salvo.Models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GameRepository extends JpaRepository<Game, Long> {
    //List<Game> findByUserName(String userName);
}
