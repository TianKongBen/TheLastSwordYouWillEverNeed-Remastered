package net.tianben.tlsywen.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.tianben.tlsywen.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1)
                .add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2)
                .add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3)
                .add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4)
                .add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5)
                .add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6)
                .add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7)
                .add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8)
                .add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9)
                .add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10)
                .add(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED);
    }
}
