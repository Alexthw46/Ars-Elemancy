package lyrellion.ars_elemancy.util;

import lyrellion.ars_elemancy.registry.ModRegistry;
import com.hollingsworth.arsnouveau.common.world.tree.AbstractSupplierBlockStateProvider;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;

import static lyrellion.ars_elemancy.ArsElemancy.prefix;

public class SupplierBlockStateProviderAE extends AbstractSupplierBlockStateProvider {
    public SupplierBlockStateProviderAE(String key) {
        super(prefix(key));
    }

    @Override
    protected BlockStateProviderType<?> type() {
        return ModRegistry.AE_BLOCKSTATE_PROVIDER.get();
    }

    public static final MapCodec<SupplierBlockStateProviderAE> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.STRING.fieldOf("key").forGetter(d -> d.key.getPath()))
            .apply(instance, SupplierBlockStateProviderAE::new));

}
