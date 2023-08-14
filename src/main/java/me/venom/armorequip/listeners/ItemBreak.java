package me.venom.armorequip.listeners;

import me.venom.armorequip.ArmorEquip;
import me.venom.armorequip.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ItemBreak implements Listener
{

    private final ArmorEquip main;

    public ItemBreak(ArmorEquip armorEquip) { main = armorEquip; }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemBreak(PlayerItemBreakEvent event)
    {
        Player p = event.getPlayer();
        ItemStack brokenItem = event.getBrokenItem();
        if(!Utils.isArmor(brokenItem)) return;
        main.callEvent(p, brokenItem, null);
    }
}
