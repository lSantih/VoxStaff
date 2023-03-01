package us.boxpvp.boxstaff.modules.core.events;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import us.boxpvp.boxstaff.model.BoxEvent;

@Data
@RequiredArgsConstructor
public class ModeDisableEvent extends Event implements Cancellable, BoxEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    @NonNull private final Player player;
    private boolean cancelled;

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
    
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
