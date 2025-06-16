package net.tianben.tlsywen.detailab;

import com.redlimerl.detailab.api.DetailArmorBarAPI;
import com.redlimerl.detailab.api.render.ArmorBarRenderManager;
import com.redlimerl.detailab.api.render.TextureOffset;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.item.ModItems;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public final class DetailArmorBarFabric {
        private static final ResourceLocation TEXTURE =
                new ResourceLocation(TheLastSwordYouWillEverNeed.MOD_ID, "textures/armor_bar/armor_bar.png");
        private static final int TEXTURE_WIDTH = 18;
        private static final int TEXTURE_HEIGHT = 9;

        private DetailArmorBarFabric() {}

        @ApiStatus.Internal
        public static void register() {
                ArmorItem[] armorSet = {
                        (ArmorItem) ModItems.DRAGON_CRYSTAL_HELMET,
                        (ArmorItem) ModItems.DRAGON_CRYSTAL_CHESTPLATE,
                        (ArmorItem) ModItems.DRAGON_CRYSTAL_LEGGINGS,
                        (ArmorItem) ModItems.DRAGON_CRYSTAL_BOOTS
                };

                DetailArmorBarAPI.customArmorBarBuilder()
                        .armor(armorSet)
                        .render(DetailArmorBarFabric::createRenderManager)
                        .register();
        }

        private static @NotNull ArmorBarRenderManager createRenderManager(ItemStack stack) {
                return new ArmorBarRenderManager(
                        TEXTURE,
                        TEXTURE_WIDTH,
                        TEXTURE_HEIGHT,
                        new TextureOffset(9, 9),
                        new TextureOffset(0, 9),
                        new TextureOffset(9, 0),
                        new TextureOffset(0, 0)
                );
        }
}