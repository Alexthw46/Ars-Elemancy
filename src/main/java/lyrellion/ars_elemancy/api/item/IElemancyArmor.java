package lyrellion.ars_elemancy.api.item;

import com.alexthw.sauce.api.item.IElementalArmor;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.hollingsworth.arsnouveau.api.spell.SpellContext;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.hollingsworth.arsnouveau.api.spell.SpellStats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
