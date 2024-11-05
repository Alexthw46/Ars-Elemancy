package lyrellion.ars_elemancy.event;

import lyrellion.ars_elemancy.ArsElemancy;
import lyrellion.ars_elemancy.ArsNouveauRegistry;
import lyrellion.ars_elemancy.api.item.ISchoolFocus;
import lyrellion.ars_elemancy.common.items.armor.SummonPerk;
import lyrellion.ars_elemancy.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.entity.ISummon;
import com.hollingsworth.arsnouveau.api.event.SummonEvent;
import com.hollingsworth.arsnouveau.api.spell.SpellSchool;
import com.hollingsworth.arsnouveau.api.util.PerkUtil;
import com.hollingsworth.arsnouveau.common.entity.*;
import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = ArsElemancy.MODID)
public class SummonEvents {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void summonedEvent(SummonEvent event) {

        SpellSchool focus = ISchoolFocus.hasFocus(event.shooter);

        if (!event.world.isClientSide && focus != null) {

            // boost summoned entities if necromancy focus is equipped
            if (focus == ArsNouveauRegistry.NECROMANCY) {
                if (event.summon.getLivingEntity() != null) {
                    event.summon.getLivingEntity().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 500, 1));
                    event.summon.getLivingEntity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 1));
                }
            }

        }
    }


    @SubscribeEvent
    public static void summonSickReduction(MobEffectEvent.Added event) {
        MobEffectInstance effectInstance = event.getEffectInstance();
        if (effectInstance != null && effectInstance.getEffect() == ModPotions.SUMMONING_SICKNESS_EFFECT) {
            effectInstance.duration = effectInstance.getDuration() * (1 - PerkUtil.countForPerk(SummonPerk.INSTANCE, event.getEntity()) / 10);
        }
    }

    @SubscribeEvent
    public static void summonPowerup(LivingDamageEvent.Pre event) {
        if (event.getSource().getEntity() instanceof ISummon summon && event.getEntity().level() instanceof ServerLevel) {
            if (summon.getOwner() instanceof Player player) {

                event.setNewDamage((float) (event.getNewDamage() + player.getAttributeValue(ModRegistry.SUMMON_POWER)));

                if (summon instanceof SummonWolf) {
                    SpellSchool school = ISchoolFocus.hasFocus(player);
                    if (school != null) switch (school.getId()) {
                        case "fire" -> event.getEntity().setRemainingFireTicks(5 * 20);
                        case "water" ->
                                event.getEntity().addEffect(new MobEffectInstance(ModPotions.FREEZING_EFFECT, 100, 1));
                        case "air" ->
                                event.getEntity().addEffect(new MobEffectInstance(ModPotions.SHOCKED_EFFECT, 100, 1));
                        case "earth" -> event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 100));
                    }
                }
            }
        }
    }
}
