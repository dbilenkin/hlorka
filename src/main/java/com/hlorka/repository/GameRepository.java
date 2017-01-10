package com.hlorka.repository;

import com.hlorka.domain.Game;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Game entity.
 */
@SuppressWarnings("unused")
public interface GameRepository extends JpaRepository<Game,Long> {

    @Query("select distinct game from Game game left join fetch game.users")
    List<Game> findAllWithEagerRelationships();

    @Query("select game from Game game left join fetch game.users where game.id =:id")
    Game findOneWithEagerRelationships(@Param("id") Long id);

}
