package lyrellion.ars_elemancy.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import static lyrellion.ars_elemancy.ArsElemancy.MODID;

@SuppressWarnings("DataFlowIssue")
public class ModTiles {

    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MODID);


}
