package us.boxpvp.boxstaff.model.location.impl;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import us.boxpvp.boxstaff.model.location.ILocation;

@AllArgsConstructor
public class SimpleLocation implements ILocation {
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private final String worldName;

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public float getYaw() {
        return yaw;
    }

    @Override
    public float getPitch() {
        return pitch;
    }

    @Override
    public String getWorldName() {
        return worldName;
    }

    @Override
    public void teleport(Player player) {
        player.teleport(getBukkitLocation());
    }

    @Override
    public void saveToSection(ConfigurationSection section) {
        section.set("x", x);
        section.set("y", y);
        section.set("z", z);
        section.set("yaw", yaw);
        section.set("pitch", pitch);
        section.set("worldName", worldName);
    }

    public static SimpleLocation fromLocation(final Location location) {
        return new SimpleLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), location.getWorld().getName());
    }

    public static SimpleLocation fromSection(final ConfigurationSection section) {
        return new SimpleLocation(section.getDouble("x"), section.getDouble("y"), section.getDouble("z"), (float) section.getDouble("yaw"), (float) section.getDouble("pitch"), section.getString("worldName"));
    }
    @Override
    public Location getBukkitLocation() {
        return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
    }

    @Override
    public String getFormatted() {
        return worldName + "," + Math.round(x) + "," + Math.round(y) + "," + Math.round(z);
    }
}
