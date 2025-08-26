package lyrellion.ars_elemancy.common.items.foci;

import alexthw.ars_elemental.common.items.foci.GreaterElementalFocus;
import com.alexthw.sauce.util.TooltipUtils;
import com.google.common.collect.Multimap;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

import static lyrellion.ars_elemancy.ConfigHandler.COMMON;

public class ElemancyFocus extends GreaterElementalFocus {
    public ElemancyFocus(Properties properties, SpellSchool element) {
        super(properties, element);
    }

    @Override
    public double getDiscount() {
        return COMMON.MajorFocusDiscount.get();
    }

    @Override
    public SpellStats.Builder applyItemModifiers(ItemStack stack, SpellStats.Builder builder, AbstractSpellPart spellPart, HitResult rayTraceResult, net.minecraft.world.level.Level world, @Nullable LivingEntity shooter, SpellContext spellContext) {
        if (element.isPartOfSchool(spellPart)) {
            builder.addAmplification(getBoostMultiplier() * 2);
        }
        return builder;
    }

    protected double getBoostMultiplier() {
        return switch (element.getId()) {
            case "tempest" -> COMMON.TempestMasteryBuff.get();
            case "silt" -> COMMON.SiltMasteryBuff.get();
            case "vapor" -> COMMON.VaporMasteryBuff.get();
            case "mire" -> COMMON.MireMasteryBuff.get();
            case "lava" -> COMMON.LavaMasteryBuff.get();
            case "cinder" -> COMMON.CinderMasteryBuff.get();
            case "elemancy" -> COMMON.ElementalMasteryBuff.get();
            default -> 0;
        };
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity().level().isClientSide() || !(slotContext.entity() instanceof Player player) || !COMMON.EnableRegenBonus.get())
            return;
        // every 20 ticks, check if the player is in the right environment for the school, and apply the appropriate effect
        if (player.tickCount % 20 == 0) {
            this.applyEnvironmentalEffects(player, this.getSchool());
        }
    }

    public void applyEnvironmentalEffects(Player player, SpellSchool school) {
        switch (school.getId()) {
            case "fire" -> {
                if (player.isOnFire() || player.isInLava()) {
                    player.addEffect(new MobEffectInstance(ModPotions.SPELL_DAMAGE_EFFECT, 200, 1));
                }
            }
            case "water" -> {
                if (player.isInWaterRainOrBubble()) {
                    if (player.isSwimming()) {
                        player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 200, 1));
                        player.addEffect(new MobEffectInstance(ModPotions.MANA_REGEN_EFFECT, 120, 1));
                    } else {
                        player.addEffect(new MobEffectInstance(ModPotions.MANA_REGEN_EFFECT, 120, 0));
                    }
                }
            }
            case "air" -> {
                if (player.hasEffect(ModPotions.SHOCKED_EFFECT)) {
                    player.addEffect(new MobEffectInstance(ModPotions.MANA_REGEN_EFFECT, 60, 1));
                    player.addEffect(new MobEffectInstance(ModPotions.SPELL_DAMAGE_EFFECT, 60, 1));
                } else if (player.getY() > 200) {
                    player.addEffect(new MobEffectInstance(ModPotions.MANA_REGEN_EFFECT, 120, 0));
                }
            }
            case "earth" -> {
                if (player.getY() < 0) {
                    player.addEffect(new MobEffectInstance(ModPotions.MANA_REGEN_EFFECT, 120, 0));
                }
            }
        }

        for (var sub : school.getSubSchools()) {
            this.applyEnvironmentalEffects(player, sub);
        }
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation uuid, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> map = super.getAttributeModifiers(slotContext, uuid, stack);

        for (var sub : this.getSchool().getSubSchools()) {
            if (sub.equals(SpellSchools.ELEMENTAL_EARTH)) {
                map.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, 0.2f, AttributeModifier.Operation.ADD_VALUE));
            }
        }
        return map;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext context, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.ars_elemental.focus_element"));
        TooltipUtils.addOnShift(pTooltipComponents, () -> {
            pTooltipComponents.add(Component.translatable("tooltip.ars_elemancy.focus_boost." + element.getId()));

            for (var sub : this.getSchool().getSubSchools()) {
                pTooltipComponents.add(Component.translatable("tooltip.ars_elemental.focus_element_mana." + sub.getId()));
            }
        }, "focus");
    }
}
