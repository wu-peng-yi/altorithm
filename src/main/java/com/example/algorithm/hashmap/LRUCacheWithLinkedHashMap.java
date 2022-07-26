package com.example.algorithm.hashmap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author W
 * @date 2022-07-19
 */
public class LRUCacheWithLinkedHashMap extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCacheWithLinkedHashMap(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        if (super.get(key) == null) {
            return -1;
        }
        return super.get(key);
    }

    //put
    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
