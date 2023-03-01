package us.boxpvp.boxstaff.modules.profile.listeners;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
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
    private void handleProfileJoin(final PlayerJoinEvent event) {
        Bukkit.broadcastMessage("profile loaded");
        profileManager.loadProfile(event.getPlayer().getUniqueId());
    }

    @EventHandler
    private void handleProfileQuit(final PlayerQuitEvent event) {
        profileManager.unloadProfile(event.getPlayer().getUniqueId());
    }
}
