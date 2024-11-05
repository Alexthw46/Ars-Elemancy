package lyrellion.ars_elemancy.common.items.armor;

import lyrellion.ars_elemancy.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.perk.Perk;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

import static lyrellion.ars_elemancy.ArsElemancy.prefix;

public class SummonPerk extends Perk {

    public static SummonPerk INSTANCE = new SummonPerk();

    public SummonPerk() {
        super(prefix("thread_summon"));
    }

    @Override
    public @NotNull ItemAttributeModifiers applyAttributeModifiers(ItemAttributeModifiers modifiers, ItemStack stack, int slotValue, EquipmentSlotGroup equipmentSlotGroup) {
        return modifiers.withModifierAdded(ModRegistry.SUMMON_POWER, new AttributeModifier(prefix("summon_power"), slotValue - 1, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
    }

}
