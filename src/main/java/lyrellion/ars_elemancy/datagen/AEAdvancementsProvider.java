package lyrellion.ars_elemancy.datagen;

import lyrellion.ars_elemancy.ArsElemancy;
import lyrellion.ars_elemancy.registry.ModItems;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.common.datagen.advancement.ANAdvancementBuilder;
import com.hollingsworth.arsnouveau.common.datagen.advancement.ANAdvancements;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AEAdvancementsProvider extends AdvancementProvider {

    public AEAdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new AEAdvancements()));
    }


    public String getOldName() {
        return "Ars Elemental Advancement Datagen";
    }


    public static class AEAdvancements extends ANAdvancements {

        static Consumer<AdvancementHolder> advancementConsumer;

        static AdvancementHolder dummy(String name) {
            return new AdvancementHolder(ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, name),null);
        }

        @Override
        public void generate(HolderLookup.@NotNull Provider registries, @NotNull Consumer<AdvancementHolder> con, @NotNull ExistingFileHelper existingFileHelper) {
            advancementConsumer = con;
            //AdvancementHolder curioBag = saveBasicItem(ModItems.CURIO_BAG.get(), dummy("magebloom_crop"));

            //AdvancementHolder air = saveBasicItem(ModItems.LESSER_AIR_FOCUS.get(), curioBag);

            //buildBasicItem(ModItems.AIR_FOCUS.get(), "air_focus", AdvancementType.CHALLENGE, air).save(con);

        }

        public AdvancementHolder saveBasicItem(ItemLike item, AdvancementHolder parent) {
            return buildBasicItem(item, BuiltInRegistries.ITEM.getKey(item.asItem()).getPath(), AdvancementType.TASK, parent).save(advancementConsumer);
        }

        public ANAdvancementBuilder buildBasicItem(ItemLike item, String id, AdvancementType frame, AdvancementHolder parent) {
            return builder(id).display(item, frame).requireItem(item).parent(parent);
        }

        public ANAdvancementBuilder builder(String key) {
            return ANAdvancementBuilder.builder(ArsElemancy.MODID, key);
        }

    }

}
