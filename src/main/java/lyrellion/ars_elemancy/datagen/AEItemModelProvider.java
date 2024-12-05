package lyrellion.ars_elemancy.datagen;

import lyrellion.ars_elemancy.ArsElemancy;
import lyrellion.ars_elemancy.api.item.SpellPrismLens;
import lyrellion.ars_elemancy.registry.ModItems;
import com.hollingsworth.arsnouveau.common.block.ArchfruitPod;
import com.hollingsworth.arsnouveau.common.block.StrippableLog;
import com.hollingsworth.arsnouveau.common.items.AnimBlockItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

import static lyrellion.ars_elemancy.datagen.Datagen.takeAll;

@SuppressWarnings("ALL")
public class AEItemModelProvider extends ItemModelProvider {
    public AEItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), ArsElemancy.MODID, existingFileHelper);
    }

    private static final ResourceLocation GENERATED = ResourceLocation.withDefaultNamespace("item/generated");

    private static final ResourceLocation HANDHELD = ResourceLocation.withDefaultNamespace("item/handheld");
    private static final ResourceLocation SPAWN_EGG = ResourceLocation.withDefaultNamespace("item/template_spawn_egg");

    @Override
    protected void registerModels() {
        Set<DeferredHolder<Item, ? extends Item>> items = new HashSet<>(ModItems.ITEMS.getEntries());

        takeAll(items, i -> i.get() instanceof AnimBlockItem).forEach(this::blockItem);
        takeAll(items, i -> i.get() instanceof SpellPrismLens);
        takeAll(items, i -> i.get() instanceof BlockItem bi && bi.getBlock() instanceof ArchfruitPod).forEach(this::generatedItem);
        takeAll(items, i -> i.get() instanceof BlockItem bi && bi.getBlock() instanceof FenceBlock).forEach(this::fenceBlockItem);
        takeAll(items, i -> i.get() instanceof BlockItem bi && bi.getBlock() instanceof SaplingBlock).forEach(this::blockGeneratedItem);
        takeAll(items, i -> i.get() instanceof BlockItem).forEach(this::blockItem);
        takeAll(items, i -> i.get() instanceof DiggerItem).forEach(this::handheldItem);
        takeAll(items, i -> i.get() instanceof SpawnEggItem).forEach(this::spawnEgg);
        items.forEach(this::generatedItem);

    }

    private void spawnEgg(DeferredHolder<Item, ? extends Item> i) {
        String name = BuiltInRegistries.ITEM.getKey(i.get()).getPath();
        try {
            withExistingParent(name, SPAWN_EGG);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to generate model {}", name, e);
        }
    }

    private void handheldItem(DeferredHolder<Item, ? extends Item> i) {
        String name = BuiltInRegistries.ITEM.getKey(i.get()).getPath();
        try {
            withExistingParent(name, HANDHELD).texture("layer0", ArsElemancy.prefix("item/" + name));
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to generate model {}", name, e);
        }
    }

    private void generatedItem(DeferredHolder<Item, ? extends Item> i) {
        String name = BuiltInRegistries.ITEM.getKey(i.get()).getPath();
        try {
            withExistingParent(name, GENERATED).texture("layer0", ArsElemancy.prefix("item/" + name));
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to generate model {}", name, e);
        }
    }

    private void focusModel(DeferredHolder<Item, ? extends Item> i) {
        String name = BuiltInRegistries.ITEM.getKey(i.get()).getPath();
        try {
            withExistingParent("item/focus/" + name, GENERATED).texture("layer0", ArsElemancy.prefix("item/" + name));
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to generate model {}", name, e);
        }
    }

    private void blockGeneratedItem(DeferredHolder<Item, ? extends Item> i) {
        String name = BuiltInRegistries.ITEM.getKey(i.get()).getPath();
        try {
            withExistingParent(name, GENERATED).texture("layer0", ArsElemancy.prefix("block/" + name));
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to generate model {}", name, e);
        }
    }

    private void blockItem(DeferredHolder<Item, ? extends Item> i) {
        String name = BuiltInRegistries.ITEM.getKey(i.get()).getPath();
        String root = "block/";
        if (i.get() instanceof BlockItem bi && (bi.getBlock() instanceof RotatedPillarBlock || bi.getBlock() instanceof LeavesBlock || bi.getBlock() instanceof StrippableLog))
            root = "block/archwood/";
        try {
            getBuilder(name).parent(new ModelFile.UncheckedModelFile(ArsElemancy.prefix(root + name)));
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to generate model {}", name, e);
        }
    }

    private void fenceBlockItem(DeferredHolder<Item, ? extends Item> i) {
        String name = BuiltInRegistries.ITEM.getKey(i.get()).getPath();
        String baseName = name.substring(0, name.length() - 6);
        try {
            fenceInventory(name, ArsElemancy.prefix("block/" + baseName));
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to generate model {}", name, e);
        }
    }

    @NotNull
    @Override
    public String getName() {
        return "Ars Elemancy Item Models";
    }
}
