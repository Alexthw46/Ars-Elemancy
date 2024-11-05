package lyrellion.ars_elemancy.datagen;

import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.hollingsworth.arsnouveau.common.crafting.recipes.GlyphRecipe;
import com.hollingsworth.arsnouveau.common.datagen.GlyphRecipeProvider;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

import static com.hollingsworth.arsnouveau.setup.registry.RegistryHelper.getRegistryName;


public class AEGlyphProvider extends GlyphRecipeProvider {

    public AEGlyphProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }


    @Override
    public void collectJsons(CachedOutput cache) {


        for (GlyphRecipe recipe : recipes) {
            Path path = getScribeGlyphPath(output, recipe.output.getItem());
            saveStable(cache, GlyphRecipe.CODEC.encodeStart(JsonOps.INSTANCE, recipe).getOrThrow(), path);
        }

    }

    public GlyphRecipe addRecipe(AbstractSpellPart part, ItemLike... items) {
        var builder = get(part);
        for (ItemLike item : items) {
            builder.withItem(item);
        }
        recipes.add(builder);
        return builder;
    }

    protected static Path getScribeGlyphPath(Path pathIn, Item glyph) {
        return pathIn.resolve("data/ars_elemancy/recipe/" + getRegistryName(glyph).getPath() + ".json");
    }

    @Override
    public @NotNull String getName() {
        return "Ars Elemental Glyph Recipes";
    }
}


