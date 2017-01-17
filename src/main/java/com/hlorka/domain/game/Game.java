package com.hlorka.domain.game;

import com.hlorka.domain.User;

import java.util.HashSet;
import java.util.Set;

/**
 * A Game.
 */
public class Game extends ObjectWithId {

    private final User host;

    private Set<User> players;

    public Game(int id, User host) {
        super(id);
        this.host = host;
        this.players = new HashSet<>();
    }

    public String getName() {
        return String.format("%s's game", host.getLogin());
    }

    public User getHost() {
        return host;
    }

    public Set<User> getPlayers() {
        return players;
    }

    public void addPlayer(User player) {
        this.players.add(player);
    }

    @Override
    public String toString() {
        return String.format("id=%d, host=%s}", getId(), host);
    }

}
