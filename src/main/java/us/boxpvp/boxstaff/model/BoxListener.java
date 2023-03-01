package us.boxpvp.boxstaff.model;

import org.bukkit.event.Listener;

public interface BoxListener {

    default Listener getBukkitListener() {
        return (Listener) this;
    }
}
