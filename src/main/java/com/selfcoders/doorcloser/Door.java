package com.selfcoders.doorcloser;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Openable;

class Door {
    private Block doorBlock;
    private long time;

    Door(Block doorBlock) {
        this.doorBlock = doorBlock;
        this.time = System.currentTimeMillis();
    }

    public Block getDoorBlock() {
        return doorBlock;
    }

    public long getTime() {
        return time;
    }

    public void close() {
        BlockState blockState = doorBlock.getState();
        MaterialData materialData = blockState.getData();

        if (!(materialData instanceof Openable)) {
            return;
        }

        Openable openable = (Openable) materialData;

        openable.setOpen(false);
        blockState.update();

        doorBlock.getWorld().playSound(doorBlock.getLocation(), Sound.BLOCK_WOODEN_DOOR_CLOSE, 1, 1);
    }
}