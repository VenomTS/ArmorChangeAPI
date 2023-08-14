package me.venom.armorequip.listeners;

import me.venom.armorequip.ArmorEquip;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.inventory.ItemStack;

public class DispenserDispenseArmor implements Listener
{

    private final ArmorEquip main;

    public DispenserDispenseArmor(ArmorEquip armorEquip) { main = armorEquip; }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDispenserDispenseArmor(BlockDispenseArmorEvent event)
    {
        // Called only when entity is actually equiped with armor
        if(!(event.getTargetEntity() instanceof Player)) return;
        Player p = (Player) event.getTargetEntity();
        ItemStack armorItem = event.getItem();
        main.callEvent(p, null, armorItem);
    }
}
