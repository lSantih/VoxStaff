package us.boxpvp.boxstaff.model;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public interface BoxCommand {

    String getPermission();

    boolean isEnabled();

    void setEnabled(final boolean b);

    String getIdentifier();

    default boolean shouldExecute(final Player player) {
        return player.hasPermission(getPermission()) && isEnabled();
    }
}
