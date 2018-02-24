package com.javasucks.bloom;


public class Bloom {
    // The number of hash functions
    int k;
    // The number of bits in the filter
    int m;
    // The bit array
    boolean[] bits;

    /**
     * A bloom filter is a probabilistic datastructure.
     *
     * @param k The number of hash functions to use. Must be greater than or equal to 2.
     * @param m The number of bits in the bloom filter. We use a byte array for speed, though.
     */
    public Bloom(int k, int m) {
        if (k < 2) {
            throw new IllegalArgumentException("k must be >= 2");
        }
        this.k = k;
        this.m = m;
        this.bits = new boolean[this.m];
    }

    private int hash(byte[] item, int seed) {
        switch (seed) {
            case 0:
                return Murmur3.hash32(item, item.length, seed);
            case 1:
                return Murmur3.hash32(item, item.length, seed);
            default:
                int hash0 = Murmur3.hash32(item, item.length, 0);
                int hash1 = Murmur3.hash32(item, item.length, 1);
                return hash0 + seed * hash1;
        }
    }

    private int pos(byte[] item, int seed) {
        return Math.abs(hash(item, seed) % m);
    }

    public void add(byte[] item) {
        for (int i = 0; i < k; i++) {
            bits[(pos(item, i))] = true;
        }
    }

    public boolean contains(byte[] item) {
        for (int i = 0; i < k; i++) {
            if (!bits[pos(item, i)]) {
                return false;
            }
        }
        return true;
    }
}
