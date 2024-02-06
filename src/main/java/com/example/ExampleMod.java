package com.example;

//import com.example.mixin.DemoBlockEntity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	// an instance of our new item
	public static final CustomItem CUSTOM_ITEM =
			Registry.register(Registries.ITEM, new Identifier("tutorial", "custom_item"),
					new CustomItem(new FabricItemSettings().maxCount(16)));

	private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(CUSTOM_ITEM))
			.displayName(Text.translatable("itemGroup.tutorial.test_group"))
			.entries((context, entries) -> {
				entries.add(CUSTOM_ITEM);

			})
			.build();

	public static final Block CHARGEABLE_BLOCK = new ChargeableBlock(FabricBlockSettings.create().strength(4.0f).requiresTool());
	public static final Block EXAMPLE_BLOCK = new ExampleBlock(FabricBlockSettings.create().strength(4.0f).requiresTool());

	public void onInitialize() {
		FuelRegistry.INSTANCE.add(CUSTOM_ITEM, 300);
		CompostingChanceRegistry.INSTANCE.add(CUSTOM_ITEM, 0.5F);

		Registry.register(Registries.ITEM_GROUP, new Identifier("tutorial", "test_group"), ITEM_GROUP);

		Registry.register(Registries.BLOCK, new Identifier("tutorial", "example_block"), EXAMPLE_BLOCK);
		Registry.register(Registries.ITEM, new Identifier("tutorial", "example_block"), new BlockItem(EXAMPLE_BLOCK, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("tutorial", "example_block"), CHARGEABLE_BLOCK);
		Registry.register(Registries.ITEM, new Identifier("tutorial", "example_block"), new BlockItem(CHARGEABLE_BLOCK, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("tutorial", "chargeable_block"), ChargeableBlock.CHARGEABLE_BLOCK);
		Registry.register(Registries.ITEM, new Identifier("tutorial", "chargeable_block"), new BlockItem(ChargeableBlock.CHARGEABLE_BLOCK, new FabricItemSettings()));

	}


}