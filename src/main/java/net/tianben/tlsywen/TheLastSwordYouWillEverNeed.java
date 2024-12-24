package net.tianben.tlsywen;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.block.ModBlocks;
import net.tianben.tlsywen.detailab.helper.ConfigHelper;
import net.tianben.tlsywen.detailab.helper.IClientHelper;
import net.tianben.tlsywen.detailab.helper.PlatformHelper;
import net.tianben.tlsywen.item.ModItems;
import net.tianben.tlsywen.item.group.ModItemGroups;
import net.tianben.tlsywen.util.ResourceManager;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheLastSwordYouWillEverNeed implements ModInitializer {
	public static final String MOD_ID = "tlsywen";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static IClientHelper platformHelper;

	public static Identifier ID(String path) {
		return new Identifier(MOD_ID, path);
	}

    @Override
	public void onInitialize() {
		TheLastSwordYouWillEverNeed.init(PlatformHelper.getInstance());

		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ResourceManager.init();

		ConfigHelper.registerClientConfig(() -> ConfigHelper.CLIENT);
	}

	@ApiStatus.Internal
	public static void init(IClientHelper platformHelper) {
		TheLastSwordYouWillEverNeed.platformHelper = platformHelper;
	}

	public static IClientHelper getPlatformHelper() {
		return TheLastSwordYouWillEverNeed.platformHelper;
	}
}