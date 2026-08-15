package net.tianben.tlsywen.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.tianben.tlsywen.config.ConfigManager;
import net.tianben.tlsywen.item.group.ModItemGroups;
import net.tianben.tlsywen.util.ItemGroupPositionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeModeInventoryScreenMixin {

    @Unique
    private static Field tlsywen$selectedTabField;
    @Unique
    private static Method tlsywen$setSelectedTabMethod;
    @Unique
    private static boolean tlsywen$lastEnableModItemGroups = true;

    @Unique
    private static Field tlsywen$getSelectedTabField() {
        if (tlsywen$selectedTabField == null) {
            try {
                tlsywen$selectedTabField = CreativeInventoryScreen.class.getDeclaredField("selectedTab");
                tlsywen$selectedTabField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.fillInStackTrace();
            }
        }
        return tlsywen$selectedTabField;
    }

    @Unique
    private static Method tlsywen$getSetSelectedTabMethod() {
        if (tlsywen$setSelectedTabMethod == null) {
            try {
                tlsywen$setSelectedTabMethod = CreativeInventoryScreen.class.getDeclaredMethod("setSelectedTab", ItemGroup.class);
                tlsywen$setSelectedTabMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                e.fillInStackTrace();
            }
        }
        return tlsywen$setSelectedTabMethod;
    }

    @Unique
    private static ItemGroup tlsywen$getSelectedTab() {
        try {
            Field field = tlsywen$getSelectedTabField();
            if (field != null) return (ItemGroup) field.get(null);
        } catch (IllegalAccessException e) {
            e.fillInStackTrace();
        }
        return null;
    }

    @Unique
    private static void tlsywen$setSelectedTabField(ItemGroup group) {
        try {
            Field field = tlsywen$getSelectedTabField();
            if (field != null) field.set(null, group);
        } catch (IllegalAccessException e) {
            e.fillInStackTrace();
        }
    }

    @Unique
    private static void tlsywen$callSetSelectedTab(CreativeInventoryScreen screen, ItemGroup group) {
        try {
            Method method = tlsywen$getSetSelectedTabMethod();
            if (method != null) method.invoke(screen, group);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @Unique
    private static int tlsywen$getModGroupPage() {
        ItemGroup modGroup = ModItemGroups.the_last_sword_you_will_ever_need;
        if (modGroup instanceof net.fabricmc.fabric.impl.itemgroup.FabricItemGroup fig) {
            return fig.getPage();
        }
        return -1;
    }

    @Unique
    private static List<ItemGroup> tlsywen$getVisibleGroupsOnPage(int page, boolean includeModGroup) {
        List<ItemGroup> groups = new ArrayList<>();
        for (ItemGroup group : Registries.ITEM_GROUP) {
            if (!group.shouldDisplay()) continue;
            if (!includeModGroup && group == ModItemGroups.the_last_sword_you_will_ever_need) continue;
            if (group instanceof net.fabricmc.fabric.impl.itemgroup.FabricItemGroup fig && fig.getPage() == page) {
                groups.add(group);
            }
        }
        groups.sort(Comparator
                .comparingInt((ItemGroup g) -> g.getRow() == ItemGroup.Row.TOP ? 0 : 1)
                .thenComparingInt(ItemGroup::getColumn));
        return groups;
    }

    @Unique
    private static ItemGroup tlsywen$findFirstTabOnPage(int page, boolean includeModGroup) {
        List<ItemGroup> groups = tlsywen$getVisibleGroupsOnPage(page, includeModGroup);
        return groups.isEmpty() ? null : groups.get(0);
    }

    @Unique
    private static boolean tlsywen$isOnlyGroupOnSecondPage() {
        int modGroupPage = tlsywen$getModGroupPage();
        if (modGroupPage <= 0) return false;

        int count = 0;
        for (ItemGroup group : Registries.ITEM_GROUP) {
            if (group.shouldDisplay() && group instanceof net.fabricmc.fabric.impl.itemgroup.FabricItemGroup fig && fig.getPage() == modGroupPage) {
                count++;
            }
        }
        return count <= 1;
    }

    @Unique
    private static ItemGroup tlsywen$calculateTargetTab() {
        int modGroupPage = tlsywen$getModGroupPage();

        if (tlsywen$isOnlyGroupOnSecondPage()) {
            return tlsywen$findFirstTabOnPage(0, false);
        }
        ItemGroup first = tlsywen$findFirstTabOnPage(modGroupPage, false);
        return first != null ? first : tlsywen$findFirstTabOnPage(0, false);
    }

    @Inject(method = "renderTabIcon", at = @At("HEAD"), cancellable = true)
    private void onRenderTabIcon(DrawContext context, ItemGroup group, CallbackInfo ci) {
        if (!ConfigManager.isEnableModItemGroups() && group == ModItemGroups.the_last_sword_you_will_ever_need) {
            ci.cancel();
        }
    }

    @Inject(method = "isClickInTab", at = @At("HEAD"), cancellable = true)
    private void onIsClickInTab(ItemGroup group, double mouseX, double mouseY, CallbackInfoReturnable<Boolean> cir) {
        if (!ConfigManager.isEnableModItemGroups() && group == ModItemGroups.the_last_sword_you_will_ever_need) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "renderTabTooltipIfHovered", at = @At("HEAD"), cancellable = true)
    private void onRenderTabTooltipIfHovered(DrawContext context, ItemGroup group, int mouseX, int mouseY, CallbackInfoReturnable<Boolean> cir) {
        if (!ConfigManager.isEnableModItemGroups() && group == ModItemGroups.the_last_sword_you_will_ever_need) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        CreativeInventoryScreen screen = (CreativeInventoryScreen) (Object) this;
        ItemGroup selectedGroup = tlsywen$getSelectedTab();

        if (selectedGroup != ModItemGroups.the_last_sword_you_will_ever_need) return;

        int modGroupPage = tlsywen$getModGroupPage();
        ItemGroup firstTab = tlsywen$findFirstTabOnPage(modGroupPage, ConfigManager.isEnableModItemGroups());

        if (firstTab == ModItemGroups.the_last_sword_you_will_ever_need || firstTab == null) {
            firstTab = tlsywen$findFirstTabOnPage(modGroupPage, false);
        }
        if (firstTab == null) {
            firstTab = tlsywen$findFirstTabOnPage(0, false);
        }
        if (firstTab != null) {
            tlsywen$callSetSelectedTab(screen, firstTab);
        }
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        CreativeInventoryScreen screen = (CreativeInventoryScreen) (Object) this;
        MinecraftClient client = MinecraftClient.getInstance();

        if (ConfigManager.isEnableModItemGroups() == tlsywen$lastEnableModItemGroups) return;

        tlsywen$lastEnableModItemGroups = ConfigManager.isEnableModItemGroups();

        if (ConfigManager.isEnableModItemGroups()) {
            ItemGroupPositionManager.restoreOriginalPositions();
        } else {
            ItemGroupPositionManager.redistributePositions(false);
        }

        if (!ConfigManager.isEnableModItemGroups()) {
            ItemGroup selectedGroup = tlsywen$getSelectedTab();
            if (selectedGroup == ModItemGroups.the_last_sword_you_will_ever_need) {
                ItemGroup targetTab = tlsywen$calculateTargetTab();
                if (targetTab != null) {
                    tlsywen$setSelectedTabField(targetTab);
                }
            }
        }

        screen.init(client, screen.width, screen.height);

        if (!ConfigManager.isEnableModItemGroups()) {
            ItemGroup currentGroup = tlsywen$getSelectedTab();
            if (currentGroup != ModItemGroups.the_last_sword_you_will_ever_need) {
                tlsywen$callSetSelectedTab(screen, currentGroup);
            }
        }
    }
}
