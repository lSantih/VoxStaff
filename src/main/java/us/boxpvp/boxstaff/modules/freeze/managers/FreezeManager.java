package us.boxpvp.boxstaff.modules.freeze.managers;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.file.impl.YamlFile;
import us.boxpvp.boxstaff.util.location.ILocation;
import us.boxpvp.boxstaff.util.location.impl.SimpleLocation;
import us.boxpvp.boxstaff.modules.profile.IProfile;
import us.boxpvp.boxstaff.util.PlayerUtils;

public class FreezeManager {

    @Getter
    private ILocation freezeLocation;
    private final BoxStaff plugin;
    private final FileConfiguration config;
    private final YamlFile freezeFile;

    public FreezeManager(final BoxStaff plugin) {
        this.plugin = plugin;
         this.freezeFile = new YamlFile("locations/freeze.yml", plugin);
         this.config = freezeFile.getConfig();

        this.freezeLocation = config.getConfigurationSection("data") != null ? SimpleLocation.fromSection(config.getConfigurationSection("data")) : null;
    }

    @SneakyThrows
    public void setFreezeLocation(final Location location) {
        this.freezeLocation = SimpleLocation.fromLocation(location);
        freezeLocation.saveToSection(config.createSection("data"));
        config.save(freezeFile.getFile());
    }

    public boolean isFreezeLocationSet() {
        return freezeLocation != null;
    }

    public boolean isFrozen(final Player player) {
        final IProfile profile = plugin.getProfiles().getProfileManager().getProfile(player.getUniqueId());
        return profile != null && profile.isFrozen();
    }

    public void freezePlayer(final Player player) {
        final IProfile profile = plugin.getProfiles().getProfileManager().getProfile(player.getUniqueId());
        if(profile == null) return;
        if(freezeLocation == null) return;
        //todo save inventory
        player.teleport(freezeLocation.getBukkitLocation());

        profile.setFrozen(true);
        player.setInvulnerable(true);
        PlayerUtils.clear(player, GameMode.ADVENTURE, false);
        player.sendMessage("frozen");
    }

    public void unfreezePlayer(final Player player) {
        final IProfile profile = plugin.getProfiles().getProfileManager().getProfile(player.getUniqueId());
        if(profile == null) return;

        player.setInvulnerable(false);
        profile.setFrozen(false);
        player.sendMessage("unfrozen");
    }

    public void toggleFreeze(final Player player) {
        if(isFrozen(player)) {
            unfreezePlayer(player);
            return;
        }

        freezePlayer(player);
    }
}
