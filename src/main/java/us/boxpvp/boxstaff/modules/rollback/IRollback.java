package us.boxpvp.boxstaff.modules.rollback;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.BoxStaff;
import us.boxpvp.boxstaff.file.impl.YamlFile;
import us.boxpvp.boxstaff.modules.profile.IProfile;

import java.util.UUID;

public interface IRollback {

    RollbackType getType();

    ItemStack[] getArmorItems();

    ItemStack[] getInventoryItems();

    UUID getRollbackUUID();

    boolean isRestored();

    String getLocation();

    String getTime();

    void setRestored(final boolean newValue);

    @SneakyThrows
    default void save(final IProfile profile) {
        final YamlFile yamlFile = new YamlFile("/players/" + profile.getUUID().toString() + "/rollbacks/" + getRollbackUUID().toString() + ".yml", BoxStaff.getInstance());
        final FileConfiguration config = yamlFile.getConfig();

        config.set("type", getType().name());
        config.set("rollbackUUID", getRollbackUUID().toString());
        config.set("isRestored", isRestored());

        int armorLoop = 0;
        for (ItemStack armorItem : getArmorItems()) {
            if(armorItem != null && armorItem.getType() != Material.AIR) {
                config.set("contents.armor." + armorLoop, armorItem);
                armorLoop++;
            }
        }

        int inventoryLoop = 0;
        for (ItemStack inventoryItem : getInventoryItems()) {
            Bukkit.broadcastMessage("read");
            if(inventoryItem != null && inventoryItem.getType() != Material.AIR) {
                config.set("contents.inventory." + inventoryLoop, inventoryItem);
                inventoryLoop++;
            }
        }

        config.set("location", getLocation());
        config.set("time", getTime());

        config.save(yamlFile.getFile());
    }
}

