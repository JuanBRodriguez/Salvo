package com.codeoftheweb.Salvo.Repositories;

import com.codeoftheweb.Salvo.Models.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScoreRepository extends JpaRepository<Score, Long>{
}
