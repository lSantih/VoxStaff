package us.boxpvp.boxstaff.modules.vanish.commands;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import us.boxpvp.boxstaff.model.BoxCommand;
import us.boxpvp.boxstaff.modules.core.managers.StaffModeManager;
import us.boxpvp.boxstaff.modules.vanish.managers.VanishManager;
import us.boxpvp.boxstaff.util.CC;

@RequiredArgsConstructor
public class VanishCommand implements CommandExecutor, BoxCommand {

    @NonNull private final VanishManager vanishManager;
    private boolean isEnabled = true;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return true;

        final Player player = (Player) sender;
        if(!shouldExecute(player)) return true;

        vanishManager.toggleVanish(player);
        player.sendMessage(vanishManager.isVanished(player) ? CC.colorize("&aYour vanish is now enabled.") : CC.colorize("&cYour vanish is now disabled."));

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
        return "vanish";
    }

}
