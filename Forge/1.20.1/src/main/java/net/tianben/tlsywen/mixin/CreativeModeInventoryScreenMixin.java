package net.tianben.tlsywen.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import net.tianben.tlsywen.config.ModConfig;
import net.tianben.tlsywen.item.group.ModItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeModeInventoryScreenMixin {

    @Unique
    private static Field _1_20_1$selectedTabField;

    @Unique
    private static Method _1_20_1$selectTabMethod;

    @Unique
    private static boolean _1_20_1$lastEnableModItemGroups = true;

    @Unique
    private static Field _1_20_1$getSelectedTabField() {
        if (_1_20_1$selectedTabField == null) {
            try {
                _1_20_1$selectedTabField = CreativeModeInventoryScreen.class.getDeclaredField("selectedTab");
                _1_20_1$selectedTabField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.fillInStackTrace();
            }
        }
        return _1_20_1$selectedTabField;
    }

    @Unique
    private static Method _1_20_1$getSelectTabMethod() {
        if (_1_20_1$selectTabMethod == null) {
            try {
                _1_20_1$selectTabMethod = CreativeModeInventoryScreen.class.getDeclaredMethod("selectTab", CreativeModeTab.class);
                _1_20_1$selectTabMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                e.fillInStackTrace();
            }
        }
        return _1_20_1$selectTabMethod;
    }

    @Unique
    private static CreativeModeTab _1_20_1$getSelectedTab() {
        try {
            Field field = _1_20_1$getSelectedTabField();
            if (field != null) {
                return (CreativeModeTab) field.get(null);
            }
        } catch (IllegalAccessException e) {
            e.fillInStackTrace();
        }
        return null;
    }

    @Unique
    private static void _1_20_1$callSelectTab(CreativeModeInventoryScreen screen, CreativeModeTab tab) {
        try {
            Method method = _1_20_1$getSelectTabMethod();
            if (method != null) {
                method.invoke(screen, tab);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @Unique
    private static CreativeModeTab _1_20_1$findFirstAvailableTab(CreativeModeInventoryScreen screen) {
        var currentPage = screen.getCurrentPage();
        var visibleTabs = currentPage.getVisibleTabs();

        for (CreativeModeTab tab : visibleTabs) {
            if (tab != ModItemGroups.the_last_sword_you_will_ever_need.get()) {
                return tab;
            }
        }

        return null;
    }

    @Inject(
            method = "renderTabButton",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onRenderTabButton(GuiGraphics guiGraphics, CreativeModeTab tab, CallbackInfo ci) {
        ModConfig config = ModConfig.getInstance();

        if (!config.enableModItemGroups &&
                tab == ModItemGroups.the_last_sword_you_will_ever_need.get()) {
            ci.cancel();
        }
    }

    @Inject(
            method = "checkTabClicked",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onCheckTabClicked(CreativeModeTab tab, double mouseX, double mouseY,
                                   CallbackInfoReturnable<Boolean> cir) {
        ModConfig config = ModConfig.getInstance();

        if (!config.enableModItemGroups &&
                tab == ModItemGroups.the_last_sword_you_will_ever_need.get()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "checkTabHovering",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onCheckTabHovering(GuiGraphics guiGraphics, CreativeModeTab tab, int mouseX, int mouseY,
                                    CallbackInfoReturnable<Boolean> cir) {
        ModConfig config = ModConfig.getInstance();

        if (!config.enableModItemGroups &&
                tab == ModItemGroups.the_last_sword_you_will_ever_need.get()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "init",
            at = @At("TAIL")
    )
    private void onInit(CallbackInfo ci) {
        ModConfig config = ModConfig.getInstance();
        CreativeModeInventoryScreen screen = (CreativeModeInventoryScreen) (Object) this;

        if (!config.enableModItemGroups) {
            CreativeModeTab selectedTab = _1_20_1$getSelectedTab();

            if (selectedTab == ModItemGroups.the_last_sword_you_will_ever_need.get()) {
                CreativeModeTab firstAvailableTab = _1_20_1$findFirstAvailableTab(screen);

                if (firstAvailableTab != null) {
                    _1_20_1$callSelectTab(screen, firstAvailableTab);
                }
            }
        }
    }

    @Inject(
            method = "render",
            at = @At("HEAD")
    )
    private void onRender(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        ModConfig config = ModConfig.getInstance();
        CreativeModeInventoryScreen screen = (CreativeModeInventoryScreen) (Object) this;
        Minecraft minecraft = Minecraft.getInstance();

        if (config.enableModItemGroups != _1_20_1$lastEnableModItemGroups) {
            _1_20_1$lastEnableModItemGroups = config.enableModItemGroups;

            screen.init(minecraft, screen.width, screen.height);
        }
    }
}