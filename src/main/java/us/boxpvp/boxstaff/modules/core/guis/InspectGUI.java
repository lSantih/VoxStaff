package us.boxpvp.boxstaff.modules.core.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.util.JavaUtils;
import us.boxpvp.boxstaff.util.Utils;
import us.boxpvp.boxstaff.util.gui.Icon;
import us.boxpvp.boxstaff.util.gui.MenuHandler;

import java.util.UUID;

public class InspectGUI extends MenuHandler {
    private final Player target;

    public InspectGUI(final Player target) {
        super(54, "Inspecting " + target.getName(), "", null);
        this.target = target;
    }

    public void open(final Player player) {


        setInventoryContents(target);

        player.openInventory(getInventory());
    }

    private void setInventoryContents(Player targetPlayer) {
        ItemStack[] items = targetPlayer.getInventory().getStorageContents();
        ItemStack[] armor = targetPlayer.getInventory().getArmorContents();

        JavaUtils.reverse(armor);
        for (int i = 0; i < items.length; i++) {
            setIcon(i, new Icon(items[i]));
        }
        for (int i = 0; i <= armor.length - 1; i++)
        {
            if (i == 3) {
                setIcon(50 + i, new Icon(targetPlayer.getInventory().getItemInMainHand()));
            }
            setIcon(45 + i, new Icon(armor[i]));
        }
    }

}
