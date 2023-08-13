package me.venom.armorequip.listeners;

import me.venom.armorequip.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ItemBreak implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemBreak(PlayerItemBreakEvent event)
    {
        Player p = event.getPlayer();
        ItemStack brokenItem = event.getBrokenItem();
        if(!Utils.isArmor(brokenItem)) return;
        Utils.callArmorEquipEvent(p, brokenItem, null);
    }
}
