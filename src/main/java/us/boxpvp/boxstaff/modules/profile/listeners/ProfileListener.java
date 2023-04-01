package us.boxpvp.boxstaff.modules.profile.listeners;

import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import us.boxpvp.boxstaff.model.BoxListener;
import us.boxpvp.boxstaff.modules.profile.managers.ProfileManager;

@AllArgsConstructor
public class ProfileListener implements Listener, BoxListener {

    private final ProfileManager profileManager;

    @EventHandler
    private void handleJoin(final PlayerJoinEvent event) {
        profileManager.loadProfile(event.getPlayer().getUniqueId());
    }

    @EventHandler
    private void handleQuit(final PlayerQuitEvent event) {
        profileManager.unloadProfile(event.getPlayer().getUniqueId());
    }
}
