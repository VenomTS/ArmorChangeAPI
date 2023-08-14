package me.venom.armorchange.listeners;

import me.venom.armorchange.ArmorChange;
import me.venom.armorchange.Utils;
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

    private final ArmorChange main;

    public PlayerInteract(ArmorChange armorChange) { main = armorChange; }

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
        if(Utils.isArmor(handItem)) { newArmor = handItem; playerArmor = Utils.playerInventoryByHeldItem(p, handItem); }
        else if(Utils.isArmor(offHandItem)) { newArmor = offHandItem; playerArmor = Utils.playerInventoryByHeldItem(p, offHandItem); }
        if(newArmor == null) return;
        if(playerArmor != null && playerArmor.getType() == Material.AIR) playerArmor = null;
        main.callEvent(p, playerArmor, newArmor);
    }

}
