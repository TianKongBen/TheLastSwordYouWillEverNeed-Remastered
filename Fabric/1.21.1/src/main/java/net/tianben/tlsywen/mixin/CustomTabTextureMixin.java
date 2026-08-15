package net.tianben.tlsywen.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.tianben.tlsywen.config.ConfigManager;
import net.tianben.tlsywen.item.group.ModItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeInventoryScreen.class)
public abstract class CustomTabTextureMixin {
    @Unique
    private static final Identifier vanilla_background_texture =
            Identifier.ofVanilla("textures/gui/container/creative_inventory/tab_items.png");
    @Unique
    private static final Identifier tlsywen_scroller_sprite =
            Identifier.ofVanilla("container/creative_inventory/tlsywen_scroller");
    @Unique
    private static final Identifier vanilla_scroller_sprite =
            Identifier.ofVanilla("container/creative_inventory/scroller");
    @Unique
    private static final Identifier tlsywen_scroller_disabled_sprite =
            Identifier.ofVanilla("container/creative_inventory/tlsuywen_scroller_disabled");
    @Unique
    private static final Identifier vanilla_scroller_disabled_sprite =
            Identifier.ofVanilla("container/creative_inventory/scroller_disabled");
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
    private static final Identifier[] vanilla_unselected_top_tabs = new Identifier[]{
            Identifier.ofVanilla("container/creative_inventory/tab_top_unselected_1"),
            Identifier.ofVanilla("container/creative_inventory/tab_top_unselected_2"),
            Identifier.ofVanilla("container/creative_inventory/tab_top_unselected_3"),
            Identifier.ofVanilla("container/creative_inventory/tab_top_unselected_4"),
            Identifier.ofVanilla("container/creative_inventory/tab_top_unselected_5"),
            Identifier.ofVanilla("container/creative_inventory/tab_top_unselected_6"),
            Identifier.ofVanilla("container/creative_inventory/tab_top_unselected_7")};
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
    private static final Identifier[] vanilla_selected_top_tabs = new Identifier[]{
            Identifier.ofVanilla("container/creative_inventory/tab_top_selected_1"),
            Identifier.ofVanilla("container/creative_inventory/tab_top_selected_2"),
            Identifier.ofVanilla("container/creative_inventory/tab_top_selected_3"),
            Identifier.ofVanilla("container/creative_inventory/tab_top_selected_4"),
            Identifier.ofVanilla("container/creative_inventory/tab_top_selected_5"),
            Identifier.ofVanilla("container/creative_inventory/tab_top_selected_6"),
            Identifier.ofVanilla("container/creative_inventory/tab_top_selected_7")};
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
    private static final Identifier[] vanilla_unselected_bottom_tabs = new Identifier[]{
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_unselected_1"),
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_unselected_2"),
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_unselected_3"),
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_unselected_4"),
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_unselected_5"),
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_unselected_6"),
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_unselected_7")};
    @Unique
    private static final Identifier[] tlsywen_selected_bottom_tabs = new Identifier[]{
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_1"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_2"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_3"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_4"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_5"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_6"),
            Identifier.of("tlsywen", "container/creative_inventory/tlsywen_tab_bottom_selected_7")};
    @Unique
    private static final Identifier[] vanilla_selected_bottom_tabs = new Identifier[]{
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_selected_1"),
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_selected_2"),
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_selected_3"),
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_selected_4"),
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_selected_5"),
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_selected_6"),
            Identifier.ofVanilla("container/creative_inventory/tab_bottom_selected_7")};

    @Shadow
    private static ItemGroup selectedTab;

    @Inject(method = "renderTabIcon", at = @At("HEAD"), cancellable = true)
    private void tlsywen$renderTabIcon(DrawContext context, ItemGroup group, CallbackInfo ci) {
        if (group != ModItemGroups.the_last_sword_you_will_ever_need) return;

        HandledScreenAccessor accessor = (HandledScreenAccessor) (Object) this;
        int leftPos = accessor.tlsywen$getX();
        int topPos = accessor.tlsywen$getY();
        int backgroundHeight = accessor.tlsywen$getBackgroundHeight();

        boolean isSelected = group == selectedTab;
        boolean isTop = group.getRow() == ItemGroup.Row.TOP;
        int column = group.getColumn();

        int x = leftPos + getTabX(group);
        int y = topPos - (isTop ? 28 : -(backgroundHeight - 4));

        boolean custom = ConfigManager.isEnableCustomTab();

        Identifier[] array;
        if (isTop) {
            if (custom) {
                array = isSelected ? tlsywen_selected_top_tabs : tlsywen_unselected_top_tabs;
            } else {
                array = isSelected ? vanilla_selected_top_tabs : vanilla_unselected_top_tabs;
            }
        } else {
            if (custom) {
                array = isSelected ? tlsywen_selected_bottom_tabs : tlsywen_unselected_bottom_tabs;
            } else {
                array = isSelected ? vanilla_selected_bottom_tabs : vanilla_unselected_bottom_tabs;
            }
        }

        Identifier sprite = array[MathHelper.clamp(column, 0, array.length - 1)];
        context.drawGuiTexture(sprite, x, y, 26, 32);

        context.getMatrices().push();
        context.getMatrices().translate(0.0F, 0.0F, 100.0F);

        int iconX = x + 5;
        int iconY = y + 8 + (isTop ? 1 : -1);
        ItemStack icon = group.getIcon();
        context.drawItem(icon, iconX, iconY);
        context.drawItemInSlot(MinecraftClient.getInstance().textRenderer, icon, iconX, iconY);

        context.getMatrices().pop();

        ci.cancel();
    }

    @Unique
    private int getTabX(ItemGroup group) {
        int column = group.getColumn();
        int x = 27 * column;
        if (group.isSpecial()) {
            x = 195 - 27 * (7 - column) + 1;
        }
        return x;
    }

    @Redirect(
            method = "drawBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"
            )
    )
    private void tlsywen$redirectScroller(DrawContext context, Identifier original,
                                          int x, int y, int width, int height) {
        if (selectedTab == ModItemGroups.the_last_sword_you_will_ever_need) {
            boolean custom = ConfigManager.isEnableCustomTab();
            boolean scrollable = selectedTab.hasScrollbar();

            Identifier sprite;
            if (custom) {
                sprite = scrollable ? tlsywen_scroller_sprite : tlsywen_scroller_disabled_sprite;
            } else {
                sprite = scrollable ? vanilla_scroller_sprite : vanilla_scroller_disabled_sprite;
            }
            context.drawGuiTexture(sprite, x, y, width, height);
        } else {
            context.drawGuiTexture(original, x, y, width, height);
        }
    }

    @Redirect(
            method = "drawBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"
            )
    )
    private void tlsywen$redirectBackground(DrawContext context, Identifier texture,
                                            int x, int y, int u, int v, int w, int h) {
        if (selectedTab == ModItemGroups.the_last_sword_you_will_ever_need
                && !ConfigManager.isEnableCustomTab()) {
            context.drawTexture(vanilla_background_texture, x, y, u, v, w, h);
        } else {
            context.drawTexture(texture, x, y, u, v, w, h);
        }
    }

    @Redirect(
            method = "drawForeground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I"
            )
    )
    private int tlsywen$redirectLabelColor(DrawContext instance, TextRenderer textRenderer,
                                           Text text, int x, int y, int color, boolean shadow) {
        if (selectedTab == ModItemGroups.the_last_sword_you_will_ever_need
                && ConfigManager.isEnableCustomTab()) {
            return instance.drawText(textRenderer, text, x, y, 0xFFFFFF, shadow);
        }
        return instance.drawText(textRenderer, text, x, y, color, shadow);
    }
}