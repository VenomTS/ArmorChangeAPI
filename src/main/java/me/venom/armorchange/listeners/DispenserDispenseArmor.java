package me.venom.armorchange.listeners;

import me.venom.armorchange.PlayerArmorChangeEvent;
import me.venom.armorchange.PlayerArmorChangeEvent.ChangeMethod;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.inventory.ItemStack;

public class DispenserDispenseArmor implements Listener
{

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDispenserDispenseArmor(BlockDispenseArmorEvent event)
    {
        // Called only when entity is actually equiped with armor
        if(!(event.getTargetEntity() instanceof Player)) return;
        Player p = (Player) event.getTargetEntity();
        ItemStack armorItem = event.getItem();
        PlayerArmorChangeEvent armorEvent = new PlayerArmorChangeEvent(p, null, armorItem, ChangeMethod.DISPENSER_EQUIP);
        Bukkit.getPluginManager().callEvent(armorEvent);
        if(!armorEvent.isCancelled()) return;
        event.setCancelled(true);
    }
}
