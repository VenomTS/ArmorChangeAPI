package me.venom.armorchange.listeners;

import me.venom.armorchange.ArmorChange;
import me.venom.armorchange.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinDeath implements Listener
{

    private final ArmorChange main;

    public JoinDeath(ArmorChange armorChange) { main = armorChange; }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();
        ItemStack helmet = p.getInventory().getHelmet();
        ItemStack chestplate = p.getInventory().getChestplate();
        ItemStack leggings = p.getInventory().getLeggings();
        ItemStack boots = p.getInventory().getBoots();
        if(Utils.isArmor(helmet)) { main.callEvent(p, null, helmet); }
        if(Utils.isArmor(chestplate)) { main.callEvent(p, null, chestplate); }
        if(Utils.isArmor(leggings)) { main.callEvent(p, null, leggings); }
        if(Utils.isArmor(boots)) { main.callEvent(p, null, boots); }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        Player p = event.getEntity();
        ItemStack helmet = p.getInventory().getHelmet();
        ItemStack chestplate = p.getInventory().getChestplate();
        ItemStack leggings = p.getInventory().getLeggings();
        ItemStack boots = p.getInventory().getBoots();
        if(Utils.isArmor(helmet)) { main.callEvent(p, helmet, null); }
        if(Utils.isArmor(chestplate)) { main.callEvent(p, chestplate, null); }
        if(Utils.isArmor(leggings)) { main.callEvent(p, leggings, null); }
        if(Utils.isArmor(boots)) { main.callEvent(p, boots, null); }
    }

}
