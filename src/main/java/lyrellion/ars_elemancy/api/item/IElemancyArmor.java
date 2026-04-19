package lyrellion.ars_elemancy.api.item;

import com.alexthw.sauce.api.item.IElementalArmor;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import net.minecraft.world.damagesource.DamageSource;

import java.util.HashMap;

public interface IElemancyArmor extends IElementalArmor {

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
                changed = true;
            }
        }

        return changed;
    }

}
