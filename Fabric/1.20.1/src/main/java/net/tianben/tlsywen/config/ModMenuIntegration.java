package net.tianben.tlsywen.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.loader.api.FabricLoader;

public final class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config")) {
            return parent -> AutoConfig.getConfigScreen(ModConfig.class, parent).get();
        }
        return null;
    }
}
