package lyrellion.ars_elemancy.datagen;

import lyrellion.ars_elemancy.ArsElemancy;
import lyrellion.ars_elemancy.registry.ModItems;
import com.hollingsworth.arsnouveau.common.block.ArchfruitPod;
import com.hollingsworth.arsnouveau.common.block.StrippableLog;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

import static lyrellion.ars_elemancy.ArsElemancy.prefix;
import static lyrellion.ars_elemancy.datagen.Datagen.takeAll;

public class AEBlockStateProvider extends BlockStateProvider {

    public AEBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen.getPackOutput(), ArsElemancy.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        Set<DeferredHolder<Block, ? extends Block>> blocks = new HashSet<>(ModItems.BLOCKS.getEntries());
        takeAll(blocks, b -> b.get() instanceof FlowerPotBlock).forEach(this::registerOnlyState);
        takeAll(blocks, b -> b.get() instanceof RotatedPillarBlock || b.get() instanceof StrippableLog).forEach(this::logBlock);
        takeAll(blocks, b -> b.get() instanceof SlabBlock).forEach(this::slabBlock);
        takeAll(blocks, b -> b.get() instanceof StairBlock).forEach(this::stairsBlock);
        takeAll(blocks, b -> b.get() instanceof LeavesBlock);
        takeAll(blocks, b -> b.get() instanceof SaplingBlock);
        takeAll(blocks, b -> b.get() instanceof ArchfruitPod);
        blocks.forEach(this::basicBlock);
    }

    public void registerOnlyState(DeferredHolder<Block, ? extends Block> obj) {
        simpleBlock(obj.get(), new ModelFile.UncheckedModelFile(prefix("block/"+ obj.getId().getPath())));
    }

    public void slabBlock(DeferredHolder<Block, ? extends Block> blockRegistryObject) {
        String name = blockRegistryObject.getId().getPath();
        String baseName = name.substring(0, name.length() - 5);
        slabBlock((SlabBlock) blockRegistryObject.get(), prefix(baseName), prefix("block/" + baseName));
    }

    public void logBlock(DeferredHolder<Block, ? extends Block> blockRegistryObject) {

    }

    public void stairsBlock(DeferredHolder<Block, ? extends Block> blockRegistryObject) {
        String name = blockRegistryObject.getId().getPath();
        String baseName = name.substring(0, name.length() - 7);
        stairsBlock((StairBlock) blockRegistryObject.get(), prefix("block/" + baseName));
    }

    public void basicBlock(DeferredHolder<Block, ? extends net.minecraft.world.level.block.Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get());
    }


    @NotNull
    @Override
    public String getName() {
        return "Ars Elemental BlockStates";
    }

}

