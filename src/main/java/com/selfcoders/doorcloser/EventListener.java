package com.selfcoders.doorcloser;

import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class EventListener implements Listener {
    private final DoorCloser plugin;

    EventListener(DoorCloser pluginInstance) {
        plugin = pluginInstance;
    }

    @EventHandler
    public void onPlayer(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Block block = event.getClickedBlock();
        BlockData blockData = block.getState().getBlockData();

        if (!(blockData instanceof Openable)) {
            return;
        }

        Openable doorBlockData = (Openable) blockData;

        List<String> doors = plugin.getConfig().getStringList("doors");

        if (doors.size() > 0 && !doors.contains(block.getType().name())) {
            return;
        }

        if (doorBlockData.isOpen()) {
            // Door is open and will be closed

            plugin.getDoorList().remove(block);
        } else {
            // Door is closed and will be opened

            plugin.getDoorList().add(block);
        }
    }

    @EventHandler
    public void onChunkUnloaded(ChunkUnloadEvent event) {
        Chunk chunk = event.getChunk();
        HashMap<Integer, Door> doorList = plugin.getDoorList().getDoors();
        Iterator<Integer> iterator = doorList.keySet().iterator();

        while (iterator.hasNext()) {
            Integer key = iterator.next();
            Door door = doorList.get(key);

            if (door.getDoorBlock().getChunk() == chunk) {
                iterator.remove();
                door.close();
            }
        }
    }
}