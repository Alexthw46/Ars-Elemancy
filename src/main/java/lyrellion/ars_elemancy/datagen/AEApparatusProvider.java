package lyrellion.ars_elemancy.datagen;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import lyrellion.ars_elemancy.ArsElemancy;
import lyrellion.ars_elemancy.common.items.armor.ArmorSet;
import lyrellion.ars_elemancy.recipe.ElemancyArmorRecipe;
import lyrellion.ars_elemancy.registry.ModItems;
import com.hollingsworth.arsnouveau.common.crafting.recipes.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeBuilder;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static alexthw.ars_elemental.registry.ModItems.MARK_OF_MASTERY;

public class AEApparatusProvider extends ApparatusRecipeProvider {

    public AEApparatusProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void collectJsons(CachedOutput cache) {
        // Bangles
        addCombinedRecipes(ModItems.TEMPEST_BANGLE, ModItems.TEMPEST_ESSENCE, 7000, List.of(alexthw.ars_elemental.registry.ModItems.AIR_BANGLE, alexthw.ars_elemental.registry.ModItems.WATER_BANGLE));
        addCombinedRecipes(ModItems.CINDER_BANGLE, ModItems.CINDER_ESSENCE, 7000, List.of(alexthw.ars_elemental.registry.ModItems.AIR_BANGLE, alexthw.ars_elemental.registry.ModItems.FIRE_BANGLE));
        addCombinedRecipes(ModItems.SILT_BANGLE, ModItems.SILT_ESSENCE, 7000, List.of(alexthw.ars_elemental.registry.ModItems.AIR_BANGLE, alexthw.ars_elemental.registry.ModItems.EARTH_BANGLE));
        addCombinedRecipes(ModItems.MIRE_BANGLE, ModItems.MIRE_ESSENCE, 7000, List.of(alexthw.ars_elemental.registry.ModItems.EARTH_BANGLE, alexthw.ars_elemental.registry.ModItems.WATER_BANGLE));
        addCombinedRecipes(ModItems.VAPOR_BANGLE, ModItems.VAPOR_ESSENCE, 7000, List.of(alexthw.ars_elemental.registry.ModItems.FIRE_BANGLE, alexthw.ars_elemental.registry.ModItems.WATER_BANGLE));
        addCombinedRecipes(ModItems.LAVA_BANGLE, ModItems.LAVA_ESSENCE, 7000, List.of(alexthw.ars_elemental.registry.ModItems.FIRE_BANGLE, alexthw.ars_elemental.registry.ModItems.EARTH_BANGLE));
        addCombinedRecipes(ModItems.ELEMANCER_BANGLE, ModItems.ELEMANCER_ESSENCE, 7000, List.of(ModItems.TEMPEST_BANGLE, ModItems.LAVA_BANGLE));
        addCombinedRecipes(ModItems.ELEMANCER_BANGLE, ModItems.ELEMANCER_ESSENCE, 7000, List.of(ModItems.CINDER_BANGLE, ModItems.MIRE_BANGLE));
        addCombinedRecipes(ModItems.ELEMANCER_BANGLE, ModItems.ELEMANCER_ESSENCE, 7000, List.of(ModItems.SILT_BANGLE, ModItems.VAPOR_BANGLE));

        //focus upgrade

        recipes.add(builder()
                .withResult(ModItems.CINDER_FOCUS.get())
                .withReagent((ItemLike) ModItems.CINDER_ESSENCE)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.FIRE_FOCUS)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.AIR_FOCUS)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.LAVA_FOCUS.get())
                .withReagent((ItemLike) ModItems.LAVA_ESSENCE)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.FIRE_FOCUS)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.EARTH_FOCUS)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.VAPOR_FOCUS.get())
                .withReagent((ItemLike) ModItems.VAPOR_ESSENCE)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.FIRE_FOCUS)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.WATER_FOCUS)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.SILT_FOCUS.get())
                .withReagent((ItemLike) ModItems.SILT_ESSENCE)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.EARTH_FOCUS)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.AIR_FOCUS)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.MIRE_FOCUS.get())
                .withReagent((ItemLike) ModItems.MIRE_ESSENCE)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.EARTH_FOCUS)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.WATER_FOCUS)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.TEMPEST_FOCUS.get())
                .withReagent((ItemLike) ModItems.TEMPEST_ESSENCE)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.WATER_FOCUS)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.AIR_FOCUS)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.ELEMANCER_FOCUS.get())
                .withReagent((ItemLike) ModItems.ELEMANCER_ESSENCE)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.FIRE_FOCUS)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.AIR_FOCUS)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.EARTH_FOCUS)
                .withPedestalItem((ItemLike) alexthw.ars_elemental.registry.ModItems.WATER_FOCUS)
                .build()
        );

        // Elemancy armors
        addArmorRecipes(ModItems.TEMPEST_ARMOR, ModItems.TEMPEST_ESSENCE, List.of(armorSet(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR), armorSet(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR)));
        addArmorRecipes(ModItems.CINDER_ARMOR, ModItems.CINDER_ESSENCE, List.of(armorSet(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR), armorSet(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR)));
        addArmorRecipes(ModItems.SILT_ARMOR, ModItems.SILT_ESSENCE, List.of(armorSet(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR), armorSet(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR)));
        addArmorRecipes(ModItems.MIRE_ARMOR, ModItems.MIRE_ESSENCE, List.of(armorSet(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR), armorSet(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR)));
        addArmorRecipes(ModItems.VAPOR_ARMOR, ModItems.VAPOR_ESSENCE, List.of(armorSet(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR), armorSet(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR)));
        addArmorRecipes(ModItems.LAVA_ARMOR, ModItems.LAVA_ESSENCE, List.of(armorSet(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR), armorSet(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR)));
        addArmorRecipes(ModItems.ELEMANCER_ARMOR, ModItems.ELEMANCER_ESSENCE, List.of(armorSet(ModItems.TEMPEST_ARMOR), armorSet(ModItems.LAVA_ARMOR)));
        addArmorRecipes(ModItems.ELEMANCER_ARMOR, ModItems.ELEMANCER_ESSENCE, List.of(armorSet(ModItems.CINDER_ARMOR), armorSet(ModItems.MIRE_ARMOR)));
        addArmorRecipes(ModItems.ELEMANCER_ARMOR, ModItems.ELEMANCER_ESSENCE, List.of(armorSet(ModItems.SILT_ARMOR), armorSet(ModItems.VAPOR_ARMOR)));


        Path output = this.generator.getPackOutput().getOutputFolder();
        for (ApparatusRecipeBuilder.RecipeWrapper<? extends EnchantingApparatusRecipe> g : recipes) {
            if (g != null) {
                Path path = getRecipePath(output, g.id().getPath());
                saveStable(cache, g.serialize(), path);
            }
        }

    }

    public record ArmorSetData(Item hat, Item chest, Item legs, Item boots) {
    }

    ArmorSetData armorSet(alexthw.ars_elemental.common.items.armor.ArmorSet set) {
        return new ArmorSetData(set.getHat(), set.getChest(), set.getLegs(), set.getBoots());
    }

    ArmorSetData armorSet(ArmorSet set) {
        return new ArmorSetData(set.getHat(), set.getChest(), set.getLegs(), set.getBoots());
    }

    static Object2IntArrayMap<Item> OUTPUT_COUNTER = new Object2IntArrayMap<>();

    protected void addArmorRecipes(ArmorSet armorSet, DeferredHolder<Item, ?> essenceHolder, List<ArmorSetData> bases) {
        if (bases.size() < 2) {
            throw new IllegalArgumentException("needs at least 2 armor bases");
        }

        var essence = essenceHolder.get();
        ArrayList<Item>[] pieceTypes = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            pieceTypes[i] = new ArrayList<>();
        }

        pieceTypes[0].add(armorSet.getHat());
        pieceTypes[1].add(armorSet.getChest());
        pieceTypes[2].add(armorSet.getLegs());
        pieceTypes[3].add(armorSet.getBoots());

        for (var base : bases) {
            pieceTypes[0].add(base.hat);
            pieceTypes[1].add(base.chest);
            pieceTypes[2].add(base.legs);
            pieceTypes[3].add(base.boots);
        }

        for (int i = 0; i < 4; i++) {
            List<Item> pieces = pieceTypes[i];
            Item piece = pieces.getFirst();
            for (int j = 1; j < pieces.size(); j++) {
                var reagent = pieces.get(j);

                var builder = Abuilder()
                        .withResult(piece)
                        .withReagent(reagent)
                        .withPedestalItem(MARK_OF_MASTERY)
                        .withPedestalItem(2, essence)
                        .withSourceCost(7000)
                        .keepNbtOfReagent(true)
                        .withId(ArsElemancy.prefix(BuiltInRegistries.ITEM.getKey(piece).getPath() + "_" + OUTPUT_COUNTER.compute(piece, (k, v) -> v == null ? 1 : v + 1)));

                for (int k = 1; k < pieces.size(); k++) {
                    if (j != k) {
                        builder = builder.withPedestalItem(pieces.get(k));
                    }
                }

                recipes.add(builder.build());
            }
        }
    }

    protected void addCombinedRecipes(DeferredHolder<Item, ?> resultHolder, DeferredHolder<Item, ?> essenceHolder, int source, List<DeferredHolder<Item, ?>> bases) {
        if (bases.size() < 2) {
            throw new IllegalArgumentException("needs at least 2 bases");
        }

        var result = resultHolder.get();
        var essence = essenceHolder.get();
        for (int j = 0; j < bases.size(); j++) {
            var reagent = bases.get(j).get();

            var builder = Abuilder()
                    .withResult(result)
                    .withReagent(reagent)
                    .withPedestalItem(MARK_OF_MASTERY)
                    .withPedestalItem(2, essence)
                    .withSourceCost(source)
                    .keepNbtOfReagent(true)
                    .withId(ArsElemancy.prefix(BuiltInRegistries.ITEM.getKey(result).getPath() + "_" + OUTPUT_COUNTER.compute(result, (k, v) -> v == null ? 1 : v + 1)));

            for (int k = 0; k < bases.size(); k++) {
                if (j != k) {
                    builder = builder.withPedestalItem(bases.get(k).get());
                }
            }

            recipes.add(builder.build());
        }
    }

    protected static Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/ars_elemancy/recipe/" + str + ".json");
    }

    @Override
    public String getName() {
        return "Ars Elemancy Apparatus";
    }

    ArmorBuilder Abuilder() {
        return new ArmorBuilder();
    }

    public static class ArmorBuilder extends ApparatusRecipeBuilder {

        @Override
        public RecipeWrapper<EnchantingApparatusRecipe> build() {
            var wrapper = super.build();
            return new RecipeWrapper<>(wrapper.id(), new ElemancyArmorRecipe(wrapper.recipe().reagent(), wrapper.recipe().result(), wrapper.recipe().pedestalItems(), wrapper.recipe().sourceCost()), ElemancyArmorRecipe.CODEC);
        }
    }

}
