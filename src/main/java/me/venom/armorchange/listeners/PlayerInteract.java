package me.venom.armorchange.listeners;

import me.venom.armorchange.PlayerArmorChangeEvent;
import me.venom.armorchange.PlayerArmorChangeEvent.ChangeMethod;
import me.venom.armorchange.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener
{

    private final Utils utils;

    public PlayerInteract(Utils utils) { this.utils = utils; }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        if (!event.useInteractedBlock().equals(Event.Result.DENY)) return;
        Player p = event.getPlayer();
        ItemStack handItem = p.getInventory().getItemInMainHand();
        ItemStack offHandItem = p.getInventory().getItemInOffHand();
        ItemStack newArmor = null;
        ItemStack playerArmor = null;
        // Item in main hand is armor
        if(utils.isArmor(handItem)) { newArmor = handItem; playerArmor = utils.playerInventoryByHeldItem(p, handItem); }
        else if(utils.isArmor(offHandItem)) { newArmor = offHandItem; playerArmor = utils.playerInventoryByHeldItem(p, offHandItem); }
        if(newArmor == null) return;
        if(playerArmor != null && playerArmor.getType() == Material.AIR) playerArmor = null;
        PlayerArmorChangeEvent armorEvent = new PlayerArmorChangeEvent(p, playerArmor, newArmor, ChangeMethod.PLAYER_INTERACT);
        Bukkit.getPluginManager().callEvent(armorEvent);
    }

}
