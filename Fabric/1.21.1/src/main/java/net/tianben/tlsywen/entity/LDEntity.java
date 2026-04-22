package net.tianben.tlsywen.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tianben.tlsywen.item.ModItems;

public class LDEntity extends ThrownItemEntity {
    private static final byte PARTICLE_EVENT = 3;
    private static final float BASE_DAMAGE = 1600.0f;

    public LDEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public LDEntity(World world, LivingEntity owner) {
        super(ModEntities.LD, owner, world);
    }

    public void setVelocity(Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        float f = -MathHelper.sin(yaw * (float) (Math.PI / 180.0)) * MathHelper.cos(pitch * (float) (Math.PI / 180.0));
        float g = -MathHelper.sin((pitch + roll) * (float) (Math.PI / 180.0));
        float h = MathHelper.cos(yaw * (float) (Math.PI / 180.0)) * MathHelper.cos(pitch * (float) (Math.PI / 180.0));
        this.setVelocity(f, g, h, speed, divergence);
        Vec3d vec3d = shooter.getVelocity();
        this.setVelocity(this.getVelocity().add(vec3d.x, shooter.isOnGround() ? 0.0 : vec3d.y, vec3d.z));
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
    public void handleStatus(byte status) {
        if (status == PARTICLE_EVENT) {
            Vec3d vec3d = getVelocity();
            var pos = new Vec3d(
                    getX() + vec3d.x,
                    getY() + vec3d.y,
                    getZ() + vec3d.z
            );
            for (int i = 0; i < 4; ++i) {
                getWorld().addParticle(
                        ParticleTypes.POOF,
                        pos.x - vec3d.x * 0.25,
                        pos.y - vec3d.y * 0.25,
                        pos.z - vec3d.z * 0.25,
                        vec3d.x, vec3d.y, vec3d.z
                );
            }
        }
    }

    private void lightning(BlockPos blockPos) {
        LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(getWorld());
        if (lightning != null) {
            lightning.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
            getWorld().spawnEntity(lightning);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        World world = this.getWorld();
        if (world instanceof ServerWorld serverWorld) {
            lightning(entityHitResult.getEntity().getBlockPos());
            var entity = entityHitResult.getEntity();
            var owner = getOwner();

            float damage = BASE_DAMAGE;
            if (owner instanceof PlayerEntity player) {
                var heldItem = player.getMainHandStack();
                if (heldItem.getItem() instanceof ToolItem toolItem) {
                    damage = toolItem.getMaterial().getAttackDamage();
                }

                DamageSource damageSource = getDamageSources().thrown(this, player);
                entity.damage(damageSource, damage);
                EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource);
            } else if (owner != null) {
                DamageSource damageSource = getDamageSources().thrown(this, owner);
                entity.damage(damageSource, damage);
                EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource);
            }
        }

        discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if(!getWorld().isClient()) {
            getWorld().sendEntityStatus(this, PARTICLE_EVENT);
            lightning(blockHitResult.getBlockPos());
        }

        discard();
    }

    @Override
    public void tick() {
        super.tick();
        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        boolean bl = false;
        if (isBurning()) {
            setOnFireFor(1);
        }
        if (hitResult.getType() != HitResult.Type.MISS && !bl) {
            onCollision(hitResult);
        }

        checkBlockCollision();
        Vec3d vec3d = getVelocity();
        setPos(
                getX() + vec3d.x,
                getY() + vec3d.y,
                getZ() + vec3d.z
        );

        updateRotation();

        if (isTouchingWater()) {
            var pos = new Vec3d(getX(), getY(), getZ());
            for (int i = 0; i < 4; ++i) {
                getWorld().addParticle(ParticleTypes.BUBBLE,
                        pos.x - vec3d.x * 0.25,
                        pos.y - vec3d.y * 0.25,
                        pos.z - vec3d.z * 0.25,
                        vec3d.x, vec3d.y, vec3d.z);
            }
        }

        if (!hasNoGravity()) {
            Vec3d vec3d2 = getVelocity();
            this.setVelocity(vec3d2.x, vec3d2.y - getGravity(), vec3d2.z);
        }
    }

    protected double getGravity() {
        return 0f;
    }
}