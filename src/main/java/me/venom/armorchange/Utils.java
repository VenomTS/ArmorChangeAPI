package me.venom.armorchange;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class Utils
{

    private final Set<Material> helmetMaterials = getHelmetMaterials();
    private final Set<Material> chestplateMaterials = getChestplateMaterials();
    private final Set<Material> leggingsMaterials = getLeggingsMaterials();
    private final Set<Material> bootsMaterials = getBootsMaterials();

    private Set<Material> getHelmetMaterials()
    {
        Set<Material> materials = new HashSet<>();
        materials.add(Material.LEATHER_HELMET);
        materials.add(Material.GOLDEN_HELMET);
        materials.add(Material.CHAINMAIL_HELMET);
        materials.add(Material.IRON_HELMET);
        materials.add(Material.DIAMOND_HELMET);
        materials.add(Material.NETHERITE_HELMET);
        materials.add(Material.TURTLE_HELMET);
        materials.add(Material.CARVED_PUMPKIN);
        materials.add(Material.SKELETON_SKULL);
        materials.add(Material.WITHER_SKELETON_SKULL);
        materials.add(Material.CREEPER_HEAD);
        materials.add(Material.DRAGON_HEAD);
        materials.add(Material.PIGLIN_HEAD);
        materials.add(Material.PLAYER_HEAD);
        materials.add(Material.ZOMBIE_HEAD);
        return materials;
    }
    private Set<Material> getChestplateMaterials()
    {
        Set<Material> materials = new HashSet<>();
        materials.add(Material.LEATHER_CHESTPLATE);
        materials.add(Material.GOLDEN_CHESTPLATE);
        materials.add(Material.CHAINMAIL_CHESTPLATE);
        materials.add(Material.IRON_CHESTPLATE);
        materials.add(Material.DIAMOND_CHESTPLATE);
        materials.add(Material.NETHERITE_CHESTPLATE);
        return materials;
    }

    private Set<Material> getLeggingsMaterials()
    {
        Set<Material> materials = new HashSet<>();
        materials.add(Material.LEATHER_LEGGINGS);
        materials.add(Material.GOLDEN_LEGGINGS);
        materials.add(Material.CHAINMAIL_LEGGINGS);
        materials.add(Material.IRON_LEGGINGS);
        materials.add(Material.DIAMOND_LEGGINGS);
        materials.add(Material.NETHERITE_LEGGINGS);
        return materials;
    }

    private Set<Material> getBootsMaterials()
    {
        Set<Material> materials = new HashSet<>();
        materials.add(Material.LEATHER_BOOTS);
        materials.add(Material.GOLDEN_BOOTS);
        materials.add(Material.CHAINMAIL_BOOTS);
        materials.add(Material.IRON_BOOTS);
        materials.add(Material.DIAMOND_BOOTS);
        materials.add(Material.NETHERITE_BOOTS);
        return materials;
    }

    public boolean hasSpaceInInventory(Player p) { return p.getInventory().firstEmpty() != -1; }

    public boolean correctArmorPieceForSlot(ItemStack armorPiece, int slot)
    {
        if(armorPiece == null) return false;
        Material material = armorPiece.getType();
        return (slot == 5 && helmetMaterials.contains(material)) ||
                (slot == 6 && chestplateMaterials.contains(material)) ||
                (slot == 7 && leggingsMaterials.contains(material)) ||
                (slot == 8 && bootsMaterials.contains(material));
    }

    public boolean isArmor(ItemStack item)
    {
        if(isEmptySlot(item)) return false;
        return isHelmet(item) || isChestplate(item) || isLeggings(item) || isBoots(item);
    }

    public boolean isEmptySlot(ItemStack item) { return item == null || item.getType() == Material.AIR; }

    public boolean isHelmet(ItemStack item) { return helmetMaterials.contains(item.getType()); }
    public boolean isChestplate(ItemStack item) { return chestplateMaterials.contains(item.getType()); }
    public boolean isLeggings(ItemStack item) { return leggingsMaterials.contains(item.getType()); }
    public boolean isBoots(ItemStack item) { return bootsMaterials.contains(item.getType()); }

    public ItemStack playerInventoryByHeldItem(Player p, ItemStack handItem)
    {
        if(handItem == null) return null;
        if(isHelmet(handItem)) return p.getInventory().getHelmet();
        else if(isChestplate(handItem)) return p.getInventory().getChestplate();
        else if(isLeggings(handItem)) return p.getInventory().getLeggings();
        else if(isBoots(handItem)) return p.getInventory().getBoots();
        return null;
    }

    public void setArmorInSlot(Player p, ItemStack armor)
    {
        if(!isArmor(armor)) return;
        if(isHelmet(armor)) p.getInventory().setHelmet(armor);
        else if(isChestplate(armor)) p.getInventory().setChestplate(armor);
        else if(isLeggings(armor)) p.getInventory().setLeggings(armor);
        else if(isBoots(armor)) p.getInventory().setBoots(armor);
    }
}
