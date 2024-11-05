package lyrellion.ars_elemancy.common.components;

import lyrellion.ars_elemancy.common.items.caster_tools.ElementalCasterTome;
import lyrellion.ars_elemancy.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class SchoolCasterTomeData extends AbstractCaster<SchoolCasterTomeData> {
    public static final MapCodec<SchoolCasterTomeData> CODEC = SpellCaster.createCodec(SchoolCasterTomeData::new);

    public static final StreamCodec<RegistryFriendlyByteBuf, SchoolCasterTomeData> STREAM_CODEC = createStream(SchoolCasterTomeData::new);


    @Override
    public MapCodec<SchoolCasterTomeData> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, SchoolCasterTomeData> streamCodec() {
        return STREAM_CODEC;
    }

    public SchoolCasterTomeData() {
        super();
    }

    public SchoolCasterTomeData(Integer slot, String flavorText, Boolean isHidden, String hiddenText, int maxSlots) {
        super(slot, flavorText, isHidden, hiddenText, maxSlots);
    }

    public SchoolCasterTomeData(Integer slot, String flavorText, Boolean isHidden, String hiddenText, int maxSlots, SpellSlotMap spells) {
        super(slot, flavorText, isHidden, hiddenText, maxSlots, spells);
    }

    @Override
    public SpellResolver getSpellResolver(SpellContext context, Level worldIn, LivingEntity playerIn, InteractionHand handIn) {
        return new ElementalCasterTome.ETomeResolver(context, context.getCasterTool().getItem() instanceof ElementalCasterTome tome ? tome.getSchool() : SpellSchools.ELEMENTAL);
    }

    @Override
    public DataComponentType<SchoolCasterTomeData> getComponentType() {
        return ModRegistry.E_TOME_CASTER.get();
    }

    @Override
    protected SchoolCasterTomeData build(int slot, String flavorText, Boolean isHidden, String hiddenText, int maxSlots, SpellSlotMap spells) {
        return new SchoolCasterTomeData(slot, flavorText, isHidden, hiddenText, maxSlots, spells);
    }
}