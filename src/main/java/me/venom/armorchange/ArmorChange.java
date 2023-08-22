package me.venom.armorchange;

import me.venom.armorchange.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public final class ArmorChange
{

    public ArmorChange(Plugin plugin) { implementListeners(plugin); }

    private final Utils utils = new Utils();

    private void implementListeners(Plugin plugin)
    {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new PlayerInteract(utils), plugin);
        manager.registerEvents(new InventoryInteract(utils), plugin);
        manager.registerEvents(new InventoryDrag(), plugin);
        manager.registerEvents(new DispenserDispenseArmor(), plugin);
        manager.registerEvents(new PlayerDeath(), plugin);
        manager.registerEvents(new ItemBreak(utils), plugin);
    }
}
