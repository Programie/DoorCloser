package com.selfcoders.doorcloser;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class DoorCloser extends JavaPlugin {
    private final Task task = new Task(this);

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new EventListener(this), this);

        BukkitScheduler scheduler = getServer().getScheduler();

        scheduler.runTaskTimer(this, task, 0, getConfig().getLong("task-timer-interval"));
    }

    public Task getTask() {
        return task;
    }
}