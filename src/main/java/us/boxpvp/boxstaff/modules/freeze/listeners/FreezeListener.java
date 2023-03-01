package us.boxpvp.boxstaff.modules.freeze.listeners;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.model.BoxListener;
import us.boxpvp.boxstaff.modules.freeze.managers.FreezeManager;
@AllArgsConstructor
public class FreezeListener implements Listener, BoxListener {

    private final FreezeManager freezeManager;

    @EventHandler
    private void handleTeleport(final PlayerTeleportEvent event) {
        event.setCancelled(freezeManager.isFrozen(event.getPlayer()));
    }

    @EventHandler
    private void handleQuit(final PlayerQuitEvent event) {
        if(!freezeManager.isFrozen(event.getPlayer())) return;


        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(BoxStaff.getStaffPermission())).forEach(staff -> {
            staff.sendMessage("Player " + event.getPlayer().getName() + " quit while frozen.");
        });
    }
}
