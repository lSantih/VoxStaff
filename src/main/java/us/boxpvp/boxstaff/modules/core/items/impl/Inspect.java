package us.boxpvp.boxstaff.modules.core.items.impl;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.modules.core.guis.InspectGUI;
import us.boxpvp.boxstaff.modules.core.items.IStaffitem;
import us.boxpvp.boxstaff.modules.core.items.StaffItemContext;
import us.boxpvp.boxstaff.modules.core.items.StaffItemType;
import us.boxpvp.boxstaff.util.ItemBuilder;
import us.boxpvp.boxstaff.util.TypeCallback;

public class Inspect implements IStaffitem {

    private final ItemStack stack;

    public Inspect() {
        this.stack = new ItemBuilder(Material.BOOK)
                .setName("#FCCA30&lInspect")
                .setLore("&7Right click to inspect a player.")
                .setStaffItem(getType().name())
                .toItemStack();
    }
    @Override
    public ItemStack getStack() {
        return stack;
    }

    @Override
    public int getItemSlot() {
        return 2;
    }

    @Override
    public TypeCallback<StaffItemContext> getAction() {
        return context -> {
            if(context.target() == null) return;

            new InspectGUI(context.target()).open(context.clicker());
        };
    }

    @Override
    public StaffItemType getType() {
        return StaffItemType.INSPECT;
    }
}
