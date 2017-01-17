package com.hlorka.event;

import com.hlorka.domain.Player;

/**
 * Created by lbilenki on 1/12/2017.
 */
public class GameJoinedEvent extends GameEvent {
    private final Player player;

    public GameJoinedEvent(Player player) {
        super(player.getGame().getId());
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
