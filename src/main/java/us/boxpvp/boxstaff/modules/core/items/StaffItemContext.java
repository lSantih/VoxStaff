package us.boxpvp.boxstaff.modules.core.items;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public record StaffItemContext(Player clicker, @Nullable Player target) {

}
