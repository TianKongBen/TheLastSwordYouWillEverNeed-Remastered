package net.tianben.tlsywen.item.armor;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tianben.tlsywen.attachment.PlayerFlightAttachment;
import net.tianben.tlsywen.compat.MaidEffectHandler;
import net.tianben.tlsywen.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static net.tianben.tlsywen.TheLastSwordYouWillEverNeed.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ModArmorItem extends ArmorItem {

    private static final Set<MobEffect> ARMOR_EFFECTS = Set.of(
            MobEffects.NIGHT_VISION,
            MobEffects.DIG_SPEED,
            MobEffects.DAMAGE_BOOST,
            MobEffects.DAMAGE_RESISTANCE
    );

    private static final int EFFECT_AMPLIFIER = 127;
    private static final int EFFECT_DURATION = -1;

    public ModArmorItem(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (level.isClientSide() || level.getGameTime() % 10 != 0) return;
        if (!(entity instanceof LivingEntity livingEntity)) return;

        if (livingEntity instanceof Player player) {
            updatePlayerState(player);
        }

        if (MaidEffectHandler.isMaidLoaded()) {
            MaidEffectHandler.updateMaidEffects(livingEntity);
        }
    }

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (event.getSlot().getType() != EquipmentSlot.Type.ARMOR) return;

        LivingEntity entity = event.getEntity();

        if (entity instanceof Player player) {
            updatePlayerState(player);
        }

        if (MaidEffectHandler.isMaidLoaded()) {
            MaidEffectHandler.updateMaidEffects(entity);
        }
    }

    @SubscribeEvent
    public static void onPlayerChangeGameMode(PlayerEvent.PlayerChangeGameModeEvent event) {
        Player player = event.getEntity();
        if (player == null || player.level().isClientSide()) return;
        updatePlayerState(player);
    }

    private static void updatePlayerState(Player player) {
        boolean isFullSet = isWearingDragonCrystalArmor(player);
        updateEffects(player, isFullSet);
        updateFlightForPlayer(player);
    }

    private static void updateEffects(LivingEntity entity, boolean isFullSet) {
        if (isFullSet) {
            for (MobEffect effect : ARMOR_EFFECTS) {
                var current = entity.getEffect(effect);
                if (current == null || current.getAmplifier() != EFFECT_AMPLIFIER || current.getDuration() != EFFECT_DURATION) {
                    entity.addEffect(new MobEffectInstance(effect, EFFECT_DURATION, EFFECT_AMPLIFIER, false, true, true));
                }
            }
        } else {
            for (MobEffect effect : ARMOR_EFFECTS) {
                var current = entity.getEffect(effect);
                if (current != null && current.getAmplifier() == EFFECT_AMPLIFIER && current.getDuration() == EFFECT_DURATION) {
                    entity.removeEffect(effect);
                }
            }
        }
    }

    public static void updateFlightForPlayer(Player player) {
        if (player.isSpectator()) {
            if (PlayerFlightAttachment.hasFlight(player)) {
                PlayerFlightAttachment.setFlight(player, false);
            }
            if (player.getAbilities().mayfly) {
                player.getAbilities().mayfly = false;
                player.onUpdateAbilities();
            }
            return;
        }

        if (player.isCreative()) {
            if (PlayerFlightAttachment.hasFlight(player)) {
                PlayerFlightAttachment.setFlight(player, false);
            }
            return;
        }

        boolean shouldHaveFlight = isWearingDragonCrystalArmor(player);
        boolean hasFlight = PlayerFlightAttachment.hasFlight(player);

        if (shouldHaveFlight != hasFlight) {
            PlayerFlightAttachment.setFlight(player, shouldHaveFlight);
            PlayerFlightAttachment.applyFlightAbility(player, shouldHaveFlight);
        } else if (shouldHaveFlight) {
            PlayerFlightAttachment.applyFlightAbility(player, true);
        }
    }

    public static boolean isWearingDragonCrystalArmor(Player player) {
        var armor = player.getInventory().armor;
        return armor.get(0).getItem() == ModItems.DRAGON_CRYSTAL_BOOTS.get() &&
                armor.get(1).getItem() == ModItems.DRAGON_CRYSTAL_LEGGINGS.get() &&
                armor.get(2).getItem() == ModItems.DRAGON_CRYSTAL_CHESTPLATE.get() &&
                armor.get(3).getItem() == ModItems.DRAGON_CRYSTAL_HELMET.get();
    }
}