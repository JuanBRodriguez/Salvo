package com.codeoftheweb.Salvo.Repositories;

import java.util.Optional;

import com.codeoftheweb.Salvo.Models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlayerRepository extends JpaRepository<Player, Long>{
    Optional<Player> findByUserName(@Param("userName") String userName);
}
