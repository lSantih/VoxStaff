package us.boxpvp.boxstaff.modules.vanish.listeners;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.model.BoxListener;
import us.boxpvp.boxstaff.modules.vanish.managers.VanishManager;

@AllArgsConstructor
public class VanishListener implements Listener, BoxListener {

    private final VanishManager vanishManager;
    private final BoxStaff boxStaff;

    @EventHandler
    private void handleJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if(player.hasPermission("vostaff.staff")) return;

        vanishManager.getVanishedPlayers().forEach(vanished -> {
            player.hidePlayer(boxStaff, vanished);
        });
    }
}
