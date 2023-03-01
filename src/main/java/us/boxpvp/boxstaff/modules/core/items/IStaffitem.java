package us.boxpvp.boxstaff.modules.core.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.util.TypeCallback;

public interface IStaffitem {

    ItemStack getStack();
    int getItemSlot();

    StaffItemType getType();
    TypeCallback<StaffItemContext> getAction();

    default void giveItem(final Player player) {
        player.getInventory().setItem(getItemSlot(), getStack());
    }


}
