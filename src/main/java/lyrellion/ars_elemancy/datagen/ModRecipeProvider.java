package lyrellion.ars_elemancy.datagen;

import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.registry.RitualRegistry;
import com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen;
import com.hollingsworth.arsnouveau.common.lib.RitualLib;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static lyrellion.ars_elemancy.ArsElemancy.prefix;
import static lyrellion.ars_elemancy.registry.ModItems.*;


public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator pGenerator, CompletableFuture<HolderLookup.Provider> provider) {
        super(pGenerator.getPackOutput(), provider);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput consumer) {


        RecipeDatagen.makeWood(FLASHING_ARCHWOOD_LOG.get(), FLASHING_ARCHWOOD.get(), 3).save(consumer);
        strippedLogToWood(consumer, FLASHING_ARCHWOOD_LOG_STRIPPED.get(), FLASHING_ARCHWOOD_STRIPPED.get());

        shapelessBuilder(getRitualItem(ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, RitualLib.FLIGHT)))
                .requires(FLASHING_ARCHWOOD_LOG.get())
                .requires(ItemsRegistry.WILDEN_WING, 1)
                .requires(Ingredient.of(Tags.Items.GEMS_DIAMOND), 2)
                .requires(Items.FEATHER)
                .save(consumer, prefix("flight_alt"));


        shapedBuilder(CURIO_BAG.get())
                .define('M', ItemsRegistry.MAGE_FIBER)
                .define('C', Items.CHEST)
                .pattern("MMM").pattern("MCM").pattern("MMM")
                .unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.WORN_NOTEBOOK))
                .save(consumer, prefix("curio_bag"));

        shapedBuilder(CASTER_BAG.get())
                .define('M', ItemsRegistry.MAGE_FIBER)
                .define('C', Items.CHEST)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .pattern("MDM").pattern("MCM").pattern("MMM")
                .unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.WORN_NOTEBOOK))
                .save(consumer, prefix("caster_bag"));

    }

    public Item getRitualItem(ResourceLocation id) {
        return RitualRegistry.getRitualItemMap().get(id);
    }

    public ShapedRecipeBuilder shapedBuilder(ItemLike result) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC ,result);
    }

    public ShapelessRecipeBuilder shapelessBuilder(ItemLike result) {
        return shapelessBuilder(result, 1);
    }

    public ShapelessRecipeBuilder shapelessBuilder(ItemLike result, int resultCount) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, resultCount).unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.WORN_NOTEBOOK));
    }

    private static void strippedLogToWood(RecipeOutput recipeConsumer, ItemLike stripped, ItemLike output) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 3).define('#', stripped).pattern("##").pattern("##").group("bark")
                .unlockedBy("has_journal", InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.WORN_NOTEBOOK))
                .save(recipeConsumer);
    }

}