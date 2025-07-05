package net.tianben.tlsywen.item.armor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tianben.tlsywen.attachment.PlayerFlightAttachmentNeoForge;
import org.jetbrains.annotations.NotNull;

public final class ModArmorItemNeoForge extends ModArmorItem {
    public ModArmorItemNeoForge(ArmorMaterial material, Type type, Properties settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level,
                             @NotNull Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide() && entity instanceof Player player) {
            updateFlightAbility(player);
        }
    }

    private void updateFlightAbility(@NotNull Player player) {
        boolean shouldHaveFlight = isWearingFullSet(player);
        boolean hasFlight = PlayerFlightAttachmentNeoForge.hasFlight(player);

        if (shouldHaveFlight != hasFlight) {
            setFlightAbility(player, shouldHaveFlight);
            PlayerFlightAttachmentNeoForge.setFlight(player, shouldHaveFlight);
        }
    }

    private void setFlightAbility(@NotNull Player player, boolean enabled) {
        if (player.isCreative() || player.isSpectator()) {
            return;
        }

        player.getAbilities().mayfly = enabled;
        if (!enabled) {
            player.getAbilities().flying = false;
        }
        player.onUpdateAbilities();
    }
}