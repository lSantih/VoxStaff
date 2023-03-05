package us.boxpvp.boxstaff;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import us.boxpvp.boxstaff.modules.IModule;
import us.boxpvp.boxstaff.modules.ModuleType;
import us.boxpvp.boxstaff.modules.core.CoreModule;
import us.boxpvp.boxstaff.file.FileManager;
import us.boxpvp.boxstaff.modules.core.listeners.StaffModeListener;
import us.boxpvp.boxstaff.modules.freeze.FreezeModule;
import us.boxpvp.boxstaff.modules.profile.ProfileModule;
import us.boxpvp.boxstaff.modules.vanish.VanishModule;

import java.util.Arrays;
import java.util.List;

public final class BoxStaff extends JavaPlugin {

    private static final String ADMIN_PERMISSION  = "boxstaff.admin";
    private static final String STAFF_PERMISSION = "boxstaff.staff";
    private static BoxStaff instance;
    @Getter private FileManager fileManager;

    private List<IModule> modules;

    @Override
    public void onEnable() {
        instance = this;

        loadModules();
        this.fileManager = new FileManager(this);
        registerEvents();
        registerCommands();
        // Plugin startup logic

    }

    private void registerEvents() {
    }

    private void registerCommands() {
    }

    public void loadModules() {
        this.modules = Arrays.asList(
                new CoreModule(),
                new ProfileModule(),
                new FreezeModule(),
                new VanishModule()
        );

        modules.forEach(IModule::enableModule);
    }

    //Getters

    public IModule getModule(final String identifier) {
        return modules.stream().filter(module -> module.getIdentifier().equals(identifier)).findFirst().orElse(null);
    }

    public IModule getModule(final ModuleType moduleType) {
        return modules.stream().filter(module -> module.getModuleType() == moduleType).findFirst().orElse(null);
    }

    public CoreModule getCore() {
        return (CoreModule) getModule(ModuleType.CORE);
    }
    
    public ProfileModule getProfiles() {
        return (ProfileModule) getModule(ModuleType.PROFILE);
    }

    public FreezeModule getFreeze() {
        return (FreezeModule) getModule(ModuleType.FREEZE);
    }
    public static BoxStaff getInstance() {
        return instance;
    }

    public static String getStaffPermission() {
        return STAFF_PERMISSION;
    }

    public static String getAdminPermission() {
        return ADMIN_PERMISSION;
    }
}
