package me.venom.armorchange.listeners;

import me.venom.armorchange.PlayerArmorChangeEvent;
import me.venom.armorchange.PlayerArmorChangeEvent.ChangeMethod;
import me.venom.armorchange.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryInteract implements Listener
{

    private final Utils utils;

    public InventoryInteract(Utils utils) { this.utils = utils; }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event)
    {
        // NOTE: EVENT TAKES WHAT WAS IN THE SLOT BEFORE BEING CLICKED
        if(!(event.getWhoClicked() instanceof Player)) return;
        Player p = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        Inventory clickedInv = event.getClickedInventory();
        int rawSlot = event.getRawSlot(), slot = event.getSlot();
        if(clickedInv == null || clickedInv.getType() != InventoryType.PLAYER || inv.getType() != InventoryType.CRAFTING) return;
        if(event.getClick() == ClickType.DROP || event.getClick() == ClickType.CONTROL_DROP)
        {
            if(event.getSlotType() != InventoryType.SlotType.ARMOR) return;
            ItemStack oldItem = clickedInv.getItem(slot);
            if(utils.isEmptySlot(oldItem)) return;
            PlayerArmorChangeEvent armorEvent = new PlayerArmorChangeEvent(p, oldItem, null, ChangeMethod.ITEM_DROP);
            Bukkit.getPluginManager().callEvent(armorEvent);
            event.setCancelled(armorEvent.isCancelled());
        }
        else if(event.getClick() == ClickType.SWAP_OFFHAND)
        {
            if(event.getSlotType() != InventoryType.SlotType.ARMOR) return;
            ItemStack oldItem = clickedInv.getItem(slot);
            ItemStack newItem = p.getInventory().getItemInOffHand();
            if(oldItem != null && oldItem.getType() == Material.AIR) oldItem = null;
            if(newItem.getType() == Material.AIR) newItem = null;
            if(oldItem == null && newItem == null) return;
            if(newItem != null && !utils.correctArmorPieceForSlot(newItem, rawSlot)) return;
            PlayerArmorChangeEvent armorEvent = new PlayerArmorChangeEvent(p, oldItem, newItem, ChangeMethod.OFFHAND_SWAP);
            Bukkit.getPluginManager().callEvent(armorEvent);
            event.setCancelled(armorEvent.isCancelled());
        }
        else if(event.getClick() == ClickType.NUMBER_KEY)
        {
            if(event.getSlotType() != InventoryType.SlotType.ARMOR) return;
            ItemStack oldItem = clickedInv.getItem(slot);
            ItemStack newItem = clickedInv.getItem(event.getHotbarButton());
            if(oldItem == null && newItem == null) return;
            if(newItem != null && !utils.correctArmorPieceForSlot(newItem, rawSlot)) return;
            PlayerArmorChangeEvent armorEvent = new PlayerArmorChangeEvent(p, oldItem, newItem, ChangeMethod.NUMBER_SWAP);
            Bukkit.getPluginManager().callEvent(armorEvent);
            event.setCancelled(armorEvent.isCancelled());
        }
        else if(event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
        {
            if(event.getSlotType() != InventoryType.SlotType.ARMOR) return;
            ItemStack oldItem = clickedInv.getItem(slot); // This should be item in the armor slot => Old item
            ItemStack newItem = event.getCursor(); // This should be item in the cursor => New Item
            if(oldItem != null && oldItem.getType() == Material.AIR) { oldItem = null; }
            if(newItem != null && newItem.getType() == Material.AIR) { newItem = null; }
            if(oldItem == null && newItem == null) return;
            if((utils.isArmor(newItem) && utils.correctArmorPieceForSlot(newItem, rawSlot)) || utils.isEmptySlot(newItem))
            {
                PlayerArmorChangeEvent armorEvent = new PlayerArmorChangeEvent(p, oldItem, newItem, ChangeMethod.MOUSE_CLICK);
                Bukkit.getPluginManager().callEvent(armorEvent);
                event.setCancelled(armorEvent.isCancelled());
            }
        }
        else if(event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)
        {
            ItemStack handItem = clickedInv.getItem(slot);
            ItemStack newItem = null;
            ItemStack oldItem = null;
            if(event.getSlotType() == InventoryType.SlotType.ARMOR)
            {
                if(!utils.hasSpaceInInventory(p)) return;
                oldItem = handItem;
            }
            else
            {
                if(!utils.isArmor(handItem) || handItem == null) return;
                ItemStack armorSlot = utils.playerInventoryByHeldItem(p, handItem);
                if(!utils.isEmptySlot(armorSlot)) return;
                newItem = handItem;
            }
            PlayerArmorChangeEvent armorEvent = new PlayerArmorChangeEvent(p, oldItem, newItem, ChangeMethod.MOUSE_SHIFT_CLICK);
            Bukkit.getPluginManager().callEvent(armorEvent);
            event.setCancelled(armorEvent.isCancelled());
        }
    }

}