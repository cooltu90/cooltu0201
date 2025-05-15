package com.codingtu.cooltu.lib4a.bus;

import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.es.Es;

public class BusStation {

    private static ListValueMap<String, Bus> map = new ListValueMap<>();

    public static void add(Bus bus) {
        map.get(bus.getTask()).add(bus);
    }

    public static void remove(Bus bus) {
        map.get(bus.getTask()).remove(bus);
    }

    public static void send(String task, Object obj) {
        Es.es(map.get(task)).ls(new Es.EachEs<Bus>() {
            @Override
            public boolean each(int position, Bus bus) {
                bus.back(obj);
                return false;
            }
        });
    }
}
