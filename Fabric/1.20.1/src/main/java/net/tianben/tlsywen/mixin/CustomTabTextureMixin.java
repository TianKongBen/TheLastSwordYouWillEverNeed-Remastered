package net.tianben.tlsywen.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.item.group.ModItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreativeInventoryScreen.class)
public abstract class CustomTabTextureMixin {
    @Unique
    private static final Identifier tlsywen_tab_texture =
            new Identifier("tlsywen", "textures/gui/container/creative_inventory/tabs_tlsywen.png");

    @Redirect(
            method = "renderTabIcon",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"
            )
    )
    private void redirectTabTexture(
            DrawContext context,
            Identifier originalTexture,
            int x, int y,
            int u, int v,
            int width, int height,
            @Local(argsOnly = true) ItemGroup group
    ) {
        if (group == ModItemGroups.the_last_sword_you_will_ever_need) {
            context.drawTexture(tlsywen_tab_texture, x, y, u, v, width, height);
        } else {
            context.drawTexture(originalTexture, x, y, u, v, width, height);
        }
    }
}