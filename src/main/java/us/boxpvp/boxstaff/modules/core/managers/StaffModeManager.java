package us.boxpvp.boxstaff.modules.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.modules.core.events.ModeDisableEvent;
import us.boxpvp.boxstaff.modules.core.events.ModeEnableEvent;
import us.boxpvp.boxstaff.modules.core.items.IStaffitem;
import us.boxpvp.boxstaff.model.SavedInventory;
import us.boxpvp.boxstaff.modules.core.items.StaffItemType;
import us.boxpvp.boxstaff.modules.core.items.impl.*;
import us.boxpvp.boxstaff.util.ItemBuilder;
import us.boxpvp.boxstaff.util.PlayerUtils;

import javax.naming.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StaffModeManager {
    private static List<IStaffitem> items;
    private final BoxStaff plugin;
    private final Map<UUID, SavedInventory> playersInventory = new HashMap<>();

    private NamespacedKey STAFF_ITEM_KEY = new NamespacedKey(BoxStaff.getInstance(), "VOX_STAFF_ITEM");

    public StaffModeManager(final BoxStaff plugin) {
        this.plugin = plugin;
    }

    public void loadItems() {
        if(items != null) return; //Prevent loading the items two times
        items = List.of(
                new RandomTP(),
                new VanishOn(),
                new VanishOff(),
                new Freeze(),
                new Compass(),
                new Inspect(),
                new Punish(),
                new StaffList()
        );
    }

    public static IStaffitem getItemByClass(Class<? extends IStaffitem> clazz) {
        for (IStaffitem item : items) {
            if(item.getClass().equals(clazz)) {
                return item;
            }
        }
        return null;
    }

    public boolean isStaffModeItem(final ItemStack itemStack) {
        if(!itemStack.hasItemMeta()) return false;

        return itemStack.getItemMeta().getPersistentDataContainer().has(STAFF_ITEM_KEY);
    }

    public IStaffitem getItem(final ItemStack item) {
        if(!isStaffModeItem(item)) return null;
        final String itemType = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "itemIdentifier"), PersistentDataType.STRING);

        return items.stream().filter(stack -> stack.getType() == StaffItemType.valueOf(itemType.toUpperCase())).findFirst().orElse(null);
    }

    public void enableMode(final Player player) {
        final ModeEnableEvent modeEnableEvent = new ModeEnableEvent(player);
        Bukkit.getPluginManager().callEvent(modeEnableEvent);
        if(modeEnableEvent.isCancelled()) return;

        player.setMetadata("staffmode", new FixedMetadataValue(plugin, true));

        playersInventory.put(player.getUniqueId(), SavedInventory.fromInventory(player));
        PlayerUtils.clear(player, GameMode.CREATIVE, true);

        items.forEach(item -> {
            if(item instanceof VanishOff) return;
            if(item.getType() == StaffItemType.STAFF_LIST) {
                final ItemStack updatedItem = new ItemBuilder(item.getStack()).setSkullOwner(player.getName()).toItemStack();
                player.getInventory().setItem(item.getItemSlot(), updatedItem);
            }
            item.giveItem(player);
        });

        player.sendMessage("§aStaff mode enabled.");
    }

    public void disableMode(final Player player) {
        final ModeDisableEvent modeDisableEvent = new ModeDisableEvent(player);
        Bukkit.getPluginManager().callEvent(modeDisableEvent);
        if(modeDisableEvent.isCancelled()) return;

        player.removeMetadata("staffmode", plugin);

        if(playersInventory.containsKey(player.getUniqueId())) playersInventory.get(player.getUniqueId()).restore(player);
        playersInventory.remove(player.getUniqueId());

        player.setGameMode(GameMode.SURVIVAL);
        player.setAllowFlight(false);

        player.sendMessage("§cStaff mode disabled.");

    }

    public boolean isInStaffMode(Player player) {
        return player.hasMetadata("staffmode");
    }

    public boolean isInStaffMode(final UUID uuid) {
        return Bukkit.getPlayer(uuid) != null && isInStaffMode(Bukkit.getPlayer(uuid));
    }

    public void toggleMode(Player player) {
        if(isInStaffMode(player)) {
            disableMode(player);
            return;
        }

        enableMode(player);
    }
}
