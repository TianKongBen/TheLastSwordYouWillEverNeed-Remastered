//package net.tianben.tlsywen.compat.task;
//
//import com.github.tartaricacid.touhoulittlemaid.api.task.IMaidTask;
//import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
//import com.mojang.datafixers.util.Pair;
//import net.minecraft.network.chat.Component;
//import net.minecraft.network.chat.MutableComponent;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.sounds.SoundEvent;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.ai.behavior.Behavior;
//import net.minecraft.world.entity.ai.behavior.BehaviorControl;
//import net.minecraft.world.entity.ai.behavior.SetWalkTargetFromAttackTargetIfTargetOutOfReach;
//import net.minecraft.world.entity.ai.behavior.StartAttacking;
//import net.minecraft.world.entity.ai.behavior.StopAttackingIfTargetInvalid;
//import net.minecraft.world.entity.ai.memory.MemoryModuleType;
//import net.minecraft.world.entity.monster.Monster;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.phys.Vec3;
//import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
//import net.tianben.tlsywen.entity.LDEntity;
//import net.tianben.tlsywen.item.ModItems;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.UUID;
//import java.util.function.Predicate;
//
//public class TheLastSwordAttackTask implements IMaidTask {
//
//    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(TheLastSwordYouWillEverNeed.MOD_ID, "last_sword_attack");
//
//    private static final double MELEE_RANGE = 3.0;
//    private static final double MAX_RANGE = 32.0;
//    private static final int MELEE_COOLDOWN = 5;
//    private static final int RANGED_COOLDOWN = 15;
//
//    // 用于跟踪女仆是否正在使用此任务
//    private static final Map<UUID, Boolean> ACTIVE_TASK_MAIDS = new java.util.concurrent.ConcurrentHashMap<java.util.UUID, Boolean>();
//
//    @Override
//    public ResourceLocation getUid() {
//        return UID;
//    }
//
//    @Override
//    public ItemStack getIcon() {
//        return new ItemStack(ModItems.THELASTSWORDYOUWILLEVERNEEDLV1.get());
//    }
//
//    @Override
//    public boolean enableLookAndRandomWalk(@NotNull EntityMaid maid) {
//        return false;
//    }
//
//    @Nullable
//    @Override
//    public SoundEvent getAmbientSound(EntityMaid maid) {
//        return SoundEvents.PLAYER_ATTACK_SWEEP;
//    }
//
//    @Override
//    public List<Pair<Integer, BehaviorControl<? super EntityMaid>>> createBrainTasks(EntityMaid maid) {
//        // 1. 目标选择
//        BehaviorControl<EntityMaid> findTargetTask = StartAttacking.create(
//                this::hasAssaultWeapon,
//                this::findFirstValidAttackTarget
//        );
//
//        // 2. 目标失效检查
//        BehaviorControl<EntityMaid> stopTargetTask = StopAttackingIfTargetInvalid.create(
//                (target) -> !this.hasAssaultWeapon(maid) || this.farAway(target, maid)
//        );
//
//        // 3. 移动靠近目标
//        @SuppressWarnings("unchecked")
//        BehaviorControl<EntityMaid> moveToTargetTask = (BehaviorControl<EntityMaid>)
//                (BehaviorControl<?>) SetWalkTargetFromAttackTargetIfTargetOutOfReach.create(0.6F);
//
//        // 4. 自定义攻击行为（使用非静态内部类，持有女仆引用）
//        BehaviorControl<EntityMaid> attackTask = new TheLastSwordAttackBehavior(maid);
//
//        return List.of(
//                Pair.of(5, findTargetTask),
//                Pair.of(5, stopTargetTask),
//                Pair.of(5, moveToTargetTask),
//                Pair.of(5, attackTask)
//        );
//    }
//
//    @Override
//    public MutableComponent getName() {
//        return Component.translatable("task.tlsywen.last_sword_attack.name");
//    }
//
//    @Override
//    public List<String> getDescription(EntityMaid maid) {
//        return List.of("task.tlsywen.last_sword_attack.desc");
//    }
//
//    @Override
//    public boolean isHidden(EntityMaid maid) {
//        return false;
//    }
//
//    @Override
//    public boolean isEnable(@NotNull EntityMaid maid) {
//        return true;
//    }
//
//    @Override
//    public List<Pair<String, Predicate<EntityMaid>>> getConditionDescription(@NotNull EntityMaid maid) {
//        return List.of(
//                Pair.of("holding_final_sword", this::hasAssaultWeapon)
//        );
//    }
//
//    private boolean hasAssaultWeapon(EntityMaid maid) {
//        if (maid == null || maid.isRemoved()) return false;
//        return isFinalSword(maid.getMainHandItem());
//    }
//
//    private boolean isFinalSword(ItemStack stack) {
//        if (stack == null || stack.isEmpty()) return false;
//        return stack.getItem() == ModItems.THELASTSWORDYOUWILLEVERNEEDLV1.get() ||
//                stack.getItem() == ModItems.THELASTSWORDYOUWILLEVERNEEDLV2.get() ||
//                stack.getItem() == ModItems.THELASTSWORDYOUWILLEVERNEEDLV3.get() ||
//                stack.getItem() == ModItems.THELASTSWORDYOUWILLEVERNEEDLV4.get() ||
//                stack.getItem() == ModItems.THELASTSWORDYOUWILLEVERNEEDLV5.get() ||
//                stack.getItem() == ModItems.THELASTSWORDYOUWILLEVERNEEDLV6.get() ||
//                stack.getItem() == ModItems.THELASTSWORDYOUWILLEVERNEEDLV7.get() ||
//                stack.getItem() == ModItems.THELASTSWORDYOUWILLEVERNEEDLV8.get() ||
//                stack.getItem() == ModItems.THELASTSWORDYOUWILLEVERNEEDLV9.get() ||
//                stack.getItem() == ModItems.THELASTSWORDYOUWILLEVERNEEDLV10.get() ||
//                stack.getItem() == ModItems.REALLYTHELASTSWORDYOUWILLEVERNEED.get();
//    }
//
//    private Optional<? extends LivingEntity> findFirstValidAttackTarget(EntityMaid maid) {
//        if (maid == null || maid.isRemoved() || !hasAssaultWeapon(maid)) {
//            return Optional.empty();
//        }
//
//        LivingEntity target = maid.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
//        if (target != null && isValidTarget(maid, target)) {
//            return Optional.of(target);
//        }
//
//        return findNearestAttackTarget(maid);
//    }
//
//    private boolean isValidTarget(EntityMaid maid, LivingEntity target) {
//        if (target == null || !target.isAlive()) return false;
//        if (target == maid) return false;
//        if (target == maid.getOwner()) return false;
//        if (target instanceof Player) return false;
//        if (target instanceof Monster) return true;
//        return target.getLastHurtByMob() == maid;
//    }
//
//    private Optional<LivingEntity> findNearestAttackTarget(EntityMaid maid) {
//        if (maid == null || maid.isRemoved()) return Optional.empty();
//
//        double range = MAX_RANGE;
//        double nearestDist = range * range;
//        LivingEntity nearestTarget = null;
//
//        for (LivingEntity entity : maid.level().getEntitiesOfClass(
//                LivingEntity.class,
//                maid.getBoundingBox().inflate(range),
//                (target) -> isValidTarget(maid, target)
//        )) {
//            double dist = maid.distanceToSqr(entity);
//            if (dist < nearestDist) {
//                nearestDist = dist;
//                nearestTarget = entity;
//            }
//        }
//
//        return Optional.ofNullable(nearestTarget);
//    }
//
//    private boolean farAway(LivingEntity target, EntityMaid maid) {
//        if (maid == null || maid.isRemoved() || target == null) return true;
//        if (!target.isAlive()) return true;
//        return maid.distanceTo(target) > MAX_RANGE;
//    }
//
//    @Override
//    public String getMaidActionSummary() {
//        return "Melee & ranged attack with last sword";
//    }
//
//    /**
//     * 自定义攻击行为 - 非静态内部类，持有女仆引用
//     */
//    private class TheLastSwordAttackBehavior implements BehaviorControl<EntityMaid> {
//
//        private Behavior.Status status = Behavior.Status.STOPPED;
//        private final EntityMaid ownerMaid;
//
//        public TheLastSwordAttackBehavior(EntityMaid maid) {
//            this.ownerMaid = maid;
//        }
//
//        @Override
//        public Behavior.Status getStatus() {
//            return status;
//        }
//
//        @Override
//        public boolean tryStart(ServerLevel level, EntityMaid maid, long time) {
//            // 检查女仆是否有效
//            if (maid == null || maid.isRemoved() || !maid.isAlive()) {
//                this.status = Behavior.Status.STOPPED;
//                return false;
//            }
//            if (!hasAssaultWeapon(maid)) {
//                this.status = Behavior.Status.STOPPED;
//                return false;
//            }
//            this.status = Behavior.Status.RUNNING;
//            ACTIVE_TASK_MAIDS.put(maid.getUUID(), Boolean.TRUE);
//            return true;
//        }
//
//        @Override
//        public void tickOrStop(ServerLevel level, EntityMaid maid, long time) {
//            // 检查女仆是否有效
//            if (maid == null || maid.isRemoved() || !maid.isAlive()) {
//                this.status = Behavior.Status.STOPPED;
//                ACTIVE_TASK_MAIDS.remove(maid != null ? maid.getUUID() : null);
//                return;
//            }
//
//            if (!hasAssaultWeapon(maid)) {
//                this.status = Behavior.Status.STOPPED;
//                ACTIVE_TASK_MAIDS.remove(maid.getUUID());
//                return;
//            }
//
//            LivingEntity target = maid.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
//            if (target == null || !target.isAlive() || target == maid.getOwner()) {
//                this.status = Behavior.Status.STOPPED;
//                return;
//            }
//
//            this.status = Behavior.Status.RUNNING;
//
//            // 面向目标
//            maid.getLookControl().setLookAt(target, 30.0F, 30.0F);
//
//            double distance = maid.distanceTo(target);
//
//            // 近战攻击
//            if (distance <= MELEE_RANGE) {
//                if (maid.tickCount % MELEE_COOLDOWN == 0) {
//                    maid.doHurtTarget(target);
//                    // 触发闪电
//                    if (!maid.level().isClientSide()) {
//                        var lightning = net.minecraft.world.entity.EntityType.LIGHTNING_BOLT.create(maid.level());
//                        if (lightning != null) {
//                            lightning.moveTo(Vec3.atBottomCenterOf(target.blockPosition()));
//                            maid.level().addFreshEntity(lightning);
//                        }
//                    }
//                    maid.swing(net.minecraft.world.InteractionHand.MAIN_HAND);
//                }
//                return;
//            }
//
//            // 远程投掷
//            if (distance <= MAX_RANGE) {
//                if (maid.tickCount % RANGED_COOLDOWN == 0) {
//                    performRangedAttack(level, maid, target);
//                }
//            }
//        }
//
//        @Override
//        public void doStop(ServerLevel level, EntityMaid maid, long time) {
//            this.status = Behavior.Status.STOPPED;
//            if (maid != null) {
//                ACTIVE_TASK_MAIDS.remove(maid.getUUID());
//            }
//        }
//
//        @Override
//        public String debugString() {
//            return "TheLastSwordAttack";
//        }
//
//        private void performRangedAttack(ServerLevel level, EntityMaid maid, LivingEntity target) {
//            if (maid == null || maid.isRemoved() || !maid.isAlive()) return;
//
//            ItemStack mainHand = maid.getMainHandItem();
//            if (!isFinalSword(mainHand)) return;
//
//            LDEntity projectile = new LDEntity(maid.level(), maid);
//            projectile.setItem(mainHand);
//
//            double dx = target.getX() - maid.getX();
//            double dy = target.getEyeY() - maid.getEyeY();
//            double dz = target.getZ() - maid.getZ();
//            float distance = maid.distanceTo(target);
//
//            float velocity = (float) Math.min(Math.max(distance / 10.0F, 1.2F), 2.5F);
//            float inaccuracy = 0.5f;
//
//            projectile.shoot(dx, dy, dz, velocity, inaccuracy);
//
//            maid.playSound(SoundEvents.SNOWBALL_THROW, 1.0F, 1.0F);
//            level.addFreshEntity(projectile);
//            maid.swing(net.minecraft.world.InteractionHand.MAIN_HAND);
//        }
//    }
//}