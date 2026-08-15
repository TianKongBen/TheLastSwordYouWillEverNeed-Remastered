package net.tianben.tlsywen.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.config.ConfigManager;
import net.tianben.tlsywen.item.group.ModItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreativeInventoryScreen.class)
public abstract class CustomTabTextureMixin {

    @Unique
    private static final Identifier TLSYWEN_TAB_TEXTURE =
            new Identifier("tlsywen", "textures/gui/container/creative_inventory/tabs_tlsywen.png");

    @Unique
    private static final Identifier VANILLA_BACKGROUND_TEXTURE =
            new Identifier("minecraft", "textures/gui/container/creative_inventory/tab_items.png");

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
        if (ConfigManager.isEnableCustomTab() &&
                group == ModItemGroups.the_last_sword_you_will_ever_need) {
            context.drawTexture(TLSYWEN_TAB_TEXTURE, x, y, u, v, width, height);
        } else {
            context.drawTexture(originalTexture, x, y, u, v, width, height);
        }
    }

    @Redirect(
            method = "drawBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"
            )
    )
    private void redirectBackgroundTexture(
            DrawContext context,
            Identifier originalTexture,
            int x, int y,
            int u, int v,
            int width, int height
    ) {
        ItemGroup selectedGroup = CreativeModeInventoryScreenAccessor.getSelectedTab();

        if (selectedGroup == ModItemGroups.the_last_sword_you_will_ever_need) {
            if (ConfigManager.isEnableCustomTab()) {
                context.drawTexture(originalTexture, x, y, u, v, width, height);
            } else {
                context.drawTexture(VANILLA_BACKGROUND_TEXTURE, x, y, u, v, width, height);
            }
        } else {
            context.drawTexture(originalTexture, x, y, u, v, width, height);
        }
    }

    @Redirect(
            method = "drawForeground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemGroup;getDisplayName()Lnet/minecraft/text/Text;"
            )
    )
    private Text redirectDisplayName(ItemGroup group) {
        if (group == ModItemGroups.the_last_sword_you_will_ever_need) {
            if (ConfigManager.isEnableCustomTab()) {
                return group.getDisplayName().copy().formatted(Formatting.WHITE);
            }
            return group.getDisplayName();
        }
        return group.getDisplayName();
    }
}
