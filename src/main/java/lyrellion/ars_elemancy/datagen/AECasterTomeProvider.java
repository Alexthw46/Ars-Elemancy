package lyrellion.ars_elemancy.datagen;

import com.hollingsworth.arsnouveau.api.sound.ConfiguredSpellSound;
import com.hollingsworth.arsnouveau.api.spell.Spell;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.common.crafting.recipes.CasterTomeData;
import com.hollingsworth.arsnouveau.common.datagen.CasterTomeProvider;
import com.hollingsworth.arsnouveau.common.items.CasterTome;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.nio.file.Path;

import static lyrellion.ars_elemancy.ArsElemancy.prefix;

public class AECasterTomeProvider extends CasterTomeProvider {
    public AECasterTomeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void collectJsons(CachedOutput cache) {


        Path output = this.generator.getPackOutput().getOutputFolder();
        for (CasterRecipeWrapper g : tomes) {
            Path path = getRecipePath(output, g.id().getPath());
            saveStable(cache, CasterTomeData.CODEC.encodeStart(JsonOps.INSTANCE, g.toData()).getOrThrow(), path);
        }
    }

    public CasterRecipeWrapper buildTome(DeferredHolder<Item, ? extends CasterTome> item, String id, String name, Spell spell, String flavorText, ParticleColor particleColor) {
        return new CasterRecipeWrapper(prefix(id + "_tome"),
                name,
                spell.serializeRecipe(),
                item.getId(),
                flavorText,
                particleColor.serialize(), ConfiguredSpellSound.DEFAULT);
    }

    protected Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/ars_elemancy/recipe/tomes/" + str + ".json");
    }

    @Override
    public String getName() {
        return "Ars Elemental Caster Tomes Datagen";
    }

}
