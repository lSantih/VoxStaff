package us.boxpvp.boxstaff.modules.profile.managers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.modules.profile.IProfile;
import us.boxpvp.boxstaff.modules.profile.events.ProfileLoadEvent;
import us.boxpvp.boxstaff.modules.profile.events.ProfileUnloadEvent;
import us.boxpvp.boxstaff.modules.profile.impl.BoxStaffProfile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {

    private final Map<UUID, IProfile> loadedProfiles = new HashMap<>();

    public void loadProfile(final UUID uuid) {
        final ProfileLoadEvent profileLoadEvent = new ProfileLoadEvent(Bukkit.getPlayer(uuid), uuid);
        Bukkit.getPluginManager().callEvent(profileLoadEvent);
        if(profileLoadEvent.isCancelled()) return;

        loadedProfiles.put(uuid, new BoxStaffProfile(uuid));
    }

    public void unloadProfile(final UUID uuid) {
        final ProfileUnloadEvent profileUnloadEvent = new ProfileUnloadEvent(Bukkit.getPlayer(uuid), uuid);
        Bukkit.getPluginManager().callEvent(profileUnloadEvent);
        if(profileUnloadEvent.isCancelled()) return;

        loadedProfiles.remove(uuid);
    }

    public boolean isProfileLoaded(final UUID uuid) {
        return loadedProfiles.containsKey(uuid);
    }

    public void cacheAllOnline() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    loadProfile(player.getUniqueId());
                });

            }
        }.runTaskLater(BoxStaff.getInstance(), 40L);
    }

    public Map<UUID, IProfile> getLoadedProfiles() {
        return loadedProfiles;
    }

    public IProfile getProfile(final UUID uuid) {
        return loadedProfiles.getOrDefault(uuid, null);
    }
}
