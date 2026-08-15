package net.tianben.tlsywen.detailab;

import com.redlimerl.detailab.api.DetailArmorBarAPI;
import com.redlimerl.detailab.api.render.ArmorBarRenderManager;
import com.redlimerl.detailab.api.render.TextureOffset;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.item.ModItems;
import org.jetbrains.annotations.ApiStatus;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

public final class DetailArmorBar {

        private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/armor_bar/armor_bar.png");
        private static final int TEXTURE_WIDTH = 18;
        private static final int TEXTURE_HEIGHT = 9;

        private DetailArmorBar() {}

        @ApiStatus.Internal
        public static void register() {
                ArmorItem[] dragonCrystalSet = {
                        (ArmorItem) ModItems.DRAGON_CRYSTAL_HELMET,
                        (ArmorItem) ModItems.DRAGON_CRYSTAL_CHESTPLATE,
                        (ArmorItem) ModItems.DRAGON_CRYSTAL_LEGGINGS,
                        (ArmorItem) ModItems.DRAGON_CRYSTAL_BOOTS
                };

                DetailArmorBarAPI.customArmorBarBuilder()
                        .armor(dragonCrystalSet)
                        .render(stack -> new ArmorBarRenderManager(
                                TEXTURE,
                                TEXTURE_WIDTH,
                                TEXTURE_HEIGHT,
                                new TextureOffset(9, 9),
                                new TextureOffset(0, 9),
                                new TextureOffset(9, 0),
                                new TextureOffset(0, 0)))
                        .register();
        }
}
