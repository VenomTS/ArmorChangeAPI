package me.venom.armorequip;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public class PlayerArmorChangeEvent extends PlayerEvent
{

    private final ItemStack oldArmorPiece, newArmorPiece;

    private static final HandlerList HANDLERS = new HandlerList();

    public PlayerArmorChangeEvent(final Player player, final ItemStack oldArmor, final ItemStack newArmor)
    {
        super(player);
        oldArmorPiece = oldArmor;
        newArmorPiece = newArmor;
    }

    public static HandlerList getHandlerList() { return HANDLERS; }

    @Override
    @NonNull
    public HandlerList getHandlers() { return HANDLERS; }

    public final ItemStack getOldArmorPiece() { return oldArmorPiece; }
    public final ItemStack getNewArmorPiece() { return newArmorPiece; }

}
