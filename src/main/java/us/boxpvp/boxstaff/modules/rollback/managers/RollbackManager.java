package us.boxpvp.boxstaff.modules.rollback.managers;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.modules.profile.IProfile;
import us.boxpvp.boxstaff.modules.profile.managers.ProfileManager;
import us.boxpvp.boxstaff.modules.rollback.RollbackType;
import us.boxpvp.boxstaff.modules.rollback.impl.VoxStaffRollback;
import us.boxpvp.boxstaff.util.Utils;
import us.boxpvp.boxstaff.util.location.impl.SimpleLocation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RollbackManager {

    private final ProfileManager profileManager;

    public RollbackManager() {
        this.profileManager = BoxStaff.getInstance().getProfiles().getProfileManager();
    }

    public void saveRollback(final Player player, final RollbackType type) {
        final IProfile profile = profileManager.getProfile(player.getUniqueId());
        if(profile == null) return;

        profile.addRollback(createRollback(player, type));
    }

    public VoxStaffRollback loadRollback(final FileConfiguration config) {
        return VoxStaffRollback.builder()
                .type(RollbackType.valueOf(config.getString("type").toUpperCase()))
                .rollbackUUID(UUID.fromString(config.getString("rollbackUUID")))
                .isRestored(config.getBoolean("isRestored"))
                .inventoryItems(getContents(config))
                .armorItems(getArmorContents(config))
                .location(config.getString("location"))
                .time(config.getString("time"))
                .build();
    }
    public VoxStaffRollback createRollback(final Player player, final RollbackType type) {
        return VoxStaffRollback.builder()
                .type(type)
                .rollbackUUID(UUID.randomUUID())
                .location(SimpleLocation.fromLocation(player.getLocation()).getFormatted())
                .time(Utils.getCurrentDate())
                .armorItems(player.getInventory().getArmorContents())
                .inventoryItems(player.getInventory().getStorageContents())
                .build();
    }

    private ItemStack[] getContents(final FileConfiguration config) {
        final List<ItemStack> items = new ArrayList<>();
        if(config.getConfigurationSection("contents.inventory") == null) return items.toArray(new ItemStack[0]);

        for (String item : config.getConfigurationSection("contents.inventory").getKeys(false)) {
            items.add(config.getItemStack("contents.inventory." + item));
        }

        return items.toArray(new ItemStack[0]);
    }

    private ItemStack[] getArmorContents(final FileConfiguration config) {
        final List<ItemStack> items = new ArrayList<>();
        if(config.getConfigurationSection("contents.armor") == null) return items.toArray(new ItemStack[0]);

        for (String item : config.getConfigurationSection("contents.armor").getKeys(false)) {
            items.add(config.getItemStack("contents.armor." + item));
        }

        return items.toArray(new ItemStack[0]);
    }
}
