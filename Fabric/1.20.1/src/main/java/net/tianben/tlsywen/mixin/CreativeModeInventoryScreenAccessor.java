package net.tianben.tlsywen.mixin;

import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreativeInventoryScreen.class)
public interface CreativeModeInventoryScreenAccessor {
    @Accessor("selectedTab")
    static ItemGroup getSelectedTab() {
        throw new AssertionError();
    }
}
