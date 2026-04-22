package net.tianben.tlsywen.detailab;

import com.redlimerl.detailab.api.DetailArmorBarAPI;
import com.redlimerl.detailab.api.render.ArmorBarRenderManager;
import com.redlimerl.detailab.api.render.TextureOffset;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.item.ModItems;
import org.jetbrains.annotations.ApiStatus;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public class DetailArmorBar {
        private static final Identifier texture = new Identifier(MOD_ID, "textures/armor_bar/armor_bar.png");
        private static final int texture_width = 18;
        private static final int texture_height = 9;

        @ApiStatus.Internal
        public static void register() {
        ArmorItem[] DragonCrystalSet = {
                (ArmorItem) ModItems.DRAGON_CRYSTAL_HELMET,
                (ArmorItem) ModItems.DRAGON_CRYSTAL_CHESTPLATE,
                (ArmorItem) ModItems.DRAGON_CRYSTAL_LEGGINGS,
                (ArmorItem) ModItems.DRAGON_CRYSTAL_BOOTS
        };

        DetailArmorBarAPI.customArmorBarBuilder().armor(DragonCrystalSet)
                .render((ItemStack stack) -> new ArmorBarRenderManager(
                        texture,
                        texture_width,
                        texture_height,
                        new TextureOffset(9, 9),
                        new TextureOffset(0, 9),
                        new TextureOffset(9, 0),
                        new TextureOffset(0, 0)))
                .register();
    }
}
