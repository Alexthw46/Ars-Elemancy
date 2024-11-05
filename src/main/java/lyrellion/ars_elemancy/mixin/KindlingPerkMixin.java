package lyrellion.ars_elemancy.mixin;

import lyrellion.ars_elemancy.registry.ModPotions;
import com.hollingsworth.arsnouveau.api.event.EffectResolveEvent;
import com.hollingsworth.arsnouveau.api.perk.PerkInstance;
import com.hollingsworth.arsnouveau.api.perk.PerkSlot;
import com.hollingsworth.arsnouveau.api.spell.IDamageEffect;
import com.hollingsworth.arsnouveau.common.perk.IgnitePerk;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IgnitePerk.class)
public class KindlingPerkMixin {

    @Inject(method = "onEffectPreResolve", at = @At("HEAD"), remap = false)
    private void onEffectPreResolve(EffectResolveEvent.Pre event, PerkInstance perkInstance, CallbackInfo ci) {
        if (event.resolveEffect instanceof IDamageEffect damageEffect && event.rayTraceResult instanceof EntityHitResult entityHitResult && perkInstance.getSlot() == PerkSlot.THREE) {
            if (damageEffect.canDamage(event.shooter, event.spellStats, event.resolver.spellContext, event.resolver, entityHitResult.getEntity()) && entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(ModPotions.MAGIC_FIRE, 20), livingEntity);
            }
        }
    }

}