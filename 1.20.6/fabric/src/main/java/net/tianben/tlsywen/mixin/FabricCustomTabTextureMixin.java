package net.tianben.tlsywen.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.tianben.tlsywen.item.group.ModItemGroupsFabric;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class FabricCustomTabTextureMixin {
    @Unique
    private static final ResourceLocation TLSYWEN_SCROLLER_SPRITE =
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_scroller");
    @Unique
    private static final ResourceLocation TLSYWEN_SCROLLER_DISABLED_SPRITE =
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_scroller_disabled");
    @Unique
    private static final ResourceLocation[] TLSYWEN_UNSELECTED_TOP_TABS = new ResourceLocation[]{
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_1"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_2"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_3"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_4"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_5"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_6"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_unselected_7")};
    @Unique
    private static final ResourceLocation[] TLSYWEN_SELECTED_TOP_TABS = new ResourceLocation[]{
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_1"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_2"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_3"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_4"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_5"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_6"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_top_selected_7")};
    @Unique
    private static final ResourceLocation[] TLSYWEN_UNSELECTED_BOTTOM_TABS = new ResourceLocation[]{
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_1"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_2"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_3"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_4"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_5"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_6"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_unselected_7")};
    @Unique
    private static final ResourceLocation[] TLSYWEN_SELECTED_BOTTOM_TABS = new ResourceLocation[]{
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_1"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_2"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_3"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_4"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_5"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_6"),
            new ResourceLocation("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_7")};
    @Shadow
    private static CreativeModeTab selectedTab;

    @ModifyArg(
            method = "renderTabButton",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V",
                    ordinal = 0
            ),
            index = 0
    )
    private ResourceLocation redirectTabTexture(ResourceLocation original,
                                                @Local CreativeModeTab tab) {
        if (tab == ModItemGroupsFabric.the_last_sword_you_will_ever_need) {
            boolean isTopRow = tab.row() == CreativeModeTab.Row.TOP;
            boolean isSelected = tab == selectedTab;
            int column = tab.column();

            if (isTopRow) {
                return isSelected ? TLSYWEN_SELECTED_TOP_TABS[column] : TLSYWEN_UNSELECTED_TOP_TABS[column];
            } else {
                return isSelected ? TLSYWEN_SELECTED_BOTTOM_TABS[column] : TLSYWEN_UNSELECTED_BOTTOM_TABS[column];
            }
        }
        return original;
    }

    @Redirect(
            method = "renderBg",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V",
                    ordinal = 0
            )
    )
    private void redirectScroller(GuiGraphics graphics, ResourceLocation original, int x, int y, int width, int height) {
        if (selectedTab == ModItemGroupsFabric.the_last_sword_you_will_ever_need) {
            ResourceLocation custom = canScroll() ? TLSYWEN_SCROLLER_SPRITE : TLSYWEN_SCROLLER_DISABLED_SPRITE;
            graphics.blitSprite(custom, x, y, width, height);
        } else {
            graphics.blitSprite(original, x, y, width, height);
        }
    }

    @Shadow
    private boolean canScroll() {
        return false;
    }
}