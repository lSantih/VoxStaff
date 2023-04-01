package us.boxpvp.boxstaff.modules.core.items.impl;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.modules.core.items.IStaffitem;
import us.boxpvp.boxstaff.modules.core.items.StaffItemContext;
import us.boxpvp.boxstaff.modules.core.items.StaffItemType;
import us.boxpvp.boxstaff.util.ItemBuilder;
import us.boxpvp.boxstaff.util.TypeCallback;

public class Compass implements IStaffitem {

    private final ItemStack stack;

    public Compass() {
        this.stack = new ItemBuilder(Material.COMPASS)
                .setName("#FCCA30&lCompass")
                .setLore("&7Right click to pass trough.")
                .setStaffItem(getType().name())
                .toItemStack();
    }
    @Override
    public ItemStack getStack() {
        return stack;
    }

    @Override
    public int getItemSlot() {
        return 0;
    }

    @Override
    public TypeCallback<StaffItemContext> getAction() {
        return context -> {
            return;
        };
    }

    @Override
    public StaffItemType getType() {
        return StaffItemType.COMPASS;
    }
}
