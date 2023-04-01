package us.boxpvp.boxstaff.modules.rollback;

import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.model.BoxCommand;
import us.boxpvp.boxstaff.model.BoxEvent;
import us.boxpvp.boxstaff.model.BoxListener;
import us.boxpvp.boxstaff.modules.IModule;
import us.boxpvp.boxstaff.modules.ModuleType;
import us.boxpvp.boxstaff.modules.profile.listeners.ProfileListener;
import us.boxpvp.boxstaff.modules.profile.managers.ProfileManager;
import us.boxpvp.boxstaff.modules.rollback.commands.RollbackCommand;
import us.boxpvp.boxstaff.modules.rollback.listeners.RollbackListener;
import us.boxpvp.boxstaff.modules.rollback.managers.RollbackManager;

import java.util.Arrays;
import java.util.List;

public class RollbackModule implements IModule {
    private boolean isEnabled = true;
    private RollbackManager rollbackManager;
    private static RollbackModule instance;

    @Override
    public void enableModule() {
        instance = this;
        this.rollbackManager = new RollbackManager();
        IModule.super.enableModule();
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public void setEnabled(boolean b) {
        this.isEnabled = b;
    }

    @Override
    public String getIdentifier() {
        return "rollback";
    }

    @Override
    public List<BoxListener> getListeners() {
        return Arrays.asList(
                new RollbackListener()
        );
    }

    @Override
    public List<BoxCommand> getCommands() {
        return Arrays.asList(

        );
    }

    @Override
    public List<BoxEvent> getEvents() {
        return Arrays.asList(
        );
    }

    @Override
    public void registerCommands() {
        BoxStaff.getInstance().getCommand("rollback").setExecutor(new RollbackCommand(rollbackManager));
    }

    @Override
    public ModuleType getModuleType() {
        return ModuleType.PROFILE;
    }

    public RollbackManager getRollbackManager() {
        return rollbackManager;
    }

    public static RollbackModule getInstance() {
        return instance;
    }
}
