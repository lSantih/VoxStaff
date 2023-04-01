package us.boxpvp.boxstaff.modules.rollback.gui;

import dev.santih.sutils.inventory.item.ClickAction;
import dev.santih.sutils.inventory.item.ItemButton;
import dev.santih.sutils.inventory.paginated.PaginatedInventory;
import dev.santih.sutils.inventory.paginated.PaginatedInventoryBuilder;
import dev.santih.sutils.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TesGUI {

    public void open(final Player player) {
        final List<Material> materials = new ArrayList<>();
        materials.add(Material.STONE);
        materials.add(Material.DIAMOND);

        PaginatedInventoryBuilder categoriesInventory = PaginatedInventoryBuilder.create()
                .title("Market")
                .shape("#########" +
                        "#######x#" +
                        "#########" +
                        "#########" +
                        "#########" +
                        "<###@###>")
                .shapeReplacement('@', new ItemButton(new ItemBuilder().type(Material.EMERALD).build(), new ClickAction() {
                    @Override
                    public void onClick(InventoryClickEvent inventoryClickEvent, PaginatedInventory paginatedInventory) {
                    }
                }))
                .shapeReplacement('#', new ItemButton(new ItemBuilder().type(Material.BLACK_STAINED_GLASS_PANE)))
                .content(materials.stream().map(mat -> new ItemButton(new ItemBuilder().type(mat))).toList());
        categoriesInventory.build().show(player);
    }
}
