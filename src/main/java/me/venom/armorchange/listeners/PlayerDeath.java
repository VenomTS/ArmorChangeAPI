package me.venom.armorchange.listeners;

import me.venom.armorchange.PlayerArmorChangeEvent;
import me.venom.armorchange.PlayerArmorChangeEvent.ChangeMethod;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        if(event.getKeepInventory()) return;
        Player p = event.getEntity();
        for(ItemStack armorItem : p.getInventory().getArmorContents())
        {
            if(armorItem == null) continue;
            PlayerArmorChangeEvent armorEvent = new PlayerArmorChangeEvent(p, armorItem, null, ChangeMethod.PLAYER_DEATH);
            Bukkit.getPluginManager().callEvent(armorEvent);
        }
    }

}
