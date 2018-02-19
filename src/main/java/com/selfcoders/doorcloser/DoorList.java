package com.selfcoders.doorcloser;

import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DoorList {
    private final List<Door> doors = new ArrayList<>();

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

    public List<Door> getDoors() {
        return doors;
    }
}