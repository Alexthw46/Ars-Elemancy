package lyrellion.ars_elemancy.datagen;

import lyrellion.ars_elemancy.ArsElemancy;
import lyrellion.ars_elemancy.registry.ModAdvTriggers;
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
        public void generate(HolderLookup.Provider registries, @NotNull Consumer<AdvancementHolder> con, @NotNull ExistingFileHelper existingFileHelper) {
            advancementConsumer = con;

            saveBasicItem(ModItems.MARK_OF_MASTERY.get(), dummy("wilden_tribute"));

            builder("mirror_shield").display(ItemsRegistry.ENCHANTERS_SHIELD, AdvancementType.CHALLENGE, true).addCriterion(ModAdvTriggers.MIRROR.get().createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty()))).parent(dummy("enchanting_apparatus")).save(con);

            AdvancementHolder curioBag = saveBasicItem(ModItems.CURIO_BAG.get(), dummy("magebloom_crop"));
            saveBasicItem(ModItems.CASTER_BAG.get(), curioBag);

            AdvancementHolder air = saveBasicItem(ModItems.LESSER_AIR_FOCUS.get(), curioBag);
            AdvancementHolder fire = saveBasicItem(ModItems.LESSER_FIRE_FOCUS.get(), curioBag);
            AdvancementHolder earth = saveBasicItem(ModItems.LESSER_EARTH_FOCUS.get(), curioBag);
            AdvancementHolder water = saveBasicItem(ModItems.LESSER_WATER_FOCUS.get(), curioBag);
            buildBasicItem(ModItems.AIR_FOCUS.get(), "air_focus", AdvancementType.CHALLENGE, air).save(con);
            buildBasicItem(ModItems.FIRE_FOCUS.get(), "fire_focus", AdvancementType.CHALLENGE, fire).save(con);
            buildBasicItem(ModItems.EARTH_FOCUS.get(), "earth_focus", AdvancementType.CHALLENGE, earth).save(con);
            buildBasicItem(ModItems.WATER_FOCUS.get(), "water_focus", AdvancementType.CHALLENGE, water).save(con);



            //path of Air
            builder("levitation").display(ModItems.AIR_CTOME.get(), AdvancementType.GOAL).addCriterion(ModAdvTriggers.LEVITATE.get().createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty()))).parent(air).save(con);


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
