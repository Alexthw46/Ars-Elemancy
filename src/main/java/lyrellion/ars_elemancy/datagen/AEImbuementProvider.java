package lyrellion.ars_elemancy.datagen;

import lyrellion.ars_elemancy.registry.ModItems;
import com.hollingsworth.arsnouveau.common.crafting.recipes.ImbuementRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ImbuementRecipeProvider;
import com.hollingsworth.arsnouveau.common.datagen.RecipeDatagen;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry.MANIPULATION_ESSENCE;
import static lyrellion.ars_elemancy.datagen.Datagen.provider;

public class AEImbuementProvider extends ImbuementRecipeProvider {

    public AEImbuementProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput pOutput) {
        collectJsons(pOutput);
        List<CompletableFuture<?>> futures = new ArrayList<>();
        return provider.thenCompose((registry) -> {
            for (ImbuementRecipe g : recipes) {
                Path path = getRecipePath(output, g.id.getPath());
                futures.add(DataProvider.saveStable(pOutput, registry, ImbuementRecipe.CODEC, g, path));
            }
            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        });
    }

    @Override
    public void collectJsons(CachedOutput cache) {

        recipes.add(new ImbuementRecipe("tempest_essence", Ingredient.of(ItemsRegistry.MANIPULATION_ESSENCE), ModItems.TEMPEST_ESSENCE.get().getDefaultInstance(), 3000)
                .withPedestalItem(ItemsRegistry.WATER_ESSENCE)
                .withPedestalItem(ItemsRegistry.AIR_ESSENCE));
        recipes.add(new ImbuementRecipe("cinder_essence", Ingredient.of(ItemsRegistry.MANIPULATION_ESSENCE), ModItems.CINDER_ESSENCE.get().getDefaultInstance(), 3000)
                .withPedestalItem(ItemsRegistry.FIRE_ESSENCE)
                .withPedestalItem(ItemsRegistry.AIR_ESSENCE));
        recipes.add(new ImbuementRecipe("silt_essence", Ingredient.of(ItemsRegistry.MANIPULATION_ESSENCE), ModItems.SILT_ESSENCE.get().getDefaultInstance(), 3000)
                .withPedestalItem(ItemsRegistry.EARTH_ESSENCE)
                .withPedestalItem(ItemsRegistry.AIR_ESSENCE));
        recipes.add(new ImbuementRecipe("mire_essence", Ingredient.of(ItemsRegistry.MANIPULATION_ESSENCE), ModItems.MIRE_ESSENCE.get().getDefaultInstance(), 3000)
                .withPedestalItem(ItemsRegistry.WATER_ESSENCE)
                .withPedestalItem(ItemsRegistry.EARTH_ESSENCE));
        recipes.add(new ImbuementRecipe("vapor_essence", Ingredient.of(ItemsRegistry.MANIPULATION_ESSENCE), ModItems.VAPOR_ESSENCE.get().getDefaultInstance(), 3000)
                .withPedestalItem(ItemsRegistry.WATER_ESSENCE)
                .withPedestalItem(ItemsRegistry.FIRE_ESSENCE));
        recipes.add(new ImbuementRecipe("lava_essence", Ingredient.of(ItemsRegistry.MANIPULATION_ESSENCE), ModItems.LAVA_ESSENCE.get().getDefaultInstance(), 3000)
                .withPedestalItem(ItemsRegistry.FIRE_ESSENCE)
                .withPedestalItem(ItemsRegistry.EARTH_ESSENCE));

//        recipes.add(new ImbuementRecipe("mark_of_mastery", Ingredient.of(ItemsRegistry.WILDEN_TRIBUTE), new ItemStack(ModItems.MARK_OF_MASTERY.get(), 5), 10000)
//                .withPedestalItem(ItemsRegistry.EARTH_ESSENCE)
//                .withPedestalItem(ItemsRegistry.FIRE_ESSENCE)
//                .withPedestalItem(ItemsRegistry.WATER_ESSENCE)
//                .withPedestalItem(ItemsRegistry.AIR_ESSENCE)
//                .withPedestalItem(ItemsRegistry.ABJURATION_ESSENCE)
//                .withPedestalItem(ItemsRegistry.CONJURATION_ESSENCE)
//                .withPedestalItem(MANIPULATION_ESSENCE)
//                .withPedestalItem(ModItems.ANIMA_ESSENCE.get())
//        );

    }

    protected Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/ars_elemancy/recipe/" + str + ".json");
    }

    @Override
    public @NotNull String getName() {
        return "Ars Elemental Imbuement";
    }

}
