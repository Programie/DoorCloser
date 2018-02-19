package com.selfcoders.doorcloser;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class DoorCloser extends JavaPlugin {
    private final DoorList doorList = new DoorList();

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new EventListener(this), this);

        BukkitScheduler scheduler = getServer().getScheduler();

        scheduler.runTaskTimer(this, new Task(this), 0, getConfig().getLong("task-timer-interval"));
    }

    public DoorList getDoorList() {
        return doorList;
    }
}