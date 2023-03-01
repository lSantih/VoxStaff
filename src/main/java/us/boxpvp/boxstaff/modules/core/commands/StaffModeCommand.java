package us.boxpvp.boxstaff.modules.core.commands;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import us.boxpvp.boxstaff.model.BoxCommand;
import us.boxpvp.boxstaff.modules.core.managers.StaffModeManager;
@RequiredArgsConstructor
public class StaffModeCommand implements CommandExecutor, BoxCommand {

    @NonNull private final StaffModeManager staffModeManager;
    private boolean isEnabled = true;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return true;

        final Player player = (Player) sender;
        if(!shouldExecute(player)) return true;

        staffModeManager.toggleMode(player);

        return true;
    }

    @Override
    public String getPermission() {
        return "boxstaff.staff";
    }

    //todo read this from config
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
        return "staffmode";
    }

}
