package com.hlorka.event;

import com.hlorka.domain.game.Game;

/**
 * Created by lbilenki on 1/12/2017.
 */
public class GameCreatedEvent extends GameEvent {
    private final Game game;

    public GameCreatedEvent(Game game) {
        super(game.getId());
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
