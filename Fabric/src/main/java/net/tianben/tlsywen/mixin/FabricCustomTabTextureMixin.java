package net.tianben.tlsywen.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.client.gui.GuiGraphics;
import net.tianben.tlsywen.item.group.ModItemGroupsFabric;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class FabricCustomTabTextureMixin {

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
        if (tab == ModItemGroupsFabric.the_last_sword_you_will_ever_need) {
            ResourceLocation customTabTexture = new ResourceLocation(
                    "tlsywen",
                    "textures/gui/container/creative_inventory/tabs_tlsywen.png"
            );
            guiGraphics.blit(customTabTexture, x, y, u, v, width, height);
        } else {
            guiGraphics.blit(texture, x, y, u, v, width, height);
        }
    }
}
