package com.github.japudcret.randomfishing

import com.github.japudcret.randomfishing.loot.LootTableModifier
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

object RandomFishing : ModInitializer {
    private val logger = LoggerFactory.getLogger("random-fishing")

	override fun onInitialize() {
		logger.info("Initializing Fishing loot tables")

		LootTableModifier.modifyLootTable();
	}
}