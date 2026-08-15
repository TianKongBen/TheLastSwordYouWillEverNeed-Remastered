//package net.tianben.tlsywen.compat;
//
//import com.github.tartaricacid.touhoulittlemaid.api.ILittleMaid;
//import com.github.tartaricacid.touhoulittlemaid.api.LittleMaidExtension;
//import com.github.tartaricacid.touhoulittlemaid.entity.task.TaskManager;
//import net.tianben.tlsywen.TheLastSwordYouWillEverNeed;
//import net.tianben.tlsywen.compat.task.TheLastSwordAttackTask;
//
//@LittleMaidExtension
//public class TheLastSwordTaskExtension implements ILittleMaid {
//
//    @Override
//    public void addMaidTask(TaskManager manager) {
//        if (TaskManager.getTaskMap().containsKey(TheLastSwordAttackTask.UID)) {
//            TheLastSwordYouWillEverNeed.LOGGER.info("最终之剑攻击任务已存在，跳过注册");
//            return;
//        }
//        manager.add(new TheLastSwordAttackTask());
//        TheLastSwordYouWillEverNeed.LOGGER.info("通过 @LittleMaidExtension 注册最终之剑攻击任务: {}", TheLastSwordAttackTask.UID);
//    }
//}