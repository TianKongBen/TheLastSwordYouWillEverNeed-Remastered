package net.tianben.tlsywen.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.tianben.tlsywen.config.ConfigManager;
import net.tianben.tlsywen.item.group.ModItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin {

    @Shadow
    private static ItemGroup selectedTab;

    @Shadow
    private void setSelectedTab(ItemGroup group) {
        throw new AssertionError();
    }

    @Unique
    private static boolean tlsywenLastEnableModItemGroups = true;

    @Unique
    private static List<ItemGroup> tlsywenGetFilteredTabs() {
        List<ItemGroup> filtered = new ArrayList<>();
        for (ItemGroup group : ItemGroups.getGroupsToDisplay()) {
            if (group != ModItemGroups.the_last_sword_you_will_ever_need) {
                filtered.add(group);
            }
        }
        return filtered;
    }

    @Redirect(
            method = {
                    "drawBackground",
                    "render",
                    "mouseClicked",
                    "mouseReleased",
                    "getTooltipFromItem"
            },
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemGroups;getGroupsToDisplay()Ljava/util/List;"
            )
    )
    private List<ItemGroup> tlsywenRedirectGetGroupsToDisplay() {
        if (!ConfigManager.isEnableModItemGroups()) {
            return tlsywenGetFilteredTabs();
        }
        return ItemGroups.getGroupsToDisplay();
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void tlsywen$onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        CreativeInventoryScreen screen = (CreativeInventoryScreen) (Object) this;
        MinecraftClient client = MinecraftClient.getInstance();

        if (ConfigManager.isEnableModItemGroups() == tlsywenLastEnableModItemGroups) return;

        tlsywenLastEnableModItemGroups = ConfigManager.isEnableModItemGroups();

        if (!ConfigManager.isEnableModItemGroups()) {
            if (selectedTab == ModItemGroups.the_last_sword_you_will_ever_need) {
                List<ItemGroup> filtered = tlsywenGetFilteredTabs();
                if (!filtered.isEmpty()) {
                    setSelectedTab(filtered.get(0));
                }
            }
        }

        screen.init(client, screen.width, screen.height);
    }
}