package us.boxpvp.boxstaff.modules.profile;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface IProfile {

    boolean isFrozen();

    void setFrozen(boolean newFrozenStatus);


    boolean isVanished();

    void setVanish(final boolean newValue);

    boolean isInStaffMode();

    void setStaffMode(boolean newStaffModeStatus);

    UUID getUUID();

    Player getBase();
}
