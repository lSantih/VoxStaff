package us.boxpvp.boxstaff.modules.profile;

import org.bukkit.entity.Player;
import us.boxpvp.boxstaff.modules.rollback.IRollback;
import us.boxpvp.boxstaff.modules.rollback.RollbackType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public interface IProfile {

    boolean isFrozen();

    void setFrozen(boolean newFrozenStatus);

    boolean isVanished();

    void setVanish(final boolean newValue);

    boolean isInStaffMode();

    void setStaffMode(boolean newStaffModeStatus);

    UUID getUUID();

    List<IRollback> getRollbacks();

    List<IRollback> getRollbacks(final RollbackType type);

    void addRollback(final IRollback rollback);

    void removeRollback(final IRollback rollback);

    Optional<IRollback> getRollback(final UUID uuid);

    void removeRollback(final UUID uuid);

    Player getBase();
}
