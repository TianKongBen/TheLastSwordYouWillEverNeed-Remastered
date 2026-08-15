package net.tianben.tlsywen.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.CreativeModeTabRegistry;
import net.tianben.tlsywen.config.ConfigManager;
import net.tianben.tlsywen.item.group.ModItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeModeInventoryScreenMixin {

    @Unique
    private static Field tlsywen$selectedTabField;

    @Unique
    private static Method tlsywen$selectTabMethod;

    @Unique
    private static boolean tlsywen$lastEnableModItemGroups = true;

    @Unique
    private static Field tlsywen$getSelectedTabField() {
        if (tlsywen$selectedTabField == null) {
            try {
                tlsywen$selectedTabField = CreativeModeInventoryScreen.class.getDeclaredField("selectedTab");
                tlsywen$selectedTabField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.fillInStackTrace();
            }
        }
        return tlsywen$selectedTabField;
    }

    @Unique
    private static Method tlsywen$getSelectTabMethod() {
        if (tlsywen$selectTabMethod == null) {
            try {
                tlsywen$selectTabMethod = CreativeModeInventoryScreen.class.getDeclaredMethod("selectTab", CreativeModeTab.class);
                tlsywen$selectTabMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                e.fillInStackTrace();
            }
        }
        return tlsywen$selectTabMethod;
    }

    @Unique
    private static CreativeModeTab tlsywen$getSelectedTab() {
        try {
            Field field = tlsywen$getSelectedTabField();
            if (field != null) return (CreativeModeTab) field.get(null);
        } catch (IllegalAccessException e) {
            e.fillInStackTrace();
        }
        return null;
    }

    @Unique
    private static void tlsywen$setSelectedTabField(CreativeModeTab tab) {
        try {
            Field field = tlsywen$getSelectedTabField();
            if (field != null) field.set(null, tab);
        } catch (IllegalAccessException e) {
            e.fillInStackTrace();
        }
    }

    @Unique
    private static void tlsywen$callSelectTab(CreativeModeInventoryScreen screen, CreativeModeTab tab) {
        try {
            Method method = tlsywen$getSelectTabMethod();
            if (method != null) method.invoke(screen, tab);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @Unique
    private static List<CreativeModeTab> tlsywen$getFilteredTabs() {
        List<CreativeModeTab> filtered = new ArrayList<>();
        for (CreativeModeTab tab : CreativeModeTabRegistry.getSortedCreativeModeTabs()) {
            if (tab != ModItemGroups.the_last_sword_you_will_ever_need.get()) {
                filtered.add(tab);
            }
        }
        return filtered;
    }

    @Redirect(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/common/CreativeModeTabRegistry;getSortedCreativeModeTabs()Ljava/util/List;"
            ),
            remap = false
    )
    private List<CreativeModeTab> redirectGetSortedCreativeModeTabs() {
        if (!ConfigManager.isEnableModItemGroups()) {
            return tlsywen$getFilteredTabs();
        }
        return CreativeModeTabRegistry.getSortedCreativeModeTabs();
    }

    @Inject(
            method = "render",
            at = @At("HEAD")
    )
    private void onRender(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        CreativeModeInventoryScreen screen = (CreativeModeInventoryScreen) (Object) this;
        Minecraft client = Minecraft.getInstance();

        if (ConfigManager.isEnableModItemGroups() == tlsywen$lastEnableModItemGroups) return;

        tlsywen$lastEnableModItemGroups = ConfigManager.isEnableModItemGroups();

        if (!ConfigManager.isEnableModItemGroups()) {
            CreativeModeTab selectedTab = tlsywen$getSelectedTab();
            if (selectedTab == ModItemGroups.the_last_sword_you_will_ever_need.get()) {
                List<CreativeModeTab> filtered = tlsywen$getFilteredTabs();
                if (!filtered.isEmpty()) {
                    tlsywen$setSelectedTabField(filtered.get(0));
                }
            }
        }

        screen.init(client, screen.width, screen.height);

        if (!ConfigManager.isEnableModItemGroups()) {
            CreativeModeTab currentTab = tlsywen$getSelectedTab();
            if (currentTab != ModItemGroups.the_last_sword_you_will_ever_need.get()) {
                tlsywen$callSelectTab(screen, currentTab);
            }
        }
    }
}
