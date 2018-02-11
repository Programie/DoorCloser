package com.selfcoders.doorcloser;

import org.bukkit.block.Block;

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
}