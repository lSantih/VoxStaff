package us.boxpvp.boxstaff.util.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public interface ClickAction {

    void execute(Player player, ClickType type);

}