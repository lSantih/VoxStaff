package us.boxpvp.boxstaff.modules.profile;

import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.model.BoxCommand;
import us.boxpvp.boxstaff.model.BoxEvent;
import us.boxpvp.boxstaff.model.BoxListener;
import us.boxpvp.boxstaff.modules.IModule;
import us.boxpvp.boxstaff.modules.ModuleType;
import us.boxpvp.boxstaff.modules.core.commands.StaffModeCommand;
import us.boxpvp.boxstaff.modules.core.listeners.StaffModeListener;
import us.boxpvp.boxstaff.modules.core.managers.StaffModeManager;
import us.boxpvp.boxstaff.modules.profile.listeners.ProfileListener;
import us.boxpvp.boxstaff.modules.profile.managers.ProfileManager;

import java.util.Arrays;
import java.util.List;

public class ProfileModule implements IModule {
    private boolean isEnabled = true;
    private final ProfileManager profileManager = new ProfileManager();

    @Override
    public void enableModule() {
        IModule.super.enableModule();
        profileManager.cacheAllOnline();
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
        return "profiles";
    }

    @Override
    public List<BoxListener> getListeners() {
        return Arrays.asList(
                new ProfileListener(profileManager)
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
    }

    @Override
    public ModuleType getModuleType() {
        return ModuleType.PROFILE;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }
}
