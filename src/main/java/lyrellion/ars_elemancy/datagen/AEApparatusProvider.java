package lyrellion.ars_elemancy.datagen;

import lyrellion.ars_elemancy.common.items.armor.ArmorSet;
import lyrellion.ars_elemancy.common.items.armor.ShockPerk;
import lyrellion.ars_elemancy.common.items.armor.SporePerk;
import lyrellion.ars_elemancy.common.items.armor.SummonPerk;
import lyrellion.ars_elemancy.recipe.ElemancyArmorRecipe;
import lyrellion.ars_elemancy.registry.ModItems;
import lyrellion.ars_elemancy.registry.ModRegistry;
import com.hollingsworth.arsnouveau.common.crafting.recipes.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeBuilder;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeProvider;
import com.hollingsworth.arsnouveau.common.datagen.ItemTagProvider;
import com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen;
import com.hollingsworth.arsnouveau.setup.registry.BlockRegistry;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.nio.file.Path;

public class AEApparatusProvider extends ApparatusRecipeProvider {

    public AEApparatusProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void collectJsons(CachedOutput cache) {

        recipes.add(builder()
                .withResult(getPerkItem(ShockPerk.INSTANCE.getRegistryName()))
                .withReagent(ItemsRegistry.BLANK_THREAD)
                .withPedestalItem(2, ItemsRegistry.AIR_ESSENCE)
                .withPedestalItem(1, Items.LIGHTNING_ROD)
                .withPedestalItem(1, ModItems.FLASHING_POD.get())
                .build()
        );

        recipes.add(builder()
                .withResult(getPerkItem(SporePerk.INSTANCE.getRegistryName()))
                .withReagent(ItemsRegistry.BLANK_THREAD)
                .withPedestalItem(2, ItemsRegistry.EARTH_ESSENCE)
                .withPedestalItem(1, Items.SPORE_BLOSSOM)
                .withPedestalItem(1, Items.SPIDER_EYE)
                .build()
        );

        recipes.add(builder()
                .withResult(getPerkItem(SummonPerk.INSTANCE.getRegistryName()))
                .withReagent(ItemsRegistry.BLANK_THREAD)
                .withPedestalItem(2, ItemsRegistry.CONJURATION_ESSENCE)
                .withPedestalItem(1, Items.ECHO_SHARD)
                .withPedestalItem(2, Ingredient.of(ItemTagProvider.WILDEN_DROP_TAG))
                .build()
        );

        recipes.add(builder()
                .withResult(ModItems.NECRO_FOCUS.get())
                .withReagent(ItemsRegistry.SUMMONING_FOCUS)
                .withPedestalItem(2, Items.WITHER_ROSE)
                .withPedestalItem(1, Items.WITHER_SKELETON_SKULL)
                .withPedestalItem(1, ModItems.ANIMA_ESSENCE.get())
                .build()
        );

        recipes.add(builder()
                .withResult(ModItems.SPELL_HORN.get())
                .withReagent(ItemsRegistry.WILDEN_HORN)
                .withPedestalItem(ItemsRegistry.AIR_ESSENCE)
                .withPedestalItem(3, Items.GOLD_INGOT)
                .withPedestalItem(4, RecipeDatagen.SOURCE_GEM)
                .build()
        );

        recipes.add(builder()
                .withResult(ModItems.ENCHANTER_BANGLE.get())
                .withReagent(ItemsRegistry.RING_OF_POTENTIAL)
                .withPedestalItem(RecipeDatagen.SOURCE_GEM_BLOCK)
                .withPedestalItem(Items.GOLD_BLOCK)
                .withPedestalItem(Items.GOLD_BLOCK)
                .withPedestalItem(Items.END_CRYSTAL)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.AIR_BANGLE.get())
                .withReagent(ModItems.ENCHANTER_BANGLE.get())
                .withPedestalItem(ItemsRegistry.AIR_ESSENCE)
                .withPedestalItem(ItemsRegistry.AIR_ESSENCE)
                .withPedestalItem(Items.PISTON)
                .withPedestalItem(ItemsRegistry.AIR_ESSENCE)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.FIRE_BANGLE.get())
                .withReagent(ModItems.ENCHANTER_BANGLE.get())
                .withPedestalItem(ItemsRegistry.FIRE_ESSENCE)
                .withPedestalItem(ItemsRegistry.FIRE_ESSENCE)
                .withPedestalItem(Items.FIRE_CHARGE)
                .withPedestalItem(ItemsRegistry.FIRE_ESSENCE)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.EARTH_BANGLE.get())
                .withReagent(ModItems.ENCHANTER_BANGLE.get())
                .withPedestalItem(ItemsRegistry.EARTH_ESSENCE)
                .withPedestalItem(ItemsRegistry.EARTH_ESSENCE)
                .withPedestalItem(Items.COBWEB)
                .withPedestalItem(ItemsRegistry.EARTH_ESSENCE)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.WATER_BANGLE.get())
                .withReagent(ModItems.ENCHANTER_BANGLE.get())
                .withPedestalItem(ItemsRegistry.WATER_ESSENCE)
                .withPedestalItem(ItemsRegistry.WATER_ESSENCE)
                .withPedestalItem(Items.POWDER_SNOW_BUCKET)
                .withPedestalItem(ItemsRegistry.WATER_ESSENCE)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.SUMMON_BANGLE.get())
                .withReagent(ModItems.ENCHANTER_BANGLE.get())
                .withPedestalItem(ItemsRegistry.CONJURATION_ESSENCE)
                .withPedestalItem(ItemsRegistry.CONJURATION_ESSENCE)
                .withPedestalItem(Items.BONE)
                .withPedestalItem(ItemsRegistry.WILDEN_HORN)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.ANIMA_BANGLE.get())
                .withReagent(ModItems.ENCHANTER_BANGLE.get())
                .withPedestalItem(ModItems.ANIMA_ESSENCE.get())
                .withPedestalItem(ModItems.ANIMA_ESSENCE.get())
                .withPedestalItem(Items.GHAST_TEAR)
                .withPedestalItem(Items.WITHER_ROSE)
                .build()
        );

        //focus upgrade

        recipes.add(builder()
                .withResult(ModItems.FIRE_FOCUS.get())
                .withReagent(ModItems.LESSER_FIRE_FOCUS.get())
                .withPedestalItem(ModItems.MARK_OF_MASTERY.get())
                .withSourceCost(5000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.AIR_FOCUS.get())
                .withReagent(ModItems.LESSER_AIR_FOCUS.get())
                .withPedestalItem(ModItems.MARK_OF_MASTERY.get())
                .withSourceCost(5000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.EARTH_FOCUS.get())
                .withReagent(ModItems.LESSER_EARTH_FOCUS.get())
                .withPedestalItem(ModItems.MARK_OF_MASTERY.get())
                .withSourceCost(5000)
                .keepNbtOfReagent(true)
                .build()
        );
        recipes.add(builder()
                .withResult(ModItems.WATER_FOCUS.get())
                .withReagent(ModItems.LESSER_WATER_FOCUS.get())
                .withPedestalItem(ModItems.MARK_OF_MASTERY.get())
                .withSourceCost(5000)
                .keepNbtOfReagent(true)
                .build()
        );

        //mirror shield enchant
        recipes.add(builder()
                .withPedestalItem(BlockRegistry.SPELL_PRISM)
                .withPedestalItem(ItemsRegistry.MANIPULATION_ESSENCE)
                .withPedestalItem(ItemsRegistry.ABJURATION_ESSENCE)
                .buildEnchantmentRecipe(ModRegistry.MIRROR, 1, 2000));


        recipes.add(builder()
                .withPedestalItem(ItemsRegistry.ENCHANTERS_MIRROR)
                .withPedestalItem(Items.TOTEM_OF_UNDYING)
                .withPedestalItem(RecipeDatagen.SOURCE_GEM_BLOCK)
                .buildEnchantmentRecipe(ModRegistry.MIRROR, 3, 8000));

        recipes.add(builder()
                .withPedestalItem(ModItems.ANIMA_ESSENCE.get())
                .withPedestalItem(Items.TOTEM_OF_UNDYING)
                .withPedestalItem(Blocks.LAPIS_BLOCK)
                .withPedestalItem(RecipeDatagen.SOURCE_GEM_BLOCK)
                .buildEnchantmentRecipe(ModRegistry.SOULBOUND, 1, 10000));

        addArmorRecipes(ModItems.TEMPEST_ARMOR, (ItemLike) ModItems.TEMPEST_ESSENCE);
        addArmorRecipes(ModItems.CINDER_ARMOR, (ItemLike) ModItems.CINDER_ESSENCE);
        addArmorRecipes(ModItems.MIRE_ARMOR, (ItemLike) ModItems.MIRE_ESSENCE);
        addArmorRecipes(ModItems.SILT_ARMOR, (ItemLike) ModItems.SILT_ESSENCE);
        addArmorRecipes(ModItems.LAVA_ARMOR, (ItemLike) ModItems.LAVA_ESSENCE);
        addArmorRecipes(ModItems.VAPOR_ARMOR, (ItemLike) ModItems.VAPOR_ESSENCE);


        recipes.add(builder()
                .withResult(new ItemStack(ModItems.MARK_OF_MASTERY.get(), 5))
                .withSourceCost(10000)
                .withReagent(ItemsRegistry.WILDEN_TRIBUTE)
                .withPedestalItem(ItemsRegistry.EARTH_ESSENCE)
                .withPedestalItem(ItemsRegistry.FIRE_ESSENCE)
                .withPedestalItem(ItemsRegistry.WATER_ESSENCE)
                .withPedestalItem(ItemsRegistry.AIR_ESSENCE)
                .withPedestalItem(ItemsRegistry.ABJURATION_ESSENCE)
                .withPedestalItem(ItemsRegistry.CONJURATION_ESSENCE)
                .withPedestalItem(ItemsRegistry.MANIPULATION_ESSENCE)
                .withPedestalItem(ModItems.ANIMA_ESSENCE.get())
                .build()
        );

        recipes.add(builder()
                .withResult(ModItems.CASTER_BAG.get())
                .withReagent(ModItems.CURIO_BAG.get())
                .withPedestalItem(ItemsRegistry.MANIPULATION_ESSENCE)
                .withPedestalItem(ItemsRegistry.MANIPULATION_ESSENCE)
                .withPedestalItem(Items.BLAZE_POWDER)
                .withPedestalItem(Items.BLAZE_POWDER)
                .withPedestalItem(Items.GOLD_BLOCK)
                .withPedestalItem(Items.GOLD_BLOCK)
                .build());

        Path output = this.generator.getPackOutput().getOutputFolder();
        for (ApparatusRecipeBuilder.RecipeWrapper<? extends EnchantingApparatusRecipe> g : recipes) {
            if (g != null) {
                Path path = getRecipePath(output, g.id().getPath());
                saveStable(cache, g.serialize(), path);
            }
        }

    }

    protected void addArmorRecipes(ArmorSet armorSet, ItemLike essence) {

        recipes.add(Abuilder().withResult(armorSet.getHat()).withReagent(Ingredient.of(AETagsProvider.AEItemTagsProvider.MAGIC_HOOD)).withPedestalItem(ModItems.MARK_OF_MASTERY.get()).withPedestalItem(Items.NETHERITE_INGOT).withPedestalItem(2, essence).withSourceCost(7000).keepNbtOfReagent(true).build());
        recipes.add(Abuilder().withResult(armorSet.getChest()).withReagent(Ingredient.of(AETagsProvider.AEItemTagsProvider.MAGIC_ROBE)).withPedestalItem(ModItems.MARK_OF_MASTERY.get()).withPedestalItem(Items.NETHERITE_INGOT).withPedestalItem(2, essence).withSourceCost(7000).keepNbtOfReagent(true).build());
        recipes.add(Abuilder().withResult(armorSet.getLegs()).withReagent(Ingredient.of(AETagsProvider.AEItemTagsProvider.MAGIC_LEG)).withPedestalItem(ModItems.MARK_OF_MASTERY.get()).withPedestalItem(Items.NETHERITE_INGOT).withPedestalItem(2, essence).withSourceCost(7000).keepNbtOfReagent(true).build());
        recipes.add(Abuilder().withResult(armorSet.getBoots()).withReagent(Ingredient.of(AETagsProvider.AEItemTagsProvider.MAGIC_BOOT)).withPedestalItem(ModItems.MARK_OF_MASTERY.get()).withPedestalItem(Items.NETHERITE_INGOT).withPedestalItem(2, essence).withSourceCost(7000).keepNbtOfReagent(true).build());

    }

    protected static Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/ars_elemental/recipe/" + str + ".json");
    }

    @Override
    public String getName() {
        return "Ars Elemental Apparatus";
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