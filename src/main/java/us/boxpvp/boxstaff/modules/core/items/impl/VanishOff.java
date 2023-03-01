package us.boxpvp.boxstaff.modules.core.items.impl;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.modules.core.items.IStaffitem;
import us.boxpvp.boxstaff.modules.core.items.StaffItemContext;
import us.boxpvp.boxstaff.modules.core.items.StaffItemType;
import us.boxpvp.boxstaff.modules.core.managers.StaffModeManager;
import us.boxpvp.boxstaff.util.ItemBuilder;
import us.boxpvp.boxstaff.util.TypeCallback;

public class VanishOff implements IStaffitem {

    private final ItemStack stack;

    public VanishOff() {
        this.stack = new ItemBuilder(Material.GRAY_DYE).setName("#FCCA30&lVanish &7(Disabled)").setLore("&7Right click to enable vanish.").toItemStack();
    }
    @Override
    public ItemStack getStack() {
        return stack;
    }

    @Override
    public int getItemSlot() {
        return 7;
    }

    @Override
    public TypeCallback<StaffItemContext> getAction() {
        return context -> {
            //todo: connect with main core and enable vanish LOL
            StaffModeManager.getItemByClass(VanishOn.class).giveItem(context.clicker());
        };
    }

    @Override
    public StaffItemType getType() {
        return StaffItemType.VANISH_OFF;
    }
}
