package us.boxpvp.boxstaff.modules.core;

import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.model.BoxCommand;
import us.boxpvp.boxstaff.model.BoxEvent;
import us.boxpvp.boxstaff.model.BoxListener;
import us.boxpvp.boxstaff.modules.IModule;
import us.boxpvp.boxstaff.modules.ModuleType;
import us.boxpvp.boxstaff.modules.core.commands.StaffModeCommand;
import us.boxpvp.boxstaff.modules.core.listeners.StaffModeListener;
import us.boxpvp.boxstaff.modules.core.managers.StaffModeManager;

import java.util.Arrays;
import java.util.List;

public class CoreModule implements IModule {
    private boolean isEnabled = true;
    private final StaffModeManager staffModeManager = new StaffModeManager(BoxStaff.getInstance());

    @Override
    public void enableModule() {
        IModule.super.enableModule();
        staffModeManager.loadItems();
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
        return "core";
    }

    @Override
    public List<BoxListener> getListeners() {
        return Arrays.asList(
                new StaffModeListener(BoxStaff.getInstance())
        );
    }

    @Override
    public List<BoxCommand> getCommands() {
        return Arrays.asList(
                new StaffModeCommand(staffModeManager)
        );
    }

    @Override
    public List<BoxEvent> getEvents() {
        return Arrays.asList(
        );
    }

    @Override
    public void registerCommands() {
        BoxStaff.getInstance().getCommand("staffmode").setExecutor(new StaffModeCommand(staffModeManager));
    }

    public StaffModeManager getStaffModeManager() {
        return staffModeManager;
    }

    @Override
    public ModuleType getModuleType() {
        return ModuleType.CORE;
    }
}
