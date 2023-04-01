package us.boxpvp.boxstaff.modules.core.items.impl;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.modules.core.items.StaffItemContext;
import us.boxpvp.boxstaff.modules.core.items.IStaffitem;
import us.boxpvp.boxstaff.modules.core.items.StaffItemType;
import us.boxpvp.boxstaff.util.ItemBuilder;
import us.boxpvp.boxstaff.util.TypeCallback;

public class Freeze implements IStaffitem {

    private final ItemStack stack;

    public Freeze() {
        this.stack = new ItemBuilder(Material.IRON_BARS)
                .setName("#FCCA30&lFreeze")
                .setLore("&7Right click to freeze a player.")
                .setStaffItem(getType().name())
                .toItemStack();
    }
    @Override
    public ItemStack getStack() {
        return stack;
    }

    @Override
    public int getItemSlot() {
        return 1;
    }

    @Override
    public TypeCallback<StaffItemContext> getAction() {
        return context -> {
            if(context.target() == null) return;
            context.clicker().sendMessage("freezing " + context.target().getName());
        };
    }

    @Override
    public StaffItemType getType() {
        return StaffItemType.FREEZE;
    }
}
