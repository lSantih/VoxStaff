package us.boxpvp.boxstaff.modules.rollback.impl;

import lombok.Builder;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.modules.rollback.IRollback;
import us.boxpvp.boxstaff.modules.rollback.RollbackType;

import java.util.UUID;

@Builder
public class VoxStaffRollback implements IRollback {

    @Builder.Default
    private final RollbackType type = RollbackType.DEATH;
    private final UUID rollbackUUID;

    @Builder.Default
    private boolean isRestored = false;

    @Builder.Default
    private final String time = "Not set";

    @Builder.Default
    private final String location = "Not set";

    @Builder.Default
    private final ItemStack[] armorItems = new ItemStack[4];

    @Builder.Default
    private final ItemStack[] inventoryItems = new ItemStack[36];

    @Override
    public RollbackType getType() {
        return type;
    }

    @Override
    public ItemStack[] getArmorItems() {
        return armorItems;
    }

    @Override
    public ItemStack[] getInventoryItems() {
        return inventoryItems;
    }

    @Override
    public UUID getRollbackUUID() {
        return rollbackUUID;
    }

    @Override
    public boolean isRestored() {
        return isRestored;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public void setRestored(boolean newValue) {
        this.isRestored = newValue;
    }
}
