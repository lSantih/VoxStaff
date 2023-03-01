package us.boxpvp.boxstaff.modules.core.items.impl;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.modules.core.items.IStaffitem;
import us.boxpvp.boxstaff.modules.core.items.StaffItemContext;
import us.boxpvp.boxstaff.modules.core.items.StaffItemType;
import us.boxpvp.boxstaff.util.ItemBuilder;
import us.boxpvp.boxstaff.util.TypeCallback;

public class StaffList implements IStaffitem {

    private final ItemStack stack;

    public StaffList() {
        this.stack = new ItemBuilder(Material.EMERALD).setName("#FCCA30&lStaff List").setLore("&7Right click to view the online staff.").toItemStack();
    }
    @Override
    public ItemStack getStack() {
        return stack;
    }

    @Override
    public int getItemSlot() {
        return 4;
    }

    @Override
    public TypeCallback<StaffItemContext> getAction() {
        return context -> {
            context.clicker().sendMessage("imagine that a menu is opened rn :)");
        };
    }

    @Override
    public StaffItemType getType() {
        return StaffItemType.STAFF_LIST;
    }
}
