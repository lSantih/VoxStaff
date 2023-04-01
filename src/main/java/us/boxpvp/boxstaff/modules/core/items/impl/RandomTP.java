package us.boxpvp.boxstaff.modules.core.items.impl;

import org.apache.commons.lang.math.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.boxpvp.boxstaff.modules.core.items.IStaffitem;
import us.boxpvp.boxstaff.modules.core.items.StaffItemContext;
import us.boxpvp.boxstaff.modules.core.items.StaffItemType;
import us.boxpvp.boxstaff.util.ItemBuilder;
import us.boxpvp.boxstaff.util.TypeCallback;

import java.util.List;
import java.util.stream.Collectors;

public class RandomTP implements IStaffitem {

    private final ItemStack stack;

    public RandomTP() {
        this.stack = new ItemBuilder(Material.MUSIC_DISC_WAIT)
                .setName("#FCCA30&lRandom Teleport")
                .setLore("&7Right click to get teleported to a random player.")
                .setStaffItem(getType().name())
                .toItemStack();
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
            final List<Player> onlines = Bukkit.getOnlinePlayers().stream().collect(Collectors.toList());
            onlines.remove(context.clicker());
            final Player randomPlayer = onlines.get(RandomUtils.nextInt(onlines.size()));

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
