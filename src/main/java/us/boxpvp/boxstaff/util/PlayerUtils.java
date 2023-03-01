package us.boxpvp.boxstaff.util;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static void clear(final Player player, final GameMode gameMode, final boolean allowFlight) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        player.setHealth(20);
        player.setFireTicks(0);
        player.setFoodLevel(20);

        player.setGameMode(gameMode);
        player.setAllowFlight(allowFlight);

    }
}
