package us.boxpvp.boxstaff.model;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public record SavedInventory(ItemStack[] contents, ItemStack[] armor) {
    public void restore(final Player player) {
        player.getInventory().setContents(contents);
        player.getInventory().setArmorContents(armor);
    }

    public static SavedInventory fromInventory(final Player player) {
        return new SavedInventory(player.getInventory().getContents(), player.getInventory().getArmorContents());
    }
}
