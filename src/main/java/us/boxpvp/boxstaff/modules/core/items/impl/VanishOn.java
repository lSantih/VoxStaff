package us.boxpvp.boxstaff.modules.core.items.impl;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.modules.core.items.IStaffitem;
import us.boxpvp.boxstaff.modules.core.items.StaffItemContext;
import us.boxpvp.boxstaff.modules.core.items.StaffItemType;
import us.boxpvp.boxstaff.modules.core.managers.StaffModeManager;
import us.boxpvp.boxstaff.modules.vanish.VanishModule;
import us.boxpvp.boxstaff.util.ItemBuilder;
import us.boxpvp.boxstaff.util.TypeCallback;

public class VanishOn implements IStaffitem {

    private final ItemStack stack;

    public VanishOn() {
        this.stack = new ItemBuilder(Material.LIME_DYE).setName("#FCCA30&lVanish &7(Enabled)").setLore("&7Right click to disable vanish.").toItemStack();
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
            VanishModule.getInstance().getVanishManager().disableVanish(context.clicker());
            StaffModeManager.getItemByClass(VanishOff.class).giveItem(context.clicker());
        };
    }

    @Override
    public StaffItemType getType() {
        return StaffItemType.VANISH_ON;
    }
}
