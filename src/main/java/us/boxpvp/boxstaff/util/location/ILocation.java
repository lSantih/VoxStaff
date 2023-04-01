package us.boxpvp.boxstaff.util.location;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public interface ILocation {

    double getX();

    double getY();

    double getZ();

    float getYaw();

    float getPitch();

    String getWorldName();

    void teleport(final Player player);

    void saveToSection(final ConfigurationSection section);

    Location getBukkitLocation();

    String getFormatted();
}
