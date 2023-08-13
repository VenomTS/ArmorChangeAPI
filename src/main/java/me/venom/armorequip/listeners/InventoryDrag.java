package me.venom.armorequip.listeners;

import me.venom.armorequip.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class InventoryDrag implements Listener
{

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryDrag(InventoryDragEvent event)
    {
        if(event.getInventory().getType() != InventoryType.CRAFTING) return;
        Player p = (Player) event.getWhoClicked();
        Set<Integer> rawSlots = event.getRawSlots();
        ItemStack newItem = null;
        if(rawSlots.contains(5)) { newItem = event.getNewItems().get(5); }
        else if(rawSlots.contains(6)) { newItem = event.getNewItems().get(6); }
        else if(rawSlots.contains(7)) { newItem = event.getNewItems().get(7); }
        else if(rawSlots.contains(8)) { newItem = event.getNewItems().get(8); }
        if(newItem == null) return;
        Utils.callArmorEquipEvent(p, null, newItem);
    }

}
