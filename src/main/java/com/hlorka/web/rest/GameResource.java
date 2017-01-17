package com.hlorka.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hlorka.domain.User;
import com.hlorka.domain.game.Game;
import com.hlorka.service.UserService;
import com.hlorka.service.game.GameService;
import com.hlorka.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Game.
 */
@RestController
@RequestMapping("/api")
public class GameResource {

    private final Logger log = LoggerFactory.getLogger(GameResource.class);

    @Inject
    private GameService gameService;

    @Inject
    private UserService userService;

    @Inject
    private SimpMessageSendingOperations messagingTemplate;

    /**
     * POST  /games : Create a new game.
     *
     * @return the ResponseEntity with status 201 (Created) and with body the new game, or with status 400 (Bad Request) if the game has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/games")
    @Timed
    public ResponseEntity<Game> createGame() throws URISyntaxException {
        log.debug("REST request to create Game.");

        User user = userService.getUserWithAuthorities();
        Game game = gameService.createGame(user);

        return ResponseEntity.created(new URI("/api/games/" + game.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("game", String.valueOf(game.getId())))
            .body(game);
    }


    /**
     * PUT  /games/:id/join : Join an existing game.
     *
     * @param id the id of the game to join
     * @return the ResponseEntity with status 200 (OK) and with body the joined game,
     * or with status 400 (Bad Request) if the game is not valid,
     * or with status 500 (Internal Server Error) if the game couldnt be joined
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/games/{id}/join")
    @Timed
    public ResponseEntity<Game> joinGame(@PathVariable int id) throws URISyntaxException {
        log.debug("REST request to join Game : {}", id);
        User user = userService.getUserWithAuthorities();
        Game game = gameService.joinGame(id, user);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityJoinAlert(user.getFirstName(), game.getName(), String.valueOf(game.getId())))
            .body(game);
    }

    /**
     * GET  /games : get all the games.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of games in body
     */
    @GetMapping("/games")
    @Timed
    public List<Game> getAllGames() {
        log.debug("REST request to get all Games");
        List<Game> games = gameService.getGames();
        return games;
    }

    /**
     * GET  /games/:id : get the "id" game.
     *
     * @param id the id of the game to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the game, or with status 404 (Not Found)
     */
    @GetMapping("/games/{id}")
    @Timed
    public ResponseEntity<Game> getGame(@PathVariable int id) {
        log.debug("REST request to get Game : {}", id);
        Game game = gameService.getGame(id);
        return Optional.ofNullable(game)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /games/:id : delete the "id" game.
     *
     * @param id the id of the game to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/games/{id}")
    @Timed
    public ResponseEntity<Void> deleteGame(@PathVariable int id) {
        log.debug("REST request to delete Game : {}", id);
        gameService.deleteGame(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("game", String.valueOf(id))).build();
    }

}
