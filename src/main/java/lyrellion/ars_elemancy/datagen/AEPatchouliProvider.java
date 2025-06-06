package lyrellion.ars_elemancy.datagen;

import lyrellion.ars_elemancy.common.items.armor.ArmorSet;
import lyrellion.ars_elemancy.registry.ModItems;
import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.api.familiar.AbstractFamiliarHolder;
import com.hollingsworth.arsnouveau.api.perk.IPerk;
import com.hollingsworth.arsnouveau.api.registry.PerkRegistry;
import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.api.spell.AbstractCastMethod;
import com.hollingsworth.arsnouveau.api.spell.AbstractEffect;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.hollingsworth.arsnouveau.common.datagen.PatchouliProvider;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.*;
import com.hollingsworth.arsnouveau.common.items.PerkItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

import static lyrellion.ars_elemancy.ArsNouveauRegistry.registeredSpells;

public class AEPatchouliProvider extends PatchouliProvider {

    public AEPatchouliProvider(DataGenerator generatorIn, CompletableFuture<HolderLookup.Provider> provider) {
        super(generatorIn, provider);
    }

    @Override
    public void collectJsons(CachedOutput cache) {

        for (AbstractSpellPart spell : registeredSpells) {
            addGlyphPage(spell);
        }

        addBasicItem(ModItems.TEMPEST_BANGLE.get(), EQUIPMENT, new ApparatusPage(ModItems.TEMPEST_BANGLE.get()));
        addBasicItem(ModItems.MIRE_BANGLE.get(), EQUIPMENT, new ApparatusPage(ModItems.MIRE_BANGLE.get()));
        addBasicItem(ModItems.VAPOR_BANGLE.get(), EQUIPMENT, new ApparatusPage(ModItems.VAPOR_BANGLE.get()));
        addBasicItem(ModItems.SILT_BANGLE.get(), EQUIPMENT, new ApparatusPage(ModItems.SILT_BANGLE.get()));
        addBasicItem(ModItems.LAVA_BANGLE.get(), EQUIPMENT, new ApparatusPage(ModItems.LAVA_BANGLE.get()));
        addBasicItem(ModItems.CINDER_BANGLE.get(), EQUIPMENT, new ApparatusPage(ModItems.CINDER_BANGLE.get()));
        addBasicItem(ModItems.ELEMANCER_BANGLE.get(), EQUIPMENT, new ApparatusPage(ModItems.ELEMANCER_BANGLE.get()));

//
//        addPage(new PatchouliBuilder(EQUIPMENT, ModItems.FIRE_FOCUS.get())
//                        .withIcon(ModItems.FIRE_FOCUS.get())
//                        .withTextPage("ars_elemancy.page1.fire_focus")
//                        .withPage(ImbuementPage(ModItems.LESSER_FIRE_FOCUS.get()))
//                        .withPage(new ApparatusPage(ModItems.FIRE_FOCUS.get()))
//                        .withTextPage("ars_elemancy.page2.fire_focus")
//                , getPath(EQUIPMENT, "fire_focus")
//        );
//        addPage(new PatchouliBuilder(EQUIPMENT, ModItems.FIRE_BANGLE.get())
//                        .withIcon(ModItems.FIRE_BANGLE.get())
//                        .withTextPage("ars_elemancy.page1.fire_bangle")
//                        .withPage(new ApparatusPage(ModItems.FIRE_BANGLE.get()))
//                , getPath(EQUIPMENT, "fire_bangle")
//        );

        addArmorPage(ModItems.TEMPEST_ARMOR);
        addArmorPage(ModItems.MIRE_ARMOR);
        addArmorPage(ModItems.SILT_ARMOR);
        addArmorPage(ModItems.LAVA_ARMOR);
        addArmorPage(ModItems.VAPOR_ARMOR);
        addArmorPage(ModItems.CINDER_ARMOR);
        addArmorPage(ModItems.ELEMANCER_ARMOR);

        for (PatchouliPage patchouliPage : pages) {
            saveStable(cache, patchouliPage.build(), patchouliPage.path());
        }

    }

    private void addArmorPage(ArmorSet armorSet) {
        PatchouliBuilder builder = new PatchouliBuilder(ARMOR, armorSet.getTranslationKey())
                .withIcon(armorSet.getHat())
                .withPage(new TextPage("ars_elemancy.page.armor_set." + armorSet.getName()));

        for (Item piece : new Item[]{armorSet.getHat(), armorSet.getChest(), armorSet.getLegs(), armorSet.getBoots()}) {
            for (var wrapper : AEApparatusProvider.RECIPES) {
                if (wrapper.recipe().result().is(piece)) {
                    builder = builder.withPage(new AEPage(wrapper.id().toString()));
                }
            }
        }

        this.pages.add(new PatchouliPage(builder, getPath(ARMOR, "armor_" + armorSet.getName())));
    }

    static class AEPage extends ApparatusPage {
        public AEPage(String recipe) {
            super(recipe);
        }

        @Override
        public ResourceLocation getType() {
            return ResourceLocation.fromNamespaceAndPath(ArsNouveau.MODID, "elemancy_armor_recipe");
        }
    }

    @Override
    public void addPerkPage(IPerk perk) {
        PerkItem perkItem = PerkRegistry.getPerkItemMap().get(perk.getRegistryName());
        PatchouliBuilder builder = new PatchouliBuilder(ARMOR, perkItem)
                .withIcon(perkItem)
                .withTextPage(perk.getDescriptionKey())
                .withPage(new ApparatusPage(perkItem)).withSortNum(99);
        this.pages.add(new PatchouliPage(builder, getPath(ARMOR, perk.getRegistryName().getPath() + ".json")));
    }

    @Override
    public PatchouliPage addBasicItem(ItemLike item, ResourceLocation category, IPatchouliPage recipePage) {
        PatchouliBuilder builder = new PatchouliBuilder(category, item.asItem().getDescriptionId())
                .withIcon(item.asItem())
                .withPage(new TextPage("ars_elemancy.page." + getRegistryName(item.asItem()).getPath()))
                .withPage(recipePage);
        var page = new PatchouliPage(builder, getPath(category, getRegistryName(item.asItem()).getPath()));
        this.pages.add(page);
        return page;
    }

    public void addFamiliarPage(AbstractFamiliarHolder familiarHolder) {
        PatchouliBuilder builder = new PatchouliBuilder(FAMILIARS, "entity.ars_elemancy." + familiarHolder.getRegistryName().getPath())
                .withIcon("ars_elemancy:" + familiarHolder.getRegistryName().getPath())
                .withTextPage("ars_elemancy.familiar_desc." + familiarHolder.getRegistryName().getPath())
                .withPage(new EntityPage(familiarHolder.getRegistryName().toString()));
        this.pages.add(new PatchouliPage(builder, getPath(FAMILIARS, familiarHolder.getRegistryName().getPath())));
    }

    public void addRitualPage(AbstractRitual ritual) {
        PatchouliBuilder builder = new PatchouliBuilder(RITUALS, "item.ars_elemancy." + ritual.getRegistryName().getPath())
                .withIcon(ritual.getRegistryName().toString())
                .withTextPage(ritual.getDescriptionKey())
                .withPage(new CraftingPage("ars_elemancy:tablet_" + ritual.getRegistryName().getPath()));

        this.pages.add(new PatchouliPage(builder, getPath(RITUALS, ritual.getRegistryName().getPath())));
    }

    public void addGlyphPage(AbstractSpellPart spellPart) {
        ResourceLocation category = switch (spellPart.defaultTier().value) {
            case 1 -> GLYPHS_1;
            case 2 -> GLYPHS_2;
            default -> GLYPHS_3;
        };
        PatchouliBuilder builder = new PatchouliBuilder(category, spellPart.getName())
                .withName("ars_elemancy.glyph_name." + spellPart.getRegistryName().getPath())
                .withIcon(spellPart.getRegistryName().toString())
                .withSortNum(spellPart instanceof AbstractCastMethod ? 1 : spellPart instanceof AbstractEffect ? 2 : 3)
                .withPage(new TextPage("ars_elemancy.glyph_desc." + spellPart.getRegistryName().getPath()))
                .withPage(new GlyphScribePage(spellPart));
        this.pages.add(new PatchouliPage(builder, getPath(category, spellPart.getRegistryName().getPath())));
    }


    /**
     * Gets a name for this provider, to use in logging.
     */
    @Override
    public String getName() {
        return "Ars Elemancy Patchouli Datagen";
    }

    ImbuementPage ImbuementPage(ItemLike item) {
        return new ImbuementPage("ars_elemancy:imbuement_" + getRegistryName(item.asItem()).getPath());
    }

    private ResourceLocation getRegistryName(Item asItem) {
        return BuiltInRegistries.ITEM.getKey(asItem);
    }


}
