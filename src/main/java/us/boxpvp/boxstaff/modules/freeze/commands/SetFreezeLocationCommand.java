package us.boxpvp.boxstaff.modules.freeze.commands;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.model.BoxCommand;
import us.boxpvp.boxstaff.modules.core.managers.StaffModeManager;
import us.boxpvp.boxstaff.modules.freeze.managers.FreezeManager;
import us.boxpvp.boxstaff.util.CC;

@RequiredArgsConstructor
public class SetFreezeLocationCommand implements CommandExecutor, BoxCommand {

    @NonNull private final FreezeManager freezeManager;
    private boolean isEnabled = true;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return true;

        final Player player = (Player) sender;
        if (!player.hasPermission(BoxStaff.getAdminPermission())) {
            player.sendMessage(CC.colorize("&cYou do not have permissions to use this command."));
            return true;
        }

        freezeManager.setFreezeLocation(player.getLocation());
        player.sendMessage(CC.colorize("&aFreeze location updated."));
        return true;
    }

    @Override
    public String getPermission() {
        return "boxstaff.staff";
    }
    @Override
    public boolean isEnabled() {
        //todo read this from config
        return isEnabled;
    }

    @Override
    public void setEnabled(boolean b) {
        this.isEnabled = b;
    }

    @Override
    public String getIdentifier() {
        return "setfreezelocation";
    }
}
