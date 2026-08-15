package net.tianben.tlsywen.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.common.CreativeModeTabRegistry;
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
                tlsywen$selectTabMethod = CreativeModeInventoryScreen.class
                        .getDeclaredMethod("selectTab", CreativeModeTab.class);
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
            var field = tlsywen$getSelectedTabField();
            if (field != null) {
                return (CreativeModeTab) field.get(null);
            }
        } catch (IllegalAccessException e) {
            e.fillInStackTrace();
        }
        return null;
    }

    @Unique
    private static void tlsywen$setSelectedTabField(CreativeModeTab tab) {
        try {
            var field = tlsywen$getSelectedTabField();
            if (field != null) {
                field.set(null, tab);
            }
        } catch (IllegalAccessException e) {
            e.fillInStackTrace();
        }
    }

    @Unique
    private static void tlsywen$callSelectTab(CreativeModeInventoryScreen screen, CreativeModeTab tab) {
        try {
            var method = tlsywen$getSelectTabMethod();
            if (method != null) {
                method.invoke(screen, tab);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    @Unique
    private static List<CreativeModeTab> tlsywen$getFilteredTabs() {
        var own = ModItemGroups.the_last_sword_you_will_ever_need.get();
        return CreativeModeTabRegistry.getSortedCreativeModeTabs().stream()
                .filter(tab -> tab != own)
                .toList();
    }

    @Redirect(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/neoforged/neoforge/common/CreativeModeTabRegistry;getSortedCreativeModeTabs()Ljava/util/List;"
            ),
            remap = false
    )
    private List<CreativeModeTab> redirectGetSortedCreativeModeTabs() {
        return ConfigManager.isEnableModItemGroups()
                ? CreativeModeTabRegistry.getSortedCreativeModeTabs()
                : tlsywen$getFilteredTabs();
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void tlsywen$onRender(GuiGraphics guiGraphics, int mouseX, int mouseY,
                                  float partialTick, CallbackInfo ci) {
        boolean enableModGroups = ConfigManager.isEnableModItemGroups();

        if (enableModGroups == tlsywen$lastEnableModItemGroups) {
            return;
        }
        tlsywen$lastEnableModItemGroups = enableModGroups;

        var screen = (CreativeModeInventoryScreen) (Object) this;
        var client = Minecraft.getInstance();
        var ownTab = ModItemGroups.the_last_sword_you_will_ever_need.get();

        if (!enableModGroups) {
            if (tlsywen$getSelectedTab() == ownTab) {
                var filtered = tlsywen$getFilteredTabs();
                if (!filtered.isEmpty()) {
                    tlsywen$setSelectedTabField(filtered.get(0));
                }
            }
        }

        screen.init(client, screen.width, screen.height);

        if (!enableModGroups) {
            var currentTab = tlsywen$getSelectedTab();
            if (currentTab != ownTab) {
                tlsywen$callSelectTab(screen, currentTab);
            }
        }
    }
}