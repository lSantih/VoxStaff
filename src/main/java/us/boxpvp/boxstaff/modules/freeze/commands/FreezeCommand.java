package us.boxpvp.boxstaff.modules.freeze.commands;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.model.BoxCommand;
import us.boxpvp.boxstaff.modules.freeze.managers.FreezeManager;
import us.boxpvp.boxstaff.util.CC;

@RequiredArgsConstructor
public class FreezeCommand implements CommandExecutor, BoxCommand {

    @NonNull private final FreezeManager freezeManager;
    private boolean isEnabled = true;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return true;

        final Player player = (Player) sender;
        if(!shouldExecute(player)) return true;

        if(args.length == 0) {
            player.sendMessage(CC.colorize("&cCorrect usage: /freeze <player>"));
            return true;
        }

        final String targetName = args[0];
        if(Bukkit.getPlayer(targetName) == null) {
            player.sendMessage(CC.colorize("&cPlayer not found."));
            return true;
        }

        final Player target = Bukkit.getPlayer(args[0]);
        freezeManager.toggleFreeze(target);
        player.sendMessage(freezeManager.isFrozen(target) ? CC.colorize("&aSuccessfully frozen " + target.getName()) :  CC.colorize("&aSuccessfully unfrozen " + target.getName()));
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
        return "freeze";
    }
}
