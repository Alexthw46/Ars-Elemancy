package lyrellion.ars_elemancy.common.items.armor;

import alexthw.ars_elemental.ArsElemental;
import com.alexthw.sauce.event.AttributeEventHandler;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.hollingsworth.arsnouveau.api.spell.SpellSchools;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

import static lyrellion.ars_elemancy.common.items.armor.ArmorSet.weaknessMap;

public class HeavyArmorE extends ElemancyArmor {

    public HeavyArmorE(ArmorItem.Type slot, SpellSchool element, Properties builder) {
        super(slot, element, schoolToMaterial(element.getId() + "_heavy"), builder.durability(slot.getDurability(50)), "heavy_armor_e");
    }

    @Override
    public String getTier() {
        return "heavy";
    }

    @Override
    public @NotNull ItemAttributeModifiers getDefaultAttributeModifiers(@NotNull ItemStack stack) {
        ItemAttributeModifiers itemAttributeModifiers = super.getDefaultAttributeModifiers(stack);

        if (element == SpellSchools.ELEMENTAL)
            return itemAttributeModifiers.withModifierAdded(AttributeEventHandler.schoolToDefenseAttribute.get(this.element), new AttributeModifier(ArsElemental.prefix("elemental_defense_armor_" + this.type.getName()), 75, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()))
                    .withModifierAdded(AttributeEventHandler.schoolToPowerAttribute.get(this.element), new AttributeModifier(ArsElemental.prefix("elemental_power_armor_" + this.type.getName()), 0.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()));

        for (var school : this.element.getSubSchools()) {
            if (school == SpellSchools.ELEMENTAL) continue;
            itemAttributeModifiers = itemAttributeModifiers.withModifierAdded(AttributeEventHandler.schoolToDefenseAttribute.get(weaknessMap.getOrDefault(school, SpellSchools.ELEMENTAL)), new AttributeModifier(ArsElemental.prefix("elemental_weakness_armor_" + this.type.getName()), 25, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()))
                    .withModifierAdded(AttributeEventHandler.schoolToDefenseAttribute.get(school), new AttributeModifier(ArsElemental.prefix("elemental_defense_armor_" + this.type.getName()), 50, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()))
                    .withModifierAdded(AttributeEventHandler.schoolToPowerAttribute.get(school), new AttributeModifier(ArsElemental.prefix("elemental_power_armor_" + this.type.getName()), 0.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()));
        }

        return itemAttributeModifiers;
    }
}