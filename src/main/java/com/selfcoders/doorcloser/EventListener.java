package com.selfcoders.doorcloser;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Openable;

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

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        MaterialData blockStateData = block.getState().getData();

        if (!(blockStateData instanceof Openable)) {
            return;
        }

        Openable door = (Openable) blockStateData;

        List<String> doors = plugin.getConfig().getStringList("doors");

        if (!doors.contains(block.getType().name())) {
            return;
        }

        if (door.isOpen()) {
            // Door is open and will be closed

            plugin.getTask().remove(block);
        } else {
            // Door is closed and will be opened

            plugin.getTask().add(block);
        }
    }
}