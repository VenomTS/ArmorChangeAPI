package me.venom.armorequip;

import me.venom.armorequip.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorEquip extends JavaPlugin {

    @Override
    public void onEnable() { sendLogoMessage(); implementListeners(); }

    @Override
    public void onDisable()
    {
    }

    private void sendLogoMessage()
    {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage(ChatColor.GREEN + "<==========[ " + ChatColor.WHITE + "Armor Equip" + ChatColor.GREEN + " ]==========>");
        console.sendMessage(ChatColor.GREEN + "<==========[ " + ChatColor.WHITE + "By VenomTS" + ChatColor.GREEN + " ]==========>");
    }

    private void implementListeners()
    {
        Bukkit.getPluginManager().registerEvents(new PlayerInteract(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryInteract(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryDrag(), this);
        Bukkit.getPluginManager().registerEvents(new DispenserDispenseArmor(), this);
        Bukkit.getPluginManager().registerEvents(new JoinDeath(), this);
        Bukkit.getPluginManager().registerEvents(new ItemBreak(), this);
    }

}
