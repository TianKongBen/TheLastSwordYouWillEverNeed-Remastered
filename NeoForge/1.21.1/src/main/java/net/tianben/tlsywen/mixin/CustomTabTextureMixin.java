package net.tianben.tlsywen.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.gui.CreativeTabsScreenPage;
import net.tianben.tlsywen.config.ConfigManager;
import net.tianben.tlsywen.item.group.ModItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.stream.IntStream;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CustomTabTextureMixin {

    @Unique
    private static final ResourceLocation VANILLA_BACKGROUND_TEXTURE =
            ResourceLocation.withDefaultNamespace("textures/gui/container/creative_inventory/tab_items.png");

    @Unique
    private static final ResourceLocation VANILLA_SCROLLER_SPRITE =
            ResourceLocation.withDefaultNamespace("container/creative_inventory/scroller");

    @Unique
    private static final ResourceLocation TLSYWEN_SCROLLER_SPRITE = VANILLA_SCROLLER_SPRITE;

    @Unique
    private static final ResourceLocation VANILLA_SCROLLER_DISABLED_SPRITE =
            ResourceLocation.withDefaultNamespace("container/creative_inventory/scroller_disabled");

    @Unique
    private static final ResourceLocation TLSYWEN_SCROLLER_DISABLED_SPRITE = VANILLA_SCROLLER_DISABLED_SPRITE;

    @Unique
    private static final int TAB_ROW_COUNT = 7;

    @Unique
    private static final int TAB_WIDTH = 26;
    @Unique
    private static final int TAB_HEIGHT = 32;

    @Unique
    private static final ResourceLocation[][][] TAB_TEXTURES = {
            {
                    buildTlsywenTabRow("top_unselected", TAB_ROW_COUNT),
                    buildTlsywenTabRow("top_selected", TAB_ROW_COUNT),
                    buildTlsywenTabRow("bottom_unselected", TAB_ROW_COUNT),
                    buildTlsywenTabRow("bottom_selected", TAB_ROW_COUNT),
            },
            {
                    buildVanillaTabRow("top_unselected", TAB_ROW_COUNT),
                    buildVanillaTabRow("top_selected", TAB_ROW_COUNT),
                    buildVanillaTabRow("bottom_unselected", TAB_ROW_COUNT),
                    buildVanillaTabRow("bottom_selected", TAB_ROW_COUNT),
            },
    };

    @Unique
    private static ResourceLocation[] buildTlsywenTabRow(String suffix, int count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> ResourceLocation.fromNamespaceAndPath(
                        "tlsywen", "container/creative_inventory/tlsywen_tab_" + suffix + "_" + i))
                .toArray(ResourceLocation[]::new);
    }

    @Unique
    private static ResourceLocation[] buildVanillaTabRow(String suffix, int count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> ResourceLocation.withDefaultNamespace(
                        "container/creative_inventory/tab_" + suffix + "_" + i))
                .toArray(ResourceLocation[]::new);
    }


    @Shadow
    private static CreativeModeTab selectedTab;

    @Shadow
    private CreativeTabsScreenPage currentPage;

    @Shadow
    private int getTabX(CreativeModeTab tab) {
        return 0;
    }

    @Shadow
    private boolean canScroll() {
        return false;
    }


    @Inject(method = "renderTabButton", at = @At("HEAD"), cancellable = true)
    private void tlsywen$renderTabButton(GuiGraphics guiGraphics, CreativeModeTab tab, CallbackInfo ci) {
        if (tab != ModItemGroups.the_last_sword_you_will_ever_need.get()) {
            return;
        }

        var accessor = (AbstractContainerScreenAccessor) this;
        int leftPos = accessor.tlsywen$getLeftPos();
        int topPos = accessor.tlsywen$getTopPos();
        int imageHeight = accessor.tlsywen$getImageHeight();

        boolean isSelected = tab == selectedTab;
        boolean isTop = currentPage.isTop(tab);
        int column = currentPage.getColumn(tab);

        int x = leftPos + getTabX(tab);
        int y = topPos - (isTop ? 28 : -(imageHeight - 4));

        var sprite = selectTabTexture(isTop, isSelected, column);
        guiGraphics.blitSprite(sprite, x, y, TAB_WIDTH, TAB_HEIGHT);

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0F, 0.0F, 100.0F);

        int iconX = x + 5;
        int iconY = y + 8 + (isTop ? 1 : -1);
        ItemStack icon = tab.getIconItem();
        guiGraphics.renderItem(icon, iconX, iconY);
        guiGraphics.renderItemDecorations(Minecraft.getInstance().font, icon, iconX, iconY);

        guiGraphics.pose().popPose();

        ci.cancel();
    }

    @Unique
    private static ResourceLocation selectTabTexture(boolean isTop, boolean isSelected, int column) {
        int namespace = ConfigManager.isEnableCustomTab() ? 0 : 1;
        int row = (isTop ? 0 : 2) + (isSelected ? 1 : 0);

        var rowTextures = TAB_TEXTURES[namespace][row];
        return rowTextures[Mth.clamp(column, 0, rowTextures.length - 1)];
    }

    @Redirect(
            method = "renderBg",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V"
            )
    )
    private void tlsywen$redirectScroller(GuiGraphics graphics, ResourceLocation original,
                                          int x, int y, int width, int height) {
        if (selectedTab != ModItemGroups.the_last_sword_you_will_ever_need.get()) {
            graphics.blitSprite(original, x, y, width, height);
            return;
        }

        boolean custom = ConfigManager.isEnableCustomTab();
        boolean scrollable = canScroll();

        var sprite = custom
                ? (scrollable ? TLSYWEN_SCROLLER_SPRITE : TLSYWEN_SCROLLER_DISABLED_SPRITE)
                : (scrollable ? VANILLA_SCROLLER_SPRITE : VANILLA_SCROLLER_DISABLED_SPRITE);

        graphics.blitSprite(sprite, x, y, width, height);
    }

    @Redirect(
            method = "renderBg",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"
            )
    )
    private void tlsywen$redirectBackground(GuiGraphics graphics, ResourceLocation texture,
                                            int x, int y, int u, int v, int w, int h) {
        boolean isOurTab = selectedTab == ModItemGroups.the_last_sword_you_will_ever_need.get();
        boolean useVanilla = isOurTab && !ConfigManager.isEnableCustomTab();

        graphics.blit(useVanilla ? VANILLA_BACKGROUND_TEXTURE : texture, x, y, u, v, w, h);
    }

    @Redirect(
            method = "renderLabels",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/CreativeModeTab;getLabelColor()I"
            )
    )
    private int tlsywen$redirectLabelColor(CreativeModeTab tab) {
        boolean isOurTab = tab == ModItemGroups.the_last_sword_you_will_ever_need.get();
        return isOurTab && ConfigManager.isEnableCustomTab() ? 0xFFFFFF : tab.getLabelColor();
    }
}