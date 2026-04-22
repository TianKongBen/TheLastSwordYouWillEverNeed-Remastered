package net.tianben.tlsywen.mixin;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.tianben.tlsywen.config.ModConfig;
import net.tianben.tlsywen.item.group.ModItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.List;

@Mixin(CreativeModeTab.class)
public abstract class CreativeModeTabMixin {

    @Inject(
            method = "getDisplayItems",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onGetDisplayItems(CallbackInfoReturnable<Collection<ItemStack>> cir) {
        ModConfig config = ModConfig.getInstance();
        CreativeModeTab tab = (CreativeModeTab) (Object) this;

        if (!config.enableModItemGroups &&
                tab == ModItemGroups.the_last_sword_you_will_ever_need.get()) {
            cir.setReturnValue(List.of());
        }
    }
}