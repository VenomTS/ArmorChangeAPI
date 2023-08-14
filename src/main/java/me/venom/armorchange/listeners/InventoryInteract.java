package me.venom.armorchange.listeners;

import me.venom.armorchange.ArmorChange;
import me.venom.armorchange.Utils;
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

    private final ArmorChange main;

    public InventoryInteract(ArmorChange armorChange) { main = armorChange; }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event)
    {
        // NOTE: EVENT TAKES WHAT WAS IN THE SLOT BEFORE BEING CLICKED
        if(!(event.getWhoClicked() instanceof Player)) return;
        Player p = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        Inventory clickedInv = event.getClickedInventory();
        int rawSlot = event.getRawSlot(), slot = event.getSlot();
        if(clickedInv == null || clickedInv.getType() != InventoryType.PLAYER || inv.getType() != InventoryType.CRAFTING) return;
        if(event.getClick() == ClickType.SWAP_OFFHAND)
        {
            if(event.getSlotType() != InventoryType.SlotType.ARMOR) return;
            ItemStack oldItem = clickedInv.getItem(slot);
            ItemStack newItem = p.getInventory().getItemInOffHand();
            if(oldItem != null && oldItem.getType() == Material.AIR) oldItem = null;
            if(newItem.getType() == Material.AIR) newItem = null;
            if(oldItem == null && newItem == null) return;
            if(newItem != null && !Utils.correctArmorPieceForSlot(newItem, rawSlot)) return;
            main.callEvent(p, oldItem, newItem);
        }
        else if(event.getClick() == ClickType.NUMBER_KEY)
        {
            if(event.getSlotType() != InventoryType.SlotType.ARMOR) return;
            ItemStack oldItem = clickedInv.getItem(slot);
            ItemStack newItem = clickedInv.getItem(event.getHotbarButton());
            if(oldItem == null && newItem == null) return;
            if(newItem != null && !Utils.correctArmorPieceForSlot(newItem, rawSlot)) return;
            main.callEvent(p, oldItem, newItem);
        }
        else if(event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT)
        {
            if(event.getSlotType() != InventoryType.SlotType.ARMOR) return;
            ItemStack oldItem = clickedInv.getItem(slot); // This should be item in the armor slot => Old item
            ItemStack newItem = event.getCursor(); // This should be item in the cursor => New Item
            if(oldItem != null && oldItem.getType() == Material.AIR) { oldItem = null; }
            if(newItem != null && newItem.getType() == Material.AIR) { newItem = null; }
            if(oldItem == null && newItem == null) return;
            if((Utils.isArmor(newItem) && Utils.correctArmorPieceForSlot(newItem, rawSlot)) || Utils.isEmptySlot(newItem))
            {
                main.callEvent(p, oldItem, newItem);
            }
        }
        else if(event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT)
        {
            ItemStack handItem = clickedInv.getItem(slot);
            if(event.getSlotType() == InventoryType.SlotType.ARMOR)
            {
                if(!Utils.hasSpaceInInventory(p)) return;
                main.callEvent(p, handItem, null);
            }
            else
            {
                if(!Utils.isArmor(handItem) || handItem == null) return;
                ItemStack armorSlot = Utils.playerInventoryByHeldItem(p, handItem);
                if(!Utils.isEmptySlot(armorSlot)) return;
                main.callEvent(p, null, handItem);
            }
        }
    }

}