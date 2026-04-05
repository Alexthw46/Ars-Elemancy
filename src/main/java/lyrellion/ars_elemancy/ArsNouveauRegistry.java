package lyrellion.ars_elemancy;


import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.perk.PerkSlot;
import com.hollingsworth.arsnouveau.api.registry.*;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.spell.*;
import lyrellion.ars_elemancy.common.items.armor.ArmorSet;
import lyrellion.ars_elemancy.registry.ModItems;
import lyrellion.ars_elemancy.registry.ModRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hollingsworth.arsnouveau.api.spell.SpellSchools.*;

public class ArsNouveauRegistry {
    public static final List<AbstractSpellPart> registeredSpells = new ArrayList<>();

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


    private static void linkDamageResistances() {
    }


    public static void registerGlyphs() {

        //effects

    }


    public static void registerRitual(AbstractRitual ritual) {
        RitualRegistry.registerRitual(ritual);
    }

    public static void postInit() {
        ArsNouveauRegistry.addPerkSlots();
        ArsNouveauAPI.getInstance().getEnchantingRecipeTypes().add(ModRegistry.ELEMANCY_ARMOR_UP.get());
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

    }

    public static ArmorSet[] medium_armors = {ModItems.TEMPEST_ARMOR, ModItems.SILT_ARMOR, ModItems.MIRE_ARMOR, ModItems.VAPOR_ARMOR, ModItems.CINDER_ARMOR, ModItems.LAVA_ARMOR, ModItems.ELEMANCER_ARMOR};
    public static ArmorSet[] heavy_armors = {ModItems.TEMPEST_ARMOR_H, ModItems.SILT_ARMOR_H, ModItems.MIRE_ARMOR_H, ModItems.VAPOR_ARMOR_H, ModItems.CINDER_ARMOR_H, ModItems.LAVA_ARMOR_H, ModItems.ELEMANCER_ARMOR_H};
    public static ArmorSet[] light_armors = {ModItems.TEMPEST_ARMOR_L, ModItems.SILT_ARMOR_L, ModItems.MIRE_ARMOR_L, ModItems.VAPOR_ARMOR_L, ModItems.CINDER_ARMOR_L, ModItems.LAVA_ARMOR_L, ModItems.ELEMANCER_ARMOR_L};

    private static void addPerkSlots() {


        for (ArmorSet set : medium_armors) {
            PerkRegistry.registerPerkProvider(set.getHat(), makePerkList(Arrays.asList(PerkSlot.ONE, PerkSlot.TWO, PerkSlot.THREE)));
            PerkRegistry.registerPerkProvider(set.getChest(), makePerkList(Arrays.asList(PerkSlot.ONE, PerkSlot.TWO, PerkSlot.THREE)));
            PerkRegistry.registerPerkProvider(set.getLegs(), makePerkList(Arrays.asList(PerkSlot.ONE, PerkSlot.TWO, PerkSlot.THREE)));
            PerkRegistry.registerPerkProvider(set.getBoots(), makePerkList(Arrays.asList(PerkSlot.ONE, PerkSlot.TWO, PerkSlot.THREE)));
        }

        for (ArmorSet set : light_armors) {
            PerkRegistry.registerPerkProvider(set.getHat(), makePerkList(Arrays.asList(PerkSlot.ONE, PerkSlot.TWO, PerkSlot.THREE)));
            PerkRegistry.registerPerkProvider(set.getChest(), makePerkList(Arrays.asList(PerkSlot.TWO, PerkSlot.TWO, PerkSlot.THREE)));
            PerkRegistry.registerPerkProvider(set.getLegs(), makePerkList(Arrays.asList(PerkSlot.TWO, PerkSlot.TWO, PerkSlot.THREE)));
            PerkRegistry.registerPerkProvider(set.getBoots(), makePerkList(Arrays.asList(PerkSlot.ONE, PerkSlot.TWO, PerkSlot.THREE)));
        }

        for (ArmorSet set : heavy_armors) {
            PerkRegistry.registerPerkProvider(set.getHat(), makePerkList(Arrays.asList(PerkSlot.ONE, PerkSlot.TWO, PerkSlot.TWO)));
            PerkRegistry.registerPerkProvider(set.getChest(), makePerkList(Arrays.asList(PerkSlot.ONE, PerkSlot.TWO, PerkSlot.THREE)));
            PerkRegistry.registerPerkProvider(set.getLegs(), makePerkList(Arrays.asList(PerkSlot.ONE, PerkSlot.TWO, PerkSlot.THREE)));
            PerkRegistry.registerPerkProvider(set.getBoots(), makePerkList(Arrays.asList(PerkSlot.ONE, PerkSlot.TWO, PerkSlot.TWO)));
        }

    }

    private static @NotNull List<List<PerkSlot>> makePerkList(List<PerkSlot> perkSlots) {
        return List.of(perkSlots, perkSlots, perkSlots, perkSlots);
    }

}
