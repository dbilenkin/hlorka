package com.hlorka.domain;

import com.hlorka.domain.game.Game;
import com.hlorka.domain.game.ObjectWithId;

/**
 * Created by lbilenki on 1/12/2017.
 */
public class Player extends ObjectWithId {
    private final User user;
    private final Game game;

    public Player(int id, User user, Game game) {
        super(id);
        this.user = user;
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public String toString() {
        return String.format("playerId=%d, user=%s, gameId=%d}", getId(), user, game.getId());
    }
}
