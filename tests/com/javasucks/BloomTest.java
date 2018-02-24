package com.javasucks;

import old.Bloom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloomTest {

    @Test
    void testItReturnsFalseForItemsNotInTheFilter() {
        Bloom filter = new Bloom(5, 2048);
        assertEquals(false, filter.contains("whales".getBytes()));
    }

    @Test
    void testItReturnsTrueForItemsInTheFilter() {  // probably...
        Bloom filter = new Bloom(5, 2048);
        filter.add("whales".getBytes());
        assertEquals(true, filter.contains("whales".getBytes()));
    }
}