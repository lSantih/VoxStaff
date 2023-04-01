package us.boxpvp.boxstaff.modules.rollback.commands;

import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.model.BoxCommand;
import us.boxpvp.boxstaff.modules.rollback.RollbackType;
import us.boxpvp.boxstaff.modules.rollback.gui.TesGUI;
import us.boxpvp.boxstaff.modules.rollback.managers.RollbackManager;

@AllArgsConstructor
public class RollbackCommand implements CommandExecutor, BoxCommand {

    private final RollbackManager rollbackManager;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final Player player = (Player) sender;

        switch (args[0].toLowerCase()) {
            case "test":
                rollbackManager.saveRollback(player, RollbackType.DEATH);
                break;
            case "view":
                BoxStaff.getInstance().getProfiles().getProfileManager().getProfile(player.getUniqueId()).getRollbacks().forEach(rollback -> {
                    player.sendMessage(rollback.getRollbackUUID().toString().substring(0, 6));
                });
                break;
            case "menu":
                new TesGUI().open(player);
        }
        return true;
    }

    @Override
    public String getPermission() {
        return "voxstaff.rollback";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean b) {
        return;
    }

    @Override
    public String getIdentifier() {
        return null;
    }
}
