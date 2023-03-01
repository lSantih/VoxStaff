package us.boxpvp.boxstaff.modules.profile.events;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import us.boxpvp.boxstaff.model.BoxEvent;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class ProfileLoadEvent extends Event implements Cancellable, BoxEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    @NonNull private final Player player;
    @NonNull private final UUID uuid;
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
