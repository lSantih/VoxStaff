package us.boxpvp.boxstaff.modules.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.model.BoxListener;
import us.boxpvp.boxstaff.modules.core.items.IStaffitem;
import us.boxpvp.boxstaff.modules.core.items.StaffItemContext;
import us.boxpvp.boxstaff.modules.core.managers.StaffModeManager;

public class StaffModeListener implements Listener, BoxListener {
    private final BoxStaff plugin;
    private final StaffModeManager manager;

    public StaffModeListener(BoxStaff plugin) {
        this.plugin = plugin;
        this.manager = plugin.getCore().getStaffModeManager();
    }

    @EventHandler
    private void onBlockBreak(final BlockBreakEvent event) {
        event.setCancelled(manager.isInStaffMode(event.getPlayer()));
    }

    @EventHandler
    private void onBlockPlace(final BlockPlaceEvent event) {
        event.setCancelled(manager.isInStaffMode(event.getPlayer()));
    }

    @EventHandler
    private void onInventoryClick(final InventoryClickEvent event) {
        event.setCancelled(manager.isInStaffMode((Player) event.getWhoClicked()));
    }

    @EventHandler
    private void onInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!event.getAction().isRightClick() || !manager.isInStaffMode(player) || event.getItem() == null) return;
        if(event.getHand() != EquipmentSlot.HAND) return;

        final ItemStack item = event.getItem();
        if(manager.getItem(item) == null) return;

        final IStaffitem staffModeItem = manager.getItem(item);
        staffModeItem.getAction().callback(new StaffItemContext(event.getPlayer(), null));
    }

    @EventHandler
    private void onInteractOnEntity(final PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if(!manager.isInStaffMode(player)) return;
        if(event.getHand() != EquipmentSlot.HAND) return;

        final ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if (!(event.getRightClicked() instanceof Player target)) return;
        if(manager.getItem(item) == null) return;

        final IStaffitem staffModeItem = manager.getItem(item);
        staffModeItem.getAction().callback(new StaffItemContext(event.getPlayer(), target));
    }

    @EventHandler
    private void onItemPickup(final PlayerPickupItemEvent event) {
        event.setCancelled(manager.isInStaffMode(event.getPlayer()));
    }

    @EventHandler
    private void onItemDrop(final PlayerDropItemEvent event) {
        event.setCancelled(manager.isInStaffMode(event.getPlayer()));
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        event.setCancelled(event.getDamager().hasMetadata("staffmode") || event.getEntity().hasMetadata("staffmode"));
    }

    @EventHandler
    private void handleQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        if (player.hasMetadata("staffmode")) manager.toggleMode(player);
        if (player.hasMetadata("vanished")) player.removeMetadata("vanished", plugin);
    }
}
