package com.selfcoders.doorcloser;

import java.util.HashMap;
import java.util.Iterator;

public class Task implements Runnable {
    private final DoorCloser plugin;

    Task(DoorCloser plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        long now = System.currentTimeMillis();
        long timeout = plugin.getConfig().getLong("timeout") * 1000;

        long maxAge = now - timeout;

        HashMap<Integer, Door> doorList = plugin.getDoorList().getDoors();
        Iterator<Integer> iterator = doorList.keySet().iterator();

        while (iterator.hasNext()) {
            Integer key = iterator.next();
            Door door = doorList.get(key);

            if (door.getTime() <= maxAge) {
                door.close();
                iterator.remove();
            }
        }
    }
}