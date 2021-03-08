package com.selfcoders.doorcloser;

import org.bukkit.block.Block;

import java.util.HashMap;

public class DoorList {
    private final HashMap<Integer, Door> doors = new HashMap<>();

    public void add(Block doorBlock) {
        Door door = new Door(doorBlock);

        doors.put(doorBlock.hashCode(), door);
    }

    public void remove(Block doorBlock) {
        doors.remove(doorBlock.hashCode());
    }

    public HashMap<Integer, Door> getDoors() {
        return doors;
    }
}