package me.venom.armorchange.listeners;

import me.venom.armorchange.PlayerArmorChangeEvent;
import me.venom.armorchange.PlayerArmorChangeEvent.ChangeMethod;
import me.venom.armorchange.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBreak implements Listener
{

    private final Utils utils;

    public ItemBreak(Utils utils) { this.utils = utils; }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemBreak(PlayerItemBreakEvent event)
    {
        Player p = event.getPlayer();
        ItemStack brokenItem = event.getBrokenItem();
        if(!utils.isArmor(brokenItem)) return;
        PlayerArmorChangeEvent armorEvent = new PlayerArmorChangeEvent(p, brokenItem, null, ChangeMethod.ITEM_BREAK);
        Bukkit.getPluginManager().callEvent(armorEvent);
        if(!armorEvent.isCancelled()) return;
        // Event was cancelled -> Set damage to 0 (one hit and it breaks)
        ItemStack replaceItem = brokenItem.clone();
        replaceItem.setAmount(1);
        ItemMeta replaceItemMeta = replaceItem.getItemMeta();
        if(!(replaceItemMeta instanceof Damageable)) return;
        Damageable dmg = (Damageable) replaceItemMeta;
        dmg.setDamage(dmg.getDamage() - 1);
    }
}
