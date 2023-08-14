package me.venom.armorchange;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PlayerArmorChangeEvent extends PlayerEvent implements Cancellable
{

    public enum ChangeMethod
    {
        DISPENSER_EQUIP,
        INVENTORY_DRAG,
        OFFHAND_SWAP,
        NUMBER_SWAP,
        MOUSE_CLICK,
        MOUSE_SHIFT_CLICK,
        ITEM_BREAK,
        ITEM_DROP,
        PLAYER_DEATH,
        PLAYER_INTERACT
    }

    private boolean cancelled;
    private final ItemStack oldArmorPiece, newArmorPiece;
    private final ChangeMethod changeMethod;

    private static final HandlerList HANDLERS = new HandlerList();

    public PlayerArmorChangeEvent(final Player player, final ItemStack oldArmor, final ItemStack newArmor, final ChangeMethod armorChangeMethod)
    {
        super(player);
        oldArmorPiece = oldArmor;
        newArmorPiece = newArmor;
        changeMethod = armorChangeMethod;
    }

    public static HandlerList getHandlerList() { return HANDLERS; }

    @Override
    @NonNull
    public HandlerList getHandlers() { return HANDLERS; }

    @Override
    public boolean isCancelled() { return cancelled; }

    @Override
    public void setCancelled(boolean b) { cancelled = b; }

    public final ItemStack getOldArmorPiece() { return oldArmorPiece; }
    public final ItemStack getNewArmorPiece() { return newArmorPiece; }
    public final ChangeMethod getChangeMethod() { return changeMethod; }
}
