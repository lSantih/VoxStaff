package us.boxpvp.boxstaff.modules.vanish;

import org.bukkit.Bukkit;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.model.BoxCommand;
import us.boxpvp.boxstaff.model.BoxEvent;
import us.boxpvp.boxstaff.model.BoxListener;
import us.boxpvp.boxstaff.modules.IModule;
import us.boxpvp.boxstaff.modules.ModuleType;
import us.boxpvp.boxstaff.modules.profile.ProfileModule;
import us.boxpvp.boxstaff.modules.vanish.commands.VanishCommand;
import us.boxpvp.boxstaff.modules.vanish.listeners.VanishListener;
import us.boxpvp.boxstaff.modules.vanish.managers.VanishManager;

import java.util.Arrays;
import java.util.List;

public class VanishModule implements IModule {
    private boolean isEnabled = true;

    private VanishManager vanishManager;
    private static VanishModule instance;


    @Override
    public void enableModule() {
        instance = this;

        this.vanishManager = new VanishManager(ProfileModule.getInstance().getProfileManager(), BoxStaff.getInstance());

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
        return "vanish";
    }

    @Override
    public List<BoxListener> getListeners() {
        return Arrays.asList(
                new VanishListener(vanishManager, BoxStaff.getInstance())
        );
    }

    @Override
    public List<BoxCommand> getCommands() {
        return Arrays.asList(
                new VanishCommand(vanishManager)
        );
    }

    @Override
    public List<BoxEvent> getEvents() {
        return Arrays.asList(
        );
    }

    @Override
    public void registerCommands() {
        BoxStaff.getInstance().getCommand("vanish").setExecutor(new VanishCommand(vanishManager));
    }

    public VanishManager getVanishManager() {
        return vanishManager;
    }

    @Override
    public ModuleType getModuleType() {
        return ModuleType.VANISH;
    }


    public static VanishModule getInstance() {
        return instance;
    }
}
