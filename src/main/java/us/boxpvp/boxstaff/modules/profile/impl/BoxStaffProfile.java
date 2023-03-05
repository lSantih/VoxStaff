package us.boxpvp.boxstaff.modules.profile.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.modules.profile.IProfile;

import java.util.UUID;

public class BoxStaffProfile implements IProfile {
    private boolean isFrozen;
    private boolean isInStaffMode;
    private final UUID uuid;
    private final Player base;

    private boolean isVanished;
    public BoxStaffProfile(final UUID uuid) {
        this.uuid = uuid;
        this.base = Bukkit.getPlayer(uuid);
    }
    @Override
    public boolean isFrozen() {
        return isFrozen;
    }

    @Override
    public void setFrozen(boolean newFrozenStatus) {
        this.isFrozen = newFrozenStatus;
    }

    @Override
    public boolean isVanished() {
        return isVanished;
    }

    @Override
    public void setVanish(boolean newValue) {
        this.isVanished = newValue;
    }

    @Override
    public boolean isInStaffMode() {
        return isInStaffMode;
    }

    @Override
    public void setStaffMode(boolean newStaffModeStatus) {
        this.isInStaffMode = newStaffModeStatus;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public Player getBase() {
        return base;
    }
}
