package net.tianben.tlsywen.item.group;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
import net.tianben.tlsywen.block.ModBlocks;
import net.tianben.tlsywen.item.ModItems;

public class ModItemGroups {
    public static final ItemGroup the_last_sword_you_will_ever_need = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TheLastSwordYouWillEverNeed.MOD_ID, "the_last_sword_you_will_ever_need"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.the_last_sword_you_will_ever_need"))
                    .icon(() -> new ItemStack(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.COMPRESSED_STAR);
                        entries.add(ModBlocks.DRAGON_CRYSTAL_BLOCK);
                        entries.add(ModBlocks.DRAGON_CRYSTAL_ORE);

                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV2);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV3);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV4);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV5);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV6);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV7);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV8);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV9);
                        entries.add(ModItems.THELASTSWORDYOUWILLEVERNEEDLV10);
                        entries.add(ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED);

                        entries.add(ModItems.DRAGON_CRYSTAL_HELMET);
                        entries.add(ModItems.DRAGON_CRYSTAL_CHESTPLATE);
                        entries.add(ModItems.DRAGON_CRYSTAL_LEGGINGS);
                        entries.add(ModItems.DRAGON_CRYSTAL_BOOTS);

                        entries.add(ModItems.DRAGON_CRYSTAL);
                    }).build());


    public static void registerItemGroups() {
        TheLastSwordYouWillEverNeed.LOGGER .info("Registering Item Groups for " +TheLastSwordYouWillEverNeed.MOD_ID);
    }
}
