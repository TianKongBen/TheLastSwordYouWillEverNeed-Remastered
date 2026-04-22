package net.tianben.tlsywen.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tianben.tlsywen.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class LDEntity extends ThrowableItemProjectile {
    private static final byte PARTICLE_EVENT = 3;
    private static final float BASE_DAMAGE = 1600.0f;

    public LDEntity(EntityType<LDEntity> entityType, Level level) {
        super(entityType, level);
    }

    public LDEntity(Level level, LivingEntity shooter) {
        super(ModEntities.LD.get(), shooter, level);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.THELASTSWORDYOUWILLEVERNEEDLV1.get();
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public void handleEntityEvent(byte status) {
        if (status == PARTICLE_EVENT) {
            var vec3 = this.getDeltaMovement();
            var pos = new Vec3(
                    this.getX() + vec3.x,
                    this.getY() + vec3.y,
                    this.getZ() + vec3.z
            );
            for (int i = 0; i < 4; ++i) {
                level().addParticle(ParticleTypes.POOF,
                        pos.x - vec3.x * 0.25,
                        pos.y - vec3.y * 0.25,
                        pos.z - vec3.z * 0.25,
                        vec3.x, vec3.y, vec3.z);
            }
        }
    }

    private void lightning(BlockPos blockPos) {
        var lightning = EntityType.LIGHTNING_BOLT.create(level());
        if (lightning != null) {
            lightning.moveTo(Vec3.atBottomCenterOf(blockPos));
            level().addFreshEntity(lightning);
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        if (!level().isClientSide()) {
            lightning(entityHitResult.getEntity().blockPosition());
            var entity = entityHitResult.getEntity();
            var owner = getOwner();

            float damage = BASE_DAMAGE;
            if (owner instanceof Player player) {
                var heldItem = player.getMainHandItem();
                if (heldItem.getItem() instanceof TieredItem tieredItem) {
                    damage = tieredItem.getTier().getAttackDamageBonus();
                }
                // 1.21.1 中改用 Entity#hurt 的伤害类型重载，由原版自动处理附魔效果
                entity.hurt(damageSources().playerAttack(player), damage);
            } else if (owner instanceof LivingEntity livingOwner) {
                entity.hurt(damageSources().mobAttack(livingOwner), damage);
            } else {
                entity.hurt(damageSources().thrown(this, owner), damage);
            }
        }
        discard();
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        if (!level().isClientSide()) {
            level().broadcastEntityEvent(this, PARTICLE_EVENT);
            lightning(blockHitResult.getBlockPos());
        }
        discard();
    }

    @Override
    public void tick() {
        super.tick();

        if (isInWater()) {
            var vec3 = getDeltaMovement();
            var pos = position();
            for (int i = 0; i < 4; ++i) {
                level().addParticle(ParticleTypes.BUBBLE,
                        pos.x - vec3.x * 0.25,
                        pos.y - vec3.y * 0.25,
                        pos.z - vec3.z * 0.25,
                        vec3.x, vec3.y, vec3.z);
            }
        }

        if (!isNoGravity()) {
            var movement = getDeltaMovement();
            setDeltaMovement(movement.x, movement.y - getDefaultGravity(), movement.z);
        }
    }

    protected double getDefaultGravity() {
        return 0.0;
    }
}