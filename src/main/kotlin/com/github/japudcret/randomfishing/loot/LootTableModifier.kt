package com.github.japudcret.randomfishing.loot

import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder
import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.minecraft.item.Item
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootPoolEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

class LootTableModifier {

    companion object {
        private val logger = LoggerFactory.getLogger("random-fishing")

        private val fishingLootTableIds: Set<Identifier> = setOf(
            Identifier.of("minecraft", "gameplay/fishing"),
        )

        fun modifyLootTable() {
            LootTableEvents.REPLACE.register { key, original, source, _ ->
                if (key.value in fishingLootTableIds) {
                    val entries: MutableMap<RegistryKey<Item>, LootPoolEntry> = HashMap()

                    for (itemKey in Registries.ITEM.keys) {
                        val item = Registries.ITEM.get(itemKey)
                        entries[itemKey] = ItemEntry.builder(item).build()
                    }

                    val lootPool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .conditionally(RandomChanceLootCondition.builder(1f)) // Drops 100% of the time

                    entries.forEach { entry ->
                        val item = Registries.ITEM.get(entry.key) ?: error("No item found for key ${entry.key}")

                        lootPool
                            .with(entry.value)
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, item.defaultStack.maxCount.toFloat())).build())
                    }

                    val tableBuilder = LootTable.builder()

                    tableBuilder.pool(lootPool.build())

                    logger.info("Registered ${entries.size} entries for fishing")

                    return@register tableBuilder.build()

                    /*
                    // do something dirty -- TODO: does not work in live
                    LootTable::class.java.getDeclaredField("pools").let {
                        it.isAccessible = true

                        it.set(original, mutableListOf(lootPool.build()))
                    }
                    */
                }

                return@register null
            }
        }
    }
}