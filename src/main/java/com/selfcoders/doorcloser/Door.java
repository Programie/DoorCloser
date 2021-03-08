package com.selfcoders.doorcloser;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;

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
        BlockData blockData = blockState.getBlockData();

        if (!(blockData instanceof Openable)) {
            return;
        }

        Openable openable = (Openable) blockData;

        if (openable.isOpen()) {
            openable.setOpen(false);
            blockState.setBlockData(openable);
            blockState.update();

            doorBlock.getWorld().playSound(doorBlock.getLocation(), Sound.BLOCK_WOODEN_DOOR_CLOSE, 1, 1);
        }
    }
}