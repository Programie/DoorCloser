package com.selfcoders.doorcloser;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Openable;

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

                Block block = door.getDoorBlock();

                BlockState blockState = block.getState();
                MaterialData materialData = blockState.getData();

                if (!(materialData instanceof Openable)) {
                    continue;
                }

                Openable openable = (Openable) materialData;

                openable.setOpen(false);
                blockState.update();

                block.getWorld().playSound(block.getLocation(), Sound.BLOCK_WOODEN_DOOR_CLOSE, 1, 1);
            }
        }
    }
}