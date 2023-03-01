package us.boxpvp.boxstaff.modules.freeze;

import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.model.BoxCommand;
import us.boxpvp.boxstaff.model.BoxEvent;
import us.boxpvp.boxstaff.model.BoxListener;
import us.boxpvp.boxstaff.modules.IModule;
import us.boxpvp.boxstaff.modules.ModuleType;
import us.boxpvp.boxstaff.modules.freeze.commands.FreezeCommand;
import us.boxpvp.boxstaff.modules.freeze.commands.SetFreezeLocationCommand;
import us.boxpvp.boxstaff.modules.freeze.listeners.FreezeListener;
import us.boxpvp.boxstaff.modules.freeze.managers.FreezeManager;
import us.boxpvp.boxstaff.modules.profile.listeners.ProfileListener;
import us.boxpvp.boxstaff.modules.profile.managers.ProfileManager;

import java.util.Arrays;
import java.util.List;

public class FreezeModule implements IModule {
    private boolean isEnabled = true;
    private FreezeManager freezeManager;
    @Override
    public void enableModule() {
        this.freezeManager = new FreezeManager(BoxStaff.getInstance());
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
        return "freeze";
    }

    @Override
    public List<BoxListener> getListeners() {
        return Arrays.asList(
                new FreezeListener(freezeManager)
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
        BoxStaff.getInstance().getCommand("setfreezelocation").setExecutor(new SetFreezeLocationCommand(freezeManager));
        BoxStaff.getInstance().getCommand("freeze").setExecutor(new FreezeCommand(freezeManager));
    }

    @Override
    public ModuleType getModuleType() {
        return ModuleType.FREEZE;
    }

    public FreezeManager getFreezeManager() {
        return freezeManager;
    }
}
