package com.hlorka.event;

/**
 * Created by lbilenki on 1/12/2017.
 */
public abstract class GameEvent {
    protected final int gameId;

    protected GameEvent(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }
}
