package me.venom.armorequip.listeners;

import me.venom.armorequip.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinDeath implements Listener
{

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();
        ItemStack helmet = p.getInventory().getHelmet();
        ItemStack chestplate = p.getInventory().getChestplate();
        ItemStack leggings = p.getInventory().getLeggings();
        ItemStack boots = p.getInventory().getBoots();
        if(Utils.isArmor(helmet)) { Utils.callArmorEquipEvent(p, null, helmet); }
        if(Utils.isArmor(chestplate)) { Utils.callArmorEquipEvent(p, null, chestplate); }
        if(Utils.isArmor(leggings)) { Utils.callArmorEquipEvent(p, null, leggings); }
        if(Utils.isArmor(boots)) { Utils.callArmorEquipEvent(p, null, boots); }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        Player p = event.getEntity();
        ItemStack helmet = p.getInventory().getHelmet();
        ItemStack chestplate = p.getInventory().getChestplate();
        ItemStack leggings = p.getInventory().getLeggings();
        ItemStack boots = p.getInventory().getBoots();
        if(Utils.isArmor(helmet)) { Utils.callArmorEquipEvent(p, helmet, null); }
        if(Utils.isArmor(chestplate)) { Utils.callArmorEquipEvent(p, chestplate, null); }
        if(Utils.isArmor(leggings)) { Utils.callArmorEquipEvent(p, leggings, null); }
        if(Utils.isArmor(boots)) { Utils.callArmorEquipEvent(p, boots, null); }
    }

}
