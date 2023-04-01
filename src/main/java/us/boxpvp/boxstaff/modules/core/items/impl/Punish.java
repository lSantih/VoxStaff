package us.boxpvp.boxstaff.modules.core.items.impl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.modules.core.items.IStaffitem;
import us.boxpvp.boxstaff.modules.core.items.StaffItemContext;
import us.boxpvp.boxstaff.modules.core.items.StaffItemType;
import us.boxpvp.boxstaff.util.ItemBuilder;
import us.boxpvp.boxstaff.util.TypeCallback;

public class Punish implements IStaffitem {

    private final ItemStack stack;

    public Punish() {
        this.stack = new ItemBuilder(Material.ANVIL)
                .setName("#FCCA30&lPunish")
                .setLore("&7Right click on a player to punish him.")
                .setStaffItem(getType().name())
                .toItemStack();
    }
    @Override
    public ItemStack getStack() {
        return stack;
    }

    @Override
    public int getItemSlot() {
        return 6;
    }

    @Override
    public TypeCallback<StaffItemContext> getAction() {
        return context -> {
            if(context.target() == null) return;
            Bukkit.dispatchCommand(context.clicker(), "punish " + context.target().getName());
        };
    }

    @Override
    public StaffItemType getType() {
        return StaffItemType.PUNISH;
    }
}
