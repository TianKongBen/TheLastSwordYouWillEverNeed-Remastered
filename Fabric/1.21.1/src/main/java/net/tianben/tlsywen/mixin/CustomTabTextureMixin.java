package net.tianben.tlsywen.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.item.group.ModItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreativeInventoryScreen.class)
public abstract class CustomTabTextureMixin {
    @Unique
    private static final Identifier tlsywen_scroller_sprite =
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_scroller");
    @Unique
    private static final Identifier tlsywen_scroller_disabled_sprite =
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_scroller_disabled");
    @Unique
    private static final Identifier[] tlsywen_unselected_top_tabs = new Identifier[]{
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_1"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_2"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_3"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_4"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_5"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_6"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_7")};
    @Unique
    private static final Identifier[] tlsywen_selected_top_tabs = new Identifier[]{
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_1"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_2"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_3"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_4"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_5"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_6"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_7")};
    @Unique
    private static final Identifier[] tlsywen_unselected_bottom_tabs = new Identifier[]{
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_1"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_2"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_3"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_4"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_5"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_6"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_7")};
    @Unique
    private static final Identifier[] tlsywen_selected_bottom_tabs = new Identifier[]{
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_1"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_2"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_3"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_4"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_5"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_6"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_7")};

    @Shadow
    private static ItemGroup selectedTab;

    @ModifyArg(
            method = "renderTabIcon",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V",
                    ordinal = 0
            ),
            index = 0
    )
    private Identifier redirectTabTexture(Identifier identifier, @Local(argsOnly = true) ItemGroup group) {
        if (group == ModItemGroups.the_last_sword_you_will_ever_need) {
            boolean isTopRow = group.getRow() == ItemGroup.Row.TOP;
            boolean isSelected = group == selectedTab;
            int column = group.getColumn();
            if (isTopRow) {
                return isSelected ? tlsywen_selected_top_tabs[column] : tlsywen_unselected_top_tabs[column];
            } else {
                return isSelected ? tlsywen_selected_bottom_tabs[column] : tlsywen_unselected_bottom_tabs[column];
            }
        }
        return identifier;
    }

    @Redirect(
            method = "drawBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V",
                    ordinal = 0
            )
    )
    private void redirectScroller(DrawContext drawContext, Identifier identifier, int x, int y, int width, int height) {
        if (selectedTab == ModItemGroups.the_last_sword_you_will_ever_need) {
            Identifier custom = hasScrollbar() ? tlsywen_scroller_sprite : tlsywen_scroller_disabled_sprite;
            drawContext.drawGuiTexture(custom, x, y, width, height);
        } else {
            drawContext.drawGuiTexture(identifier, x, y, width, height);
        }
    }

    @Shadow
    private boolean hasScrollbar() {
        return false;
    }
}