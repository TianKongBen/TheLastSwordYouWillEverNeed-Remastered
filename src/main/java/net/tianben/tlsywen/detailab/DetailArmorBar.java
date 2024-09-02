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
        static Identifier texture = new Identifier(MOD_ID, "textures/armor_bar/armor_bar.png");
        @ApiStatus.Internal
        public static void register() {
        ArmorItem[] DragonCrystalSet = {(ArmorItem) ModItems.DRAGON_CRYSTAL_HELMET,
                (ArmorItem) ModItems.DRAGON_CRYSTAL_CHESTPLATE,
                (ArmorItem) ModItems.DRAGON_CRYSTAL_LEGGINGS,
                (ArmorItem) ModItems.DRAGON_CRYSTAL_BOOTS};

        DetailArmorBarAPI.customArmorBarBuilder().armor(DragonCrystalSet)
                .render((ItemStack stack) -> new ArmorBarRenderManager(texture, 18, 9,
                        new TextureOffset(9, 9), new TextureOffset(0, 9),
                        new TextureOffset(9, 0), new TextureOffset(0, 0)))
                .register();
    }
}
