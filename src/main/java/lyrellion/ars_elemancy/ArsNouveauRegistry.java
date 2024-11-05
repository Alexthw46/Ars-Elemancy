package lyrellion.ars_elemancy;


import lyrellion.ars_elemancy.common.glyphs.*;

import lyrellion.ars_elemancy.common.items.armor.ArmorSet;
import lyrellion.ars_elemancy.common.items.armor.ShockPerk;
import lyrellion.ars_elemancy.common.items.armor.SporePerk;
import lyrellion.ars_elemancy.common.items.armor.SummonPerk;

import lyrellion.ars_elemancy.registry.ModItems;
import lyrellion.ars_elemancy.registry.ModRegistry;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.perk.PerkSlot;
import com.hollingsworth.arsnouveau.api.registry.*;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.block.tile.RotatingTurretTile;
import com.hollingsworth.arsnouveau.common.entity.EntityHomingProjectileSpell;
import com.hollingsworth.arsnouveau.common.entity.EntityProjectileSpell;
import com.hollingsworth.arsnouveau.common.spell.augment.*;
import com.hollingsworth.arsnouveau.common.spell.effect.*;
import com.hollingsworth.arsnouveau.setup.registry.DataComponentRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hollingsworth.arsnouveau.api.spell.SpellSchools.*;
import static lyrellion.ars_elemancy.api.item.IElemancyArmor.damageResistances;
import static com.hollingsworth.arsnouveau.common.block.BasicSpellTurret.TURRET_BEHAVIOR_MAP;
import static com.hollingsworth.arsnouveau.common.block.RotatingSpellTurret.ROT_TURRET_BEHAVIOR_MAP;

public class ArsNouveauRegistry {
    public static final List<AbstractSpellPart> registeredSpells = new ArrayList<>();

    public static final SpellSchool NECROMANCY = new SpellSchool("necromancy");
    public static final SpellSchool TEMPEST = new SpellSchool("tempest").withSubSchool(ELEMENTAL_AIR).withSubSchool(ELEMENTAL_WATER);
    public static final SpellSchool CINDER = new SpellSchool("cinder").withSubSchool(ELEMENTAL_AIR).withSubSchool(ELEMENTAL_FIRE);
    public static final SpellSchool SILT = new SpellSchool("silt").withSubSchool(ELEMENTAL_AIR).withSubSchool(ELEMENTAL_EARTH);
    public static final SpellSchool MIRE = new SpellSchool("mire").withSubSchool(ELEMENTAL_EARTH).withSubSchool(ELEMENTAL_WATER);
    public static final SpellSchool VAPOR = new SpellSchool("vapor").withSubSchool(ELEMENTAL_FIRE).withSubSchool(ELEMENTAL_WATER);
    public static final SpellSchool LAVA = new SpellSchool("lava").withSubSchool(ELEMENTAL_FIRE).withSubSchool(ELEMENTAL_EARTH);

    public static void init() {
        registerGlyphs();
        registerPerks();
        linkDamageResistances();
    }

    private static void registerCasters() {
        SpellCasterRegistry.register(ModItems.SPELL_HORN.get(), (stack) -> stack.get(DataComponentRegistry.SPELL_CASTER.get()));
        SpellCasterRegistry.register(ModItems.AIR_CTOME.get(), (stack) -> stack.get(ModRegistry.E_TOME_CASTER.get()));
        SpellCasterRegistry.register(ModItems.FIRE_CTOME.get(), (stack) -> stack.get(ModRegistry.E_TOME_CASTER.get()));
        SpellCasterRegistry.register(ModItems.EARTH_CTOME.get(), (stack) -> stack.get(ModRegistry.E_TOME_CASTER.get()));
        SpellCasterRegistry.register(ModItems.WATER_CTOME.get(), (stack) -> stack.get(ModRegistry.E_TOME_CASTER.get()));
        SpellCasterRegistry.register(ModItems.NECRO_CTOME.get(), (stack) -> stack.get(ModRegistry.E_TOME_CASTER.get()));
        SpellCasterRegistry.register(ModItems.SHAPERS_CTOME.get(), (stack) -> stack.get(ModRegistry.E_TOME_CASTER.get()));

    }

    private static void linkDamageResistances() {
        damageResistances.put(ELEMENTAL_FIRE, ModRegistry.FIRE_DAMAGE);
        damageResistances.put(ELEMENTAL_AIR, ModRegistry.AIR_DAMAGE);
        damageResistances.put(SpellSchools.ELEMENTAL_EARTH, ModRegistry.EARTH_DAMAGE);
        damageResistances.put(ELEMENTAL_WATER, ModRegistry.WATER_DAMAGE);
    }


    public static void registerGlyphs() {

        //effects
        register(EffectWaterGrave.INSTANCE);
        register(EffectBubbleShield.INSTANCE);
        register(EffectConjureTerrain.INSTANCE);
        register(EffectCharm.INSTANCE);
        register(EffectPhantom.INSTANCE);
        register(EffectLifeLink.INSTANCE);
        register(EffectSpores.INSTANCE);
        register(EffectDischarge.INSTANCE);
        register(EffectEnvenom.INSTANCE);
        register(EffectSpike.INSTANCE);
        register(EffectSpark.INSTANCE);

        //methods
        register(MethodHomingProjectile.INSTANCE);
        register(MethodArcProjectile.INSTANCE);

        //propagators
        register(PropagatorHoming.INSTANCE);
        register(PropagatorArc.INSTANCE);


        // the bullshit one

        register(EffectNullify.INSTANCE);
    }


    public static void registerRitual(AbstractRitual ritual) {
        RitualRegistry.registerRitual(ritual);
    }

    public static void postInit() {
        registerCasters();

        //Schools
        addSchool(EffectHeal.INSTANCE, NECROMANCY);
        addSchool(EffectSummonVex.INSTANCE, NECROMANCY);
        addSchool(EffectWither.INSTANCE, NECROMANCY);
        addSchool(EffectHex.INSTANCE, NECROMANCY);
        addSchool(EffectLifeLink.INSTANCE, NECROMANCY);
        addSchool(EffectCharm.INSTANCE, NECROMANCY);
        addSchool(EffectSummonUndead.INSTANCE, NECROMANCY);

        addSchool(EffectCut.INSTANCE, ELEMENTAL_AIR);

        //Tweaks
        EffectFirework.INSTANCE.compatibleAugments.add(AugmentDampen.INSTANCE);
        EffectLaunch.INSTANCE.compatibleAugments.add(AugmentExtendTime.INSTANCE);
        EffectLaunch.INSTANCE.compatibleAugments.add(AugmentDurationDown.INSTANCE);
        EffectGravity.INSTANCE.compatibleAugments.add(AugmentSensitive.INSTANCE);
        EffectWindshear.INSTANCE.compatibleAugments.add(AugmentFortune.INSTANCE);

        ArsNouveauRegistry.addPerkSlots();

        ArsNouveauAPI.getInstance().getEnchantingRecipeTypes().add(ModRegistry.NETHERITE_UP.get());
        ArsNouveauAPI.getInstance().getEnchantingRecipeTypes().add(ModRegistry.ELEMENTAL_ARMOR_UP.get());

    }

    public static void addSchool(AbstractSpellPart part, SpellSchool school) {
        part.spellSchools.add(school);
        school.addSpellPart(part);
    }

    public static void register(AbstractSpellPart spellPart) {
        GlyphRegistry.registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }


    public static void registerPerks() {
        PerkRegistry.registerPerk(SporePerk.INSTANCE);
        PerkRegistry.registerPerk(ShockPerk.INSTANCE);
        PerkRegistry.registerPerk(SummonPerk.INSTANCE);
    }

    private static void addPerkSlots() {

        ArmorSet[] medium_armors = {ModItems.AIR_ARMOR, ModItems.FIRE_ARMOR, ModItems.EARTH_ARMOR, ModItems.WATER_ARMOR, ModItems.TEMPEST_ARMOR, ModItems.MIRE_ARMOR, ModItems.VAPOR_ARMOR, ModItems.CINDER_ARMOR, ModItems.LAVA_ARMOR, ModItems.SILT_ARMOR, ModItems.ELEMANCER_ARMOR};
        List<PerkSlot> perkSlots = Arrays.asList(PerkSlot.ONE, PerkSlot.TWO, PerkSlot.THREE);
        for (ArmorSet set : medium_armors) {
            PerkRegistry.registerPerkProvider(set.getHat(), List.of(perkSlots, perkSlots, perkSlots, perkSlots));
            PerkRegistry.registerPerkProvider(set.getChest(), List.of(perkSlots, perkSlots, perkSlots, perkSlots));
            PerkRegistry.registerPerkProvider(set.getLegs(), List.of(perkSlots, perkSlots, perkSlots, perkSlots));
            PerkRegistry.registerPerkProvider(set.getBoots(), List.of(perkSlots, perkSlots, perkSlots, perkSlots));
        }

    }

    static {

        ROT_TURRET_BEHAVIOR_MAP.put(MethodHomingProjectile.INSTANCE, new ITurretBehavior() {
            @Override
            public void onCast(SpellResolver resolver, ServerLevel world, BlockPos pos, Player fakePlayer, Position position, Direction direction) {
                EntityHomingProjectileSpell spell = new EntityHomingProjectileSpell(world, resolver);
                spell.setOwner(fakePlayer);
                spell.setPos(position.x(), position.y(), position.z());
                spell.setIgnored(MethodHomingProjectile.basicIgnores(fakePlayer, resolver.spell.getAugments(0, null).contains(AugmentSensitive.INSTANCE), resolver.spell));
                if (world.getBlockEntity(pos) instanceof RotatingTurretTile rotatingTurretTile) {
                    Vec3 vec3d = rotatingTurretTile.getShootAngle().normalize();
                    spell.shoot(vec3d.x(), vec3d.y(), vec3d.z(), 0.25f, 0);
                }
                world.addFreshEntity(spell);
            }
        });

        ROT_TURRET_BEHAVIOR_MAP.put(MethodArcProjectile.INSTANCE, new ITurretBehavior() {
            @Override
            public void onCast(SpellResolver resolver, ServerLevel world, BlockPos pos, Player fakePlayer, Position position, Direction direction) {
                EntityProjectileSpell spell = new EntityProjectileSpell(world, resolver);
                spell.setGravity(true);
                spell.setOwner(fakePlayer);
                spell.setPos(position.x(), position.y(), position.z());
                if (world.getBlockEntity(pos) instanceof RotatingTurretTile rotatingTurretTile) {
                    Vec3 vec3d = rotatingTurretTile.getShootAngle().normalize();
                    spell.shoot(vec3d.x(), vec3d.y(), vec3d.z(), 0.6f, 0);
                }
                world.addFreshEntity(spell);
            }
        });

        TURRET_BEHAVIOR_MAP.put(MethodHomingProjectile.INSTANCE, new ITurretBehavior() {
            @Override
            public void onCast(SpellResolver resolver, ServerLevel world, BlockPos pos, Player fakePlayer, Position position, Direction direction) {
                EntityHomingProjectileSpell spell = new EntityHomingProjectileSpell(world, resolver);
                spell.setOwner(fakePlayer);
                spell.setPos(position.x(), position.y(), position.z());
                spell.setIgnored(MethodHomingProjectile.basicIgnores(fakePlayer, resolver.spell.getAugments(0, null).contains(AugmentSensitive.INSTANCE), resolver.spell));
                spell.shoot(direction.getStepX(), direction.getStepY(), direction.getStepZ(), 0.25f, 0);
                world.addFreshEntity(spell);
            }
        });

        TURRET_BEHAVIOR_MAP.put(MethodArcProjectile.INSTANCE, new ITurretBehavior() {
            @Override
            public void onCast(SpellResolver resolver, ServerLevel world, BlockPos pos, Player fakePlayer, Position position, Direction direction) {
                EntityProjectileSpell spell = new EntityProjectileSpell(world, resolver);
                spell.setGravity(true);
                spell.setOwner(fakePlayer);
                spell.setPos(position.x(), position.y(), position.z());
                spell.shoot(direction.getStepX(), direction.getStepY() + 0.25F, direction.getStepZ(), 0.6f, 0);
                world.addFreshEntity(spell);
            }
        });

    }

}
