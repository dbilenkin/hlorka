package com.hlorka.service;

/**
 * Created by lbilenki on 1/12/2017.
 */
public class IntSequence {
    private int seed;

    public IntSequence() {
        this(0);
    }

    public IntSequence(int seed) {
        this.seed = seed;
    }

    public int next() {
        return seed++;
    }
}
