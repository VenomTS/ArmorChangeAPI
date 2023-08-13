package me.venom.armorequip;

import me.venom.armorequip.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorEquip extends JavaPlugin {

    @Override
    public void onEnable() { implementListeners(); }

    @Override
    public void onDisable()
    {
    }

    private void implementListeners()
    {
        Bukkit.getPluginManager().registerEvents(new ArmorEquipEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteract(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryInteract(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryDrag(), this);
        Bukkit.getPluginManager().registerEvents(new DispenserDispenseArmor(), this);
        Bukkit.getPluginManager().registerEvents(new JoinDeath(), this);
        Bukkit.getPluginManager().registerEvents(new ItemBreak(), this);
    }

}
