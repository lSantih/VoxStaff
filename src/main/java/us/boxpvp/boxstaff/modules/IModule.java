package us.boxpvp.boxstaff.modules;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.model.BoxCommand;
import us.boxpvp.boxstaff.model.BoxEvent;
import us.boxpvp.boxstaff.model.BoxListener;
import us.boxpvp.boxstaff.util.CC;

import java.util.List;

public interface IModule {

    boolean isEnabled();

    void setEnabled(boolean b);

    String getIdentifier();

    List<BoxListener> getListeners();

    List<BoxCommand> getCommands();

    List<BoxEvent> getEvents();

    default void unregisterListener(final Class<? extends Listener> clazz) {
        getListeners().forEach(listener -> {
            if(listener.getBukkitListener().getClass() == clazz) {
                HandlerList.unregisterAll(listener.getBukkitListener());
            }
        });
    }

    default void unregisterBoxListener(final Class<? extends BoxListener> clazz) {
        getListeners().forEach(listener -> {
            if(listener.getClass() == clazz) {
                unregisterListener(listener.getBukkitListener().getClass());
            }
        });
    }

    void registerCommands();

    ModuleType getModuleType();

    default void enableModule() {
        if(!isEnabled()) {
            Bukkit.getLogger().info("Not loading the module because is not enabled.");
            return;
        }

        Bukkit.getConsoleSender().sendMessage(getModulePrefix() + "Loading commands...");
        registerCommands();
        Bukkit.getConsoleSender().sendMessage(getModulePrefix() + "Loading listeners...");
        getListeners().forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener.getBukkitListener(), BoxStaff.getInstance());
        });

        Bukkit.getConsoleSender().sendMessage(getModulePrefix() + "Module loaded successfully");
    }

    default String getModulePrefix() {
        return "[" + CC.capitalize(getIdentifier().toLowerCase()) + "] ";
    }

    default BoxCommand getCommand(final String identifier) {
        return getCommands().stream().filter(command -> command.getIdentifier().equals(identifier)).findFirst().orElse(null);
    }
    default void disableCommand(final String commandName) {
        if(getCommand(commandName) != null) getCommand(commandName).setEnabled(false);
    }

    default boolean isCommandEnabled(final String identifier) {
        return getCommand(identifier) == null ? false : getCommand(identifier).isEnabled();
    }
    default void enableCommand(final String commandName) {
        if(getCommand(commandName) != null) getCommand(commandName).setEnabled(true);
    }
}
