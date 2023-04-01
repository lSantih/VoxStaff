package us.boxpvp.boxstaff.modules.core.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.util.ItemBuilder;
import us.boxpvp.boxstaff.util.JavaUtils;
import us.boxpvp.boxstaff.util.gui.ClickAction;
import us.boxpvp.boxstaff.util.gui.Icon;
import us.boxpvp.boxstaff.util.gui.MenuHandler;

public class StaffListGUI extends MenuHandler {

    public StaffListGUI() {
        super(54, "Staff List", "", null);
    }

    public void open(final Player player) {
        setIcon(new Icon(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("&eVoxPvP").toItemStack()), 0, 1, 2, 3, 4, 5, 6, 7, 8, 53, 52, 51, 50, 49, 48, 47, 46, 45);

        int startSlot = 9;

        for (final Player online : Bukkit.getOnlinePlayers()) {
            if (online.hasPermission("voxstaff.staff")) {
                setIcon(startSlot, new Icon(
                        new ItemBuilder(Material.PLAYER_HEAD)
                                .setSkullOwner(online.getName())
                                .setName("&6" + online.getName())
                                .setLore("", "&7Left-Click to teleport")
                                .toItemStack())
                        .addClickAction((player1, type) -> player1.teleport(online.getLocation())));
                startSlot++;
            }
        }

        player.openInventory(getInventory());
    }
}
