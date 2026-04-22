package net.tianben.tlsywen;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.tianben.tlsywen.block.ModBlocks;
import net.tianben.tlsywen.item.ModItems;
import net.tianben.tlsywen.item.group.ModItemGroups;
import net.tianben.tlsywen.util.ResourceManager;

import java.util.logging.Logger;

public class TheLastSwordYouWillEverNeed implements ModInitializer {
	public static final String MOD_ID = "tlsywen";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

	public static Identifier ID(String path) {
		return Identifier.of(MOD_ID, path);
	}

    @Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ResourceManager.init();
	}
}