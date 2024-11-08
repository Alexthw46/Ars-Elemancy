package lyrellion.ars_elemancy.datagen;

import lyrellion.ars_elemancy.registry.ModItems;
import com.hollingsworth.arsnouveau.common.crafting.recipes.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeBuilder;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeProvider;
import com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.nio.file.Path;

import static alexthw.ars_elemental.registry.ModItems.MARK_OF_MASTERY;

public class AEApparatusProvider extends ApparatusRecipeProvider {

    public AEApparatusProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void collectJsons(CachedOutput cache) {


        recipes.add(builder()
                .withResult(ModItems.ENCHANTER_BANGLE.get())
                .withReagent(ItemsRegistry.RING_OF_POTENTIAL)
                .withPedestalItem(RecipeDatagen.SOURCE_GEM_BLOCK)
                .withPedestalItem(Items.GOLD_BLOCK)
                .withPedestalItem(Items.GOLD_BLOCK)
                .withPedestalItem(Items.END_CRYSTAL)
                .build()
        );


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
        recipes.add(builder()
                .withResult(ModItems.CINDER_ARMOR.getChest())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR.getChest())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getChest())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.CINDER_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.CINDER_ARMOR.getHat())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR.getHat())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getHat())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.CINDER_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.CINDER_ARMOR.getBoots())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR.getBoots())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getBoots())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.CINDER_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.CINDER_ARMOR.getLegs())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR.getLegs())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getLegs())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.CINDER_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );

        recipes.add(builder()
                .withResult(ModItems.MIRE_ARMOR.getChest())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR.getChest())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR.getChest())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.MIRE_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.MIRE_ARMOR.getHat())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR.getHat())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR.getHat())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.MIRE_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.MIRE_ARMOR.getBoots())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR.getBoots())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR.getBoots())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.MIRE_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.MIRE_ARMOR.getLegs())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR.getLegs())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR.getLegs())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.MIRE_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );

        recipes.add(builder()
                .withResult(ModItems.SILT_ARMOR.getChest())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR.getChest())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getChest())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.SILT_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.SILT_ARMOR.getHat())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR.getHat())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR.getHat())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.SILT_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.SILT_ARMOR.getBoots())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR.getBoots())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR.getBoots())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.SILT_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.SILT_ARMOR.getLegs())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR.getLegs())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR.getLegs())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.SILT_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );

        recipes.add(builder()
                .withResult(ModItems.TEMPEST_ARMOR.getChest())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR.getChest())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR.getChest())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.TEMPEST_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.TEMPEST_ARMOR.getHat())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR.getHat())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR.getHat())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.TEMPEST_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.TEMPEST_ARMOR.getBoots())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR.getBoots())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR.getBoots())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.TEMPEST_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.TEMPEST_ARMOR.getLegs())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.AIR_ARMOR.getLegs())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR.getLegs())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.TEMPEST_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );

        recipes.add(builder()
                .withResult(ModItems.LAVA_ARMOR.getChest())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR.getChest())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getChest())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.LAVA_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.LAVA_ARMOR.getHat())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR.getHat())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getHat())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.LAVA_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.LAVA_ARMOR.getBoots())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR.getBoots())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getBoots())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.LAVA_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.LAVA_ARMOR.getLegs())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.EARTH_ARMOR.getLegs())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getLegs())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.LAVA_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );

        recipes.add(builder()
                .withResult(ModItems.VAPOR_ARMOR.getChest())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR.getChest())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getChest())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.VAPOR_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.VAPOR_ARMOR.getHat())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR.getHat())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getHat())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.VAPOR_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.VAPOR_ARMOR.getBoots())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR.getBoots())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getBoots())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.VAPOR_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.VAPOR_ARMOR.getLegs())
                .withReagent(MARK_OF_MASTERY)
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.WATER_ARMOR.getLegs())
                .withPedestalItem(alexthw.ars_elemental.registry.ModItems.FIRE_ARMOR.getLegs())
                .withPedestalItem(2,(ItemLike) lyrellion.ars_elemancy.registry.ModItems.VAPOR_ESSENCE)
                .withSourceCost(7000)
                .keepNbtOfReagent(true)
                .build()
        );


    //    addArmorRecipes(ModItems.TEMPEST_ARMOR, (ItemLike) ModItems.TEMPEST_ESSENCE);
    //    addArmorRecipes(ModItems.CINDER_ARMOR, (ItemLike) ModItems.CINDER_ESSENCE);
    //    addArmorRecipes(ModItems.MIRE_ARMOR, (ItemLike) ModItems.MIRE_ESSENCE);
    //    addArmorRecipes(ModItems.SILT_ARMOR, (ItemLike) ModItems.SILT_ESSENCE);
    //    addArmorRecipes(ModItems.LAVA_ARMOR, (ItemLike) ModItems.LAVA_ESSENCE);
    //    addArmorRecipes(ModItems.VAPOR_ARMOR, (ItemLike) ModItems.VAPOR_ESSENCE);

        Path output = this.generator.getPackOutput().getOutputFolder();
        for (ApparatusRecipeBuilder.RecipeWrapper<? extends EnchantingApparatusRecipe> g : recipes) {
            if (g != null) {
                Path path = getRecipePath(output, g.id().getPath());
                saveStable(cache, g.serialize(), path);
            }
        }

    }

    //protected void addArmorRecipes(ArmorSet armorSet, ItemLike essence) {

    //    recipes.add(Abuilder().withResult(armorSet.getHat()).withReagent(Ingredient.of(AETagsProvider.AEItemTagsProvider.MAGIC_HOOD)).withPedestalItem(MARK_OF_MASTERY.get()).withPedestalItem(Items.NETHERITE_INGOT).withPedestalItem(2, essence).withSourceCost(7000).keepNbtOfReagent(true).build());
    //    recipes.add(Abuilder().withResult(armorSet.getChest()).withReagent(Ingredient.of(AETagsProvider.AEItemTagsProvider.MAGIC_ROBE)).withPedestalItem(MARK_OF_MASTERY.get()).withPedestalItem(Items.NETHERITE_INGOT).withPedestalItem(2, essence).withSourceCost(7000).keepNbtOfReagent(true).build());
    //    recipes.add(Abuilder().withResult(armorSet.getLegs()).withReagent(Ingredient.of(AETagsProvider.AEItemTagsProvider.MAGIC_LEG)).withPedestalItem(MARK_OF_MASTERY.get()).withPedestalItem(Items.NETHERITE_INGOT).withPedestalItem(2, essence).withSourceCost(7000).keepNbtOfReagent(true).build());
    //    recipes.add(Abuilder().withResult(armorSet.getBoots()).withReagent(Ingredient.of(AETagsProvider.AEItemTagsProvider.MAGIC_BOOT)).withPedestalItem(MARK_OF_MASTERY.get()).withPedestalItem(Items.NETHERITE_INGOT).withPedestalItem(2, essence).withSourceCost(7000).keepNbtOfReagent(true).build());

    // }

    protected static Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/ars_elemancy/recipe/" + str + ".json");
    }

    @Override
    public String getName() {
        return "Ars Elemental Apparatus";
    }

    // ArmorBuilder Abuilder() {
    //    return new ArmorBuilder();
    // }

    //public static class ArmorBuilder extends ApparatusRecipeBuilder {

    //     @Override
    //    public RecipeWrapper<EnchantingApparatusRecipe> build() {
    //        var wrapper = super.build();
    //        return new RecipeWrapper<>(wrapper.id(), new Recipe(wrapper.recipe().reagent(), wrapper.recipe().result(), wrapper.recipe().pedestalItems(), wrapper.recipe().sourceCost()), Recipe.CODEC);
    //    }
    //}

}
