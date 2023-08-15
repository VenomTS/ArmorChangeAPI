package me.venom.armorchange;

import me.venom.armorchange.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorChange extends JavaPlugin
{

    private static final Utils utils = new Utils();

    public static void implementListeners(Plugin plugin)
    {
        Bukkit.getPluginManager().registerEvents(new PlayerInteract(utils), plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryInteract(utils), plugin);
        Bukkit.getPluginManager().registerEvents(new InventoryDrag(), plugin);
        Bukkit.getPluginManager().registerEvents(new DispenserDispenseArmor(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), plugin);
        Bukkit.getPluginManager().registerEvents(new ItemBreak(utils), plugin);
    }
}
