package lyrellion.ars_elemancy.datagen;

import lyrellion.ars_elemancy.common.items.armor.ArmorSet;
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



        //mirror shield enchant

        addArmorRecipes(ModItems.TEMPEST_ARMOR, (ItemLike) ModItems.TEMPEST_ESSENCE);
        addArmorRecipes(ModItems.CINDER_ARMOR, (ItemLike) ModItems.CINDER_ESSENCE);
        addArmorRecipes(ModItems.MIRE_ARMOR, (ItemLike) ModItems.MIRE_ESSENCE);
        addArmorRecipes(ModItems.SILT_ARMOR, (ItemLike) ModItems.SILT_ESSENCE);
        addArmorRecipes(ModItems.LAVA_ARMOR, (ItemLike) ModItems.LAVA_ESSENCE);
        addArmorRecipes(ModItems.VAPOR_ARMOR, (ItemLike) ModItems.VAPOR_ESSENCE);

        Path output = this.generator.getPackOutput().getOutputFolder();
        for (ApparatusRecipeBuilder.RecipeWrapper<? extends EnchantingApparatusRecipe> g : recipes) {
            if (g != null) {
                Path path = getRecipePath(output, g.id().getPath());
                saveStable(cache, g.serialize(), path);
            }
        }

    }

    protected void addArmorRecipes(ArmorSet armorSet, ItemLike essence) {

        recipes.add(Abuilder().withResult(armorSet.getHat()).withReagent(Ingredient.of(AETagsProvider.AEItemTagsProvider.MAGIC_HOOD)).withPedestalItem(MARK_OF_MASTERY.get()).withPedestalItem(Items.NETHERITE_INGOT).withPedestalItem(2, essence).withSourceCost(7000).keepNbtOfReagent(true).build());
        recipes.add(Abuilder().withResult(armorSet.getChest()).withReagent(Ingredient.of(AETagsProvider.AEItemTagsProvider.MAGIC_ROBE)).withPedestalItem(MARK_OF_MASTERY.get()).withPedestalItem(Items.NETHERITE_INGOT).withPedestalItem(2, essence).withSourceCost(7000).keepNbtOfReagent(true).build());
        recipes.add(Abuilder().withResult(armorSet.getLegs()).withReagent(Ingredient.of(AETagsProvider.AEItemTagsProvider.MAGIC_LEG)).withPedestalItem(MARK_OF_MASTERY.get()).withPedestalItem(Items.NETHERITE_INGOT).withPedestalItem(2, essence).withSourceCost(7000).keepNbtOfReagent(true).build());
        recipes.add(Abuilder().withResult(armorSet.getBoots()).withReagent(Ingredient.of(AETagsProvider.AEItemTagsProvider.MAGIC_BOOT)).withPedestalItem(MARK_OF_MASTERY.get()).withPedestalItem(Items.NETHERITE_INGOT).withPedestalItem(2, essence).withSourceCost(7000).keepNbtOfReagent(true).build());

    }

    protected static Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/ars_elemancy/recipe/" + str + ".json");
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
