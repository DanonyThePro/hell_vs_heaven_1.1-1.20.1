package net.DanonyThePro.hell_vs_heaven.worldgen;

import net.DanonyThePro.hell_vs_heaven.Hell_vs_Heaven;
import net.DanonyThePro.hell_vs_heaven.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> HOLY_PLACED_KEY = createKey("holy_placed");
    public static final ResourceKey<PlacedFeature> CHARCOAL_PLACED_KEY = createKey("charcoal_placed");

    public static final ResourceKey<PlacedFeature> RUBY_PLACED_KEY = createKey("ruby_placed");
    public static final ResourceKey<PlacedFeature> SAPPHIRE_PLACED_KEY = createKey("sapphire_placed");
    public static final ResourceKey<PlacedFeature> END_ENDERITE_PLACED_KEY = createKey("end_enderite_placed");
    public static final ResourceKey<PlacedFeature> NETHER_FIRE_STEEL_PLACED_KEY = createKey("nether_fire_steel_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, HOLY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HOLY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3,0.1f,2), ModBlocks.HOLY_SAPLING.get()));
        register(context, CHARCOAL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CHARCOAL_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3,0.1f,2), ModBlocks.CHARCOAL_SAPLING.get()));

        register(context, RUBY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_RUBY_ORE_KEY),
                ModOrePlacement.commonOrePlacement(2,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, SAPPHIRE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SAPPHIRE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(2,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, END_ENDERITE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_ENDERITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, NETHER_FIRE_STEEL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_FIRE_STEEL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Hell_vs_Heaven.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier...modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
