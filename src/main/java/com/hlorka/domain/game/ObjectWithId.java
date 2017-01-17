package com.hlorka.domain.game;

/**
 * Created by lbilenki on 1/13/2017.
 */
public abstract class ObjectWithId {
    private final int id;

    protected ObjectWithId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
