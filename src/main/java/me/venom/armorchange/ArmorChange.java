package me.venom.armorchange;

import me.venom.armorchange.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorChange extends JavaPlugin
{

    public ArmorChange() { implementListeners(); }

    private final Utils utils = new Utils();

    private void implementListeners()
    {
        Bukkit.getPluginManager().registerEvents(new PlayerInteract(utils), this);
        Bukkit.getPluginManager().registerEvents(new InventoryInteract(utils), this);
        Bukkit.getPluginManager().registerEvents(new InventoryDrag(), this);
        Bukkit.getPluginManager().registerEvents(new DispenserDispenseArmor(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
        Bukkit.getPluginManager().registerEvents(new ItemBreak(utils), this);
    }
}
