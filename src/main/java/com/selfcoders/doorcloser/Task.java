package com.selfcoders.doorcloser;

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

        Iterator<Door> iterator = plugin.getDoorList().getDoors().iterator();

        while (iterator.hasNext()) {
            Door door = iterator.next();

            if (door.getTime() <= maxAge) {
                iterator.remove();
                door.close();
            }
        }
    }
}