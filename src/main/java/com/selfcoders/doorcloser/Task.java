package com.selfcoders.doorcloser;

import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Task implements Runnable {
    private final DoorCloser plugin;
    private final List<Door> doors = new ArrayList<>();

    Task(DoorCloser plugin) {
        this.plugin = plugin;
    }

    public void add(Block doorBlock) {
        Door door = new Door(doorBlock);

        doors.add(door);
    }

    public void remove(Block doorBlock) {
        Iterator<Door> iterator = doors.iterator();

        while (iterator.hasNext()) {
            Door door = iterator.next();

            if (door.getDoorBlock() == doorBlock) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public void run() {
        long now = System.currentTimeMillis();
        long timeout = plugin.getConfig().getLong("timeout") * 1000;

        long maxAge = now - timeout;

        Iterator<Door> iterator = doors.iterator();

        while (iterator.hasNext()) {
            Door door = iterator.next();

            if (door.getTime() <= maxAge) {
                iterator.remove();
                door.close();
            }
        }
    }

    public List<Door> getDoors() {
        return doors;
    }
}