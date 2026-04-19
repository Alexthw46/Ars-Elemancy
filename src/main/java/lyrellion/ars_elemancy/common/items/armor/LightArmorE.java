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

public class LightArmorE extends ElemancyArmor {

    public LightArmorE(ArmorItem.Type slot, SpellSchool element, Properties builder) {
        super(slot, element, schoolToMaterial(element.getId() + "_light"), builder.durability(slot.getDurability(30)), "light_armor_e");
    }


    @Override
    public String getTier() {
        return "light";
    }

    @Override
    public @NotNull ItemAttributeModifiers getDefaultAttributeModifiers(@NotNull ItemStack stack) {
        ItemAttributeModifiers itemAttributeModifiers = super.getDefaultAttributeModifiers(stack);

        if (element == SpellSchools.ELEMENTAL)
            return itemAttributeModifiers.withModifierAdded(AttributeEventHandler.schoolToDefenseAttribute.get(this.element), new AttributeModifier(ArsElemental.prefix("elemental_defense_armor_" + this.type.getName()), 12.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()))
                    .withModifierAdded(AttributeEventHandler.schoolToPowerAttribute.get(this.element), new AttributeModifier(ArsElemental.prefix("elemental_power_armor_" + this.type.getName()), 2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()));

        for (var school : this.element.getSubSchools()) {
            if (school == SpellSchools.ELEMENTAL) continue;
            itemAttributeModifiers = itemAttributeModifiers.withModifierAdded(AttributeEventHandler.schoolToDefenseAttribute.get(weaknessMap.getOrDefault(school, SpellSchools.ELEMENTAL)), new AttributeModifier(ArsElemental.prefix("elemental_weakness_armor_" + this.type.getName()), -25, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()))
                    .withModifierAdded(AttributeEventHandler.schoolToDefenseAttribute.get(school), new AttributeModifier(ArsElemental.prefix("elemental_defense_armor_" + this.type.getName()), 12.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()))
                    .withModifierAdded(AttributeEventHandler.schoolToPowerAttribute.get(school), new AttributeModifier(ArsElemental.prefix("elemental_power_armor_" + this.type.getName()), 2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.bySlot(this.type.getSlot()));
        }

        return itemAttributeModifiers;
    }
}