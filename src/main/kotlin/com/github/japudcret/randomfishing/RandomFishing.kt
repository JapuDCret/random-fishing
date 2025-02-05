package com.github.japudcret.randomfishing

import com.github.japudcret.randomfishing.loot.LootTableModifier
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

object RandomFishing : ModInitializer {
    private val logger = LoggerFactory.getLogger("random-fishing")

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hello Fabric world!")

		LootTableModifier.modifyLootTable();
	}
}