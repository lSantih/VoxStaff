package us.boxpvp.boxstaff.modules.vanish.managers;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.modules.profile.IProfile;
import us.boxpvp.boxstaff.modules.profile.managers.ProfileManager;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class VanishManager {
    private final ProfileManager profileManager;
    private final BoxStaff boxStaff;

    public void enableVanish(final Player player) {
        Bukkit.getOnlinePlayers().stream().filter(online -> !online.hasPermission("boxstaff.staff")).forEach(online -> online.hidePlayer(player));

        final IProfile profile = profileManager.getProfile(player.getUniqueId());
        profile.setVanish(true);

    }

    public void disableVanish(final Player player) {
        Bukkit.getOnlinePlayers().forEach(online -> online.showPlayer(player));

        final IProfile profile = profileManager.getProfile(player.getUniqueId());
        profile.setVanish(false);
    }

    public void toggleVanish(final Player player) {
        if (isVanished(player)) {
            disableVanish(player);
            return;
        }

        enableVanish(player);
    }

    public boolean isVanished(final Player player) {
        return profileManager.getProfile(player.getUniqueId()).isVanished();
    }

    public List<Player> getVanishedPlayers() {
        final List<Player> returnValue = new ArrayList<>();
        profileManager.getLoadedProfiles().entrySet().forEach(profile -> {
            if (profile.getValue().isVanished()) {
                returnValue.add(Bukkit.getPlayer(profile.getKey()));
            }
        });

        return returnValue;
    }
}
