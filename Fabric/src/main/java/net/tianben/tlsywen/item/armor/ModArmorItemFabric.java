package net.tianben.tlsywen.item.armor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tianben.tlsywen.attachment.PlayerFlightAttachmentFabric;

public class ModArmorItemFabric extends ModArmorItem {
    public ModArmorItemFabric(ArmorMaterial material, Type type, Properties settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide() && entity instanceof Player player) {
            boolean isWearingFullSet = isWearingFullSet(player);
            boolean hasFlight = PlayerFlightAttachmentFabric.hasFlight(player);

            if (isWearingFullSet && !hasFlight) {
                enableFlight(player);
                PlayerFlightAttachmentFabric.setFlight(player, true);
            } else if (!isWearingFullSet && hasFlight) {
                disableFlight(player);
                PlayerFlightAttachmentFabric.setFlight(player, false);
            }
        }
    }

    private static void enableFlight(Player player) {
        if (!player.isCreative() && !player.isSpectator()) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
    }

    private static void disableFlight(Player player) {
        if (!player.isCreative() && !player.isSpectator()) {
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
            player.onUpdateAbilities();
        }
    }
}