package me.venom.armorchange;

import me.venom.armorchange.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorChange extends JavaPlugin {

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
        Bukkit.getPluginManager().registerEvents(new PlayerInteract(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryInteract(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryDrag(this), this);
        Bukkit.getPluginManager().registerEvents(new DispenserDispenseArmor(this), this);
        Bukkit.getPluginManager().registerEvents(new JoinDeath(this), this);
        Bukkit.getPluginManager().registerEvents(new ItemBreak(this), this);
    }

    public void callEvent(Player p, ItemStack oldItem, ItemStack newItem)
    {
        Bukkit.getScheduler().runTaskLater(this, () ->
                Bukkit.getPluginManager().callEvent(new PlayerArmorChangeEvent(p, oldItem, newItem)), 1L);
    }
}
