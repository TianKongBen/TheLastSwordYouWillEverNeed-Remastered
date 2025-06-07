package net.tianben.tlsywen.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tianben.tlsywen.item.ModItems;
import net.tianben.tlsywen.platform.PlatformBridgeHolder;

public class LDEntity extends ThrowableItemProjectile {
    public LDEntity(EntityType<? extends LDEntity> type, Level level) {
        super(type, level);
    }

    public static LDEntity createForRegistration(EntityType<?> type, Level level) {
        return new LDEntity((EntityType<? extends LDEntity>) type, level);
    }

    public LDEntity(Level level, LivingEntity owner) {
        super(PlatformBridgeHolder.BRIDGE.getLDEntityType(), level);
        setOwner(owner);
        setPos(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.THELASTSWORDYOUWILLEVERNEEDLV1;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    protected boolean isBurning() {
        return false;
    }

    @Override
    public void handleEntityEvent(byte status) {
        if (status == 3) { // Particle effect status
            Vec3 vec3 = this.getDeltaMovement();
            double d = this.getX() + vec3.x;
            double e = this.getY() + vec3.y;
            double f = this.getZ() + vec3.z;
            for (int i = 0; i < 4; ++i) {
                this.level().addParticle(ParticleTypes.POOF, 
                    d - vec3.x * 0.25, 
                    e - vec3.y * 0.25, 
                    f - vec3.z * 0.25, 
                    vec3.x, vec3.y, vec3.z);
            }
        }
    }

    private void lightning(BlockPos blockPos) {
        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(this.level());
        if (lightning != null) {
            lightning.moveTo(Vec3.atBottomCenterOf(blockPos));
            this.level().addFreshEntity(lightning);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (!this.level().isClientSide()) {
            lightning(entityHitResult.getEntity().blockPosition());
            Entity entity = entityHitResult.getEntity();
            Entity owner = this.getOwner();
            
            float damage = 1600.0f;
            if (owner instanceof Player player) {
                ItemStack heldItem = player.getMainHandItem();
                if (heldItem.getItem() instanceof TieredItem tieredItem) {
                    damage = tieredItem.getTier().getAttackDamageBonus();
                }
                entity.hurt(this.damageSources().thrown(this, player), damage);
                this.doEnchantDamageEffects(player, entity);
            } else if (owner != null) {
                entity.hurt(this.damageSources().thrown(this, owner), damage);
            }
        }
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte)3);
            lightning(blockHitResult.getBlockPos());
        }
        this.discard();
    }

    public void tick() {
        super.tick();
        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        boolean bl = false;
        if (this.isBurning()) {
            this.setSecondsOnFire(1);
        }
        if (hitResult.getType() != HitResult.Type.MISS && !bl) {
            this.onHit(hitResult);
        }

        this.checkInsideBlocks();
        Vec3 vec3 = this.getDeltaMovement();
        double d = this.getX() + vec3.x;
        double e = this.getY() + vec3.y;
        double f = this.getZ() + vec3.z;

        this.updateRotation();

        if (this.isInWater()) {
            for (int i = 0; i < 4; ++i) {
                this.level().addParticle(ParticleTypes.BUBBLE,
                        d - vec3.x * 0.25,
                        e - vec3.y * 0.25,
                        f - vec3.z * 0.25,
                        vec3.x, vec3.y, vec3.z);
            }
        }

        if (!this.isNoGravity()) {
            Vec3 vec3d2 = this.getDeltaMovement();
            this.setDeltaMovement(vec3d2.x, vec3d2.y - this.getGravity(), vec3d2.z);
        }
        this.setPos(d, e, f);
    }

    @Override
    protected float getGravity() {
        return 0f;
    }
}