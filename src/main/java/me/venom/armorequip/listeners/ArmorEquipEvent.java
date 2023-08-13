package me.venom.armorequip.listeners;

import me.venom.armorequip.ArmorPlayerEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ArmorEquipEvent implements Listener
{

    @EventHandler
    public void onPlayerEquipArmor(ArmorPlayerEvent event)
    {
        Player p = event.getPlayer();
        ItemStack newItem = event.getNewArmorPiece();
        ItemStack oldItem = event.getOldArmorPiece();
        p.sendMessage("Old Armor: " + oldItem);
        p.sendMessage("New Armor: " + newItem);
    }

}
