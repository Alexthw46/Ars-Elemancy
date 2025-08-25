package lyrellion.ars_elemancy.api.item;

import com.alexthw.sauce.api.item.IElementalArmor;
import lyrellion.ars_elemancy.common.items.armor.AAMaterials;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.hollingsworth.arsnouveau.api.spell.SpellStats;
import com.hollingsworth.arsnouveau.setup.registry.MaterialRegistry;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public interface IElemancyArmor extends IElementalArmor {
    static Holder<ArmorMaterial> schoolToMaterial(SpellSchool element) {
        return switch (element.getId()) {
            case "tempest" -> AAMaterials.TEMPEST;
            case "silt" -> AAMaterials.SILT;
            case "mire" -> AAMaterials.MIRE;
            case "vapor" -> AAMaterials.VAPOR;
            case "lava" -> AAMaterials.LAVA;
            case "cinder" -> AAMaterials.CINDER;
            case "elemental" -> AAMaterials.ELEMANCER;

            default -> MaterialRegistry.MEDIUM;
        };
    }

    @Override
    default SpellStats.Builder applyItemModifiers(ItemStack stack, SpellStats.Builder builder, AbstractSpellPart spellPart, HitResult rayTraceResult, Level world, @Nullable LivingEntity shooter, SpellContext spellContext) {
        if (getSchool().isPartOfSchool(spellPart)) {
            builder.addAmplification(0.5);
        }
        return builder;
    }

    default double getDiscount(List<AbstractSpellPart> recipe) {
        // check if the recipe contains a glyph from the same school as this armor
        double sum = 0;
        for (AbstractSpellPart part : recipe) {
            if (getSchool().isPartOfSchool(part))
                sum += 0.2 * part.getCastingCost();
        }
        return sum;
    }

    SpellSchool getSchool();

    String getTier();

    default boolean doAbsorb(DamageSource damageSource) {
        if (damageResistances.containsKey(this.getSchool()) && damageSource.is(damageResistances.get(this.getSchool()))) {
            return true;
        }

        for (var school : this.getSchool().getSubSchools()) {
            if (damageResistances.containsKey(school) && damageSource.is(damageResistances.get(school))) {
                return true;
            }
        }

        return false;
    }

    @Override
    default boolean fillAbsorptions(DamageSource damageSource, HashMap<SpellSchool, Integer> bonusMap) {
        boolean changed = false;
        SpellSchool mainSchool = this.getSchool();
        if (damageResistances.containsKey(mainSchool) && damageSource.is(damageResistances.get(mainSchool))) {
            bonusMap.put(mainSchool, bonusMap.getOrDefault(mainSchool, 0) + 1);
            changed = true;
        }

        for (var school : mainSchool.getSubSchools()) {
            if (damageResistances.containsKey(school) && damageSource.is(damageResistances.get(school))) {
                bonusMap.put(school, bonusMap.getOrDefault(school, 0) + 1);
            }
            changed = true;
        }

        return changed;
    }
}
