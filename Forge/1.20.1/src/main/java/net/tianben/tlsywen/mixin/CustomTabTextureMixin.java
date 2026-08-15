package net.tianben.tlsywen.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.tianben.tlsywen.config.ConfigManager;
import net.tianben.tlsywen.item.group.ModItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CustomTabTextureMixin {

    @Unique
    private static final ResourceLocation TLSYWEN_TAB_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/gui/container/creative_inventory/tabs_tlsywen.png");

    @Unique
    private static final ResourceLocation VANILLA_BACKGROUND_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/creative_inventory/tab_items.png");

    @Redirect(
            method = "renderTabButton",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"
            )
    )
    private void redirectTabTexture(
            GuiGraphics guiGraphics,
            ResourceLocation texture,
            int x, int y,
            int u, int v,
            int width, int height,
            @Local(argsOnly = true) CreativeModeTab tab
    ) {
        if (ConfigManager.isEnableCustomTab() &&
                tab == ModItemGroups.the_last_sword_you_will_ever_need.get()) {
            guiGraphics.blit(TLSYWEN_TAB_TEXTURE, x, y, u, v, width, height);
        } else {
            guiGraphics.blit(texture, x, y, u, v, width, height);
        }
    }

    @Redirect(
            method = "renderBg",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V",
                    ordinal = 0
            )
    )
    private void redirectBackgroundTexture(
            GuiGraphics guiGraphics,
            ResourceLocation texture,
            int x, int y,
            int u, int v,
            int width, int height
    ) {
        CreativeModeTab selectedTab = CreativeModeInventoryScreenAccessor.getSelectedTab();

        if (selectedTab == ModItemGroups.the_last_sword_you_will_ever_need.get()) {
            if (ConfigManager.isEnableCustomTab()) {
                guiGraphics.blit(texture, x, y, u, v, width, height);
            } else {
                guiGraphics.blit(VANILLA_BACKGROUND_TEXTURE, x, y, u, v, width, height);
            }
        } else {
            guiGraphics.blit(texture, x, y, u, v, width, height);
        }
    }

    @Redirect(
            method = "renderLabels",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/CreativeModeTab;getDisplayName()Lnet/minecraft/network/chat/Component;"
            )
    )
    private Component redirectDisplayName(CreativeModeTab tab) {
        if (tab == ModItemGroups.the_last_sword_you_will_ever_need.get()) {
            if (ConfigManager.isEnableCustomTab()) {
                return tab.getDisplayName().copy().withStyle(ChatFormatting.WHITE);
            }
            return tab.getDisplayName();
        }
        return tab.getDisplayName();
    }
}