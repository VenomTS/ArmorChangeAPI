package me.venom.armorchange.listeners;

import me.venom.armorchange.ArmorChange;
import me.venom.armorchange.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ItemBreak implements Listener
{

    private final ArmorChange main;

    public ItemBreak(ArmorChange armorChange) { main = armorChange; }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemBreak(PlayerItemBreakEvent event)
    {
        Player p = event.getPlayer();
        ItemStack brokenItem = event.getBrokenItem();
        if(!Utils.isArmor(brokenItem)) return;
        main.callEvent(p, brokenItem, null);
    }
}
