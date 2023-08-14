package me.venom.armorchange;

import me.venom.armorchange.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorChange extends JavaPlugin
{

    private final Utils utils = new Utils();

    @Override
    public void onEnable()
    {
        sendLogoMessage();
        implementListeners();
    }

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
        Bukkit.getPluginManager().registerEvents(new PlayerInteract(utils), this);
        Bukkit.getPluginManager().registerEvents(new InventoryInteract(utils), this);
        Bukkit.getPluginManager().registerEvents(new InventoryDrag(), this);
        Bukkit.getPluginManager().registerEvents(new DispenserDispenseArmor(utils), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
        Bukkit.getPluginManager().registerEvents(new ItemBreak(utils), this);
    }
}
