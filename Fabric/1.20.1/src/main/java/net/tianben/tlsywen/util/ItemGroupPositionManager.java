package net.tianben.tlsywen.util;

import net.fabricmc.fabric.impl.itemgroup.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.tianben.tlsywen.item.group.ModItemGroups;

import java.util.*;

@SuppressWarnings("UnstableApiUsage")
public final class ItemGroupPositionManager {

    private static final int TABS_PER_PAGE = 10;

    private static final Map<String, Position> ORIGINAL_POSITIONS = new HashMap<>();
    private static boolean positionsSaved = false;

    private ItemGroupPositionManager() {}

    public static void saveOriginalPositions() {
        if (positionsSaved) return;
        positionsSaved = true;

        for (ItemGroup group : Registries.ITEM_GROUP) {
            if (group.shouldDisplay() && isModGroup(group)) {
                String id = Objects.requireNonNull(Registries.ITEM_GROUP.getId(group)).toString();
                if (group instanceof FabricItemGroup fig) {
                    ORIGINAL_POSITIONS.put(id, new Position(fig.getPage(), group.getRow(), group.getColumn()));
                }
            }
        }
    }

    public static void redistributePositions(boolean modGroupVisible) {
        saveOriginalPositions();

        List<ItemGroup> modGroups = getModGroupsSorted();

        int count = 0;
        for (ItemGroup group : modGroups) {
            if (!modGroupVisible && group == ModItemGroups.the_last_sword_you_will_ever_need) {
                continue;
            }

            if (group instanceof FabricItemGroup fig) {
                fig.setPage((count / TABS_PER_PAGE) + 1);
            }

            if (group instanceof net.fabricmc.fabric.mixin.itemgroup.ItemGroupAccessor accessor) {
                int pageIndex = count % TABS_PER_PAGE;
                ItemGroup.Row row = pageIndex < (TABS_PER_PAGE / 2) ? ItemGroup.Row.TOP : ItemGroup.Row.BOTTOM;
                accessor.setRow(row);
                accessor.setColumn(row == ItemGroup.Row.TOP ? pageIndex % TABS_PER_PAGE : (pageIndex - TABS_PER_PAGE / 2) % TABS_PER_PAGE);
            }

            count++;
        }
    }

    public static void restoreOriginalPositions() {
        saveOriginalPositions();

        for (ItemGroup group : Registries.ITEM_GROUP) {
            if (group.shouldDisplay() && isModGroup(group)) {
                String id = Objects.requireNonNull(Registries.ITEM_GROUP.getId(group)).toString();
                Position pos = ORIGINAL_POSITIONS.get(id);
                if (pos != null) {
                    if (group instanceof FabricItemGroup fig) {
                        fig.setPage(pos.page);
                    }
                    if (group instanceof net.fabricmc.fabric.mixin.itemgroup.ItemGroupAccessor accessor) {
                        accessor.setRow(pos.row);
                        accessor.setColumn(pos.column);
                    }
                }
            }
        }
    }

    private static List<ItemGroup> getModGroupsSorted() {
        List<ItemGroup> modGroups = new ArrayList<>();
        for (ItemGroup group : Registries.ITEM_GROUP) {
            if (group.shouldDisplay() && isModGroup(group)) {
                modGroups.add(group);
            }
        }
        modGroups.sort(Comparator
                .comparingInt((ItemGroup g) -> {
                    Position pos = ORIGINAL_POSITIONS.get(Objects.requireNonNull(Registries.ITEM_GROUP.getId(g)).toString());
                    return pos != null ? pos.page : Integer.MAX_VALUE;
                })
                .thenComparingInt(g -> {
                    Position pos = ORIGINAL_POSITIONS.get(Objects.requireNonNull(Registries.ITEM_GROUP.getId(g)).toString());
                    return pos != null && pos.row == ItemGroup.Row.TOP ? 0 : 1;
                })
                .thenComparingInt(g -> {
                    Position pos = ORIGINAL_POSITIONS.get(Objects.requireNonNull(Registries.ITEM_GROUP.getId(g)).toString());
                    return pos != null ? pos.column : Integer.MAX_VALUE;
                }));
        return modGroups;
    }

    private static boolean isModGroup(ItemGroup group) {
        return !Objects.requireNonNull(Registries.ITEM_GROUP.getId(group)).getNamespace().equals("minecraft");
    }

    private record Position(int page, ItemGroup.Row row, int column) {}
}