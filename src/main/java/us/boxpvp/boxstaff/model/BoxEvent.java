package us.boxpvp.boxstaff.model;

import org.bukkit.event.Event;

public interface BoxEvent {

    default Event getBukkitEvent() {
        return (Event) this;
    }
}
