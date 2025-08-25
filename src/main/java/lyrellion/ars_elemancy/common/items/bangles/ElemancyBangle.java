package lyrellion.ars_elemancy.common.items.bangles;

import alexthw.ars_elemental.common.items.ElementalCurio;
import com.alexthw.sauce.api.item.ISchoolBangle;
import com.alexthw.sauce.registry.ModRegistry;
import com.google.common.collect.Multimap;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.NeoForgeMod;
import top.theillusivec4.curios.api.SlotContext;

import java.util.ArrayList;
import java.util.List;

public class ElemancyBangle extends ElementalCurio implements ISchoolBangle {
    public final SpellSchool school;

    public ElemancyBangle(Properties pProperties, SpellSchool school) {
        super(pProperties);
        this.school = school;
    }

    @Override
    public SpellSchool getSchool() {
        return this.school;
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation uuid, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> map = super.getAttributeModifiers(slotContext, uuid, stack);

        List<SpellSchool> subschools = new ArrayList<>(this.school.getSubSchools());

        for (var sub : subschools) {
            switch (sub.getId()) {
                case "air" -> {
                    map.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, 0.06f, AttributeModifier.Operation.ADD_VALUE));
                    map.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(uuid, 1.2f, AttributeModifier.Operation.ADD_VALUE));
                }
                case "anima" -> map.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid,  4.d, AttributeModifier.Operation.ADD_VALUE));
                case "earth" -> map.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid,  0.3d, AttributeModifier.Operation.ADD_VALUE));
                case "fire" -> {
                    LivingEntity entity = slotContext.entity();
                    if (entity != null) {
                        Holder<Biome> biome = entity.level().getBiome(slotContext.entity().getOnPos());
                        if (biome.value().getBaseTemperature() > 1.8f) {
                            map.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, 0.035f, AttributeModifier.Operation.ADD_VALUE));
                        }
                    }
                }
                case "summon" -> map.put(ModRegistry.SUMMON_POWER, new AttributeModifier(uuid,  2.d, AttributeModifier.Operation.ADD_VALUE));
                case "water" -> {
                    map.put(NeoForgeMod.SWIM_SPEED, new AttributeModifier(uuid, 0.5f, AttributeModifier.Operation.ADD_VALUE));
                    if (slotContext.entity() != null && slotContext.entity().isInWaterOrRain()) {
                        map.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, 0.035f, AttributeModifier.Operation.ADD_VALUE));
                    }
                }
            }
        }

        return map;
    }
}
