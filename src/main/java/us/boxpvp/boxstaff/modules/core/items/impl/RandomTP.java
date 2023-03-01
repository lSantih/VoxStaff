package us.boxpvp.boxstaff.modules.core.items.impl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.modules.core.items.IStaffitem;
import us.boxpvp.boxstaff.modules.core.items.StaffItemContext;
import us.boxpvp.boxstaff.modules.core.items.StaffItemType;
import us.boxpvp.boxstaff.util.ItemBuilder;
import us.boxpvp.boxstaff.util.TypeCallback;

public class RandomTP implements IStaffitem {

    private final ItemStack stack;

    public RandomTP() {
        this.stack = new ItemBuilder(Material.MUSIC_DISC_WAIT).setName("#FCCA30&lRandom Teleport").setLore("&7Right click to get teleported to a random player.").toItemStack();
    }
    @Override
    public ItemStack getStack() {
        return stack;
    }

    @Override
    public int getItemSlot() {
        return 8;
    }

    @Override
    public TypeCallback<StaffItemContext> getAction() {
        return context -> {
            final Player randomPlayer = Bukkit.getOnlinePlayers().stream().filter(player -> player.getUniqueId() != context.clicker().getUniqueId()).findAny().orElse(null);
            if(randomPlayer == null) {
                context.clicker().sendMessage("no player found");
                return;
            }

            context.clicker().teleport(randomPlayer.getLocation());
            context.clicker().sendMessage("teleported to " + randomPlayer.getName());
        };
    }

    @Override
    public StaffItemType getType() {
        return StaffItemType.RANDOM_TP;
    }
}
