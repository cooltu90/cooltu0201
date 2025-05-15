package com.codingtu.cooltu.lib4j.es.map;

import java.util.Map;

public class BaseMaps<K, V> extends CoreMaps<K, V, BaseMaps> {

    public BaseMaps() {
    }

    public BaseMaps(Map<K, V> map) {
        super(map);
    }
}
