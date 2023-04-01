package us.boxpvp.boxstaff.modules.profile.impl;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.file.impl.YamlFile;
import us.boxpvp.boxstaff.modules.profile.IProfile;
import us.boxpvp.boxstaff.modules.rollback.IRollback;
import us.boxpvp.boxstaff.modules.rollback.RollbackModule;
import us.boxpvp.boxstaff.modules.rollback.RollbackType;
import us.boxpvp.boxstaff.modules.rollback.impl.VoxStaffRollback;
import us.boxpvp.boxstaff.modules.rollback.managers.RollbackManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BoxStaffProfile implements IProfile {
    private boolean isFrozen;
    private boolean isInStaffMode;
    private final UUID uuid;
    private final Player base;

    private final List<IRollback> rollbacks;

    private boolean isVanished;
    public BoxStaffProfile(final UUID uuid) {
        this.uuid = uuid;
        this.base = Bukkit.getPlayer(uuid);

        
        //todo load rollbacks
        final RollbackManager rollbackManager = RollbackModule.getInstance().getRollbackManager();

        this.rollbacks = new ArrayList<>();

        final File[] rollbacks = new File(BoxStaff.getInstance().getDataFolder() + "/players/" + uuid + "/rollbacks/").listFiles();
        if(rollbacks == null) return;
        if(rollbacks.length == 0) return;

        for (File rollback : rollbacks) {
            final YamlFile rollbackYaml = new YamlFile("/players/" + uuid + "/rollbacks/" + rollback.getName(), BoxStaff.getInstance());
            final FileConfiguration rollbackConfig = rollbackYaml.getConfig();

            this.rollbacks.add(rollbackManager.loadRollback(rollbackConfig));
        }
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
    public List<IRollback> getRollbacks() {
        return new ArrayList<>(rollbacks);
    }

    @Override
    public List<IRollback> getRollbacks(RollbackType type) {
        return rollbacks.stream().filter(rollback -> rollback.getType() == type).collect(Collectors.toList());
    }

    @Override
    public void addRollback(IRollback rollback) {
        rollbacks.add(rollback);
        rollback.save(this);
    }

    @Override
    public void removeRollback(IRollback rollback) {
        rollbacks.remove(rollback);
    }

    @Override
    public Optional<IRollback> getRollback(UUID uuid) {
        return rollbacks.stream().filter(rollback -> rollback.getRollbackUUID() == uuid).findFirst();
    }

    @Override
    public void removeRollback(UUID uuid) {
        getRollback(uuid).ifPresent(rollback -> rollbacks.remove(rollback));
    }

    @Override
    public Player getBase() {
        return base;
    }
}
